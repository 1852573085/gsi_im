package com.aqiang.day0714_gisim.audio;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Message;



public class Mp3Recorder {

    private static final String TAG = Mp3Recorder.class.getSimpleName();

    static {
        System.loadLibrary("lamemp3");
    }

    //默认采样率
    private static final int DEFAULT_SAMPLING_RATE = 44100;

    //转换周期，录音每满160帧，进行一次转换
    private static final int FRAME_COUNT = 160;

    //输出MP3的码率
    private static final int BIT_RATE = 32;

    //根据资料假定的最大值。 实测时有时超过此值。
    private static final int MAX_VOLUME = 2000;

    private AudioRecord audioRecord = null;

    private int bufferSize;

    private File mp3File;

    private int mVolume;

    private short[] mPCMBuffer;

    private FileOutputStream os = null;

    private Mp3EncodeThread encodeThread;

    private int samplingRate;

    private int channelConfig;

    private PCMFormat audioFormat;

    private boolean isRecording = false;

    private ExecutorService executor = Executors.newFixedThreadPool(1);

    private OnFinishListener finishListener;

    public interface OnFinishListener {
        void onFinish(String mp3SavePath);
    }

    public Mp3Recorder(int samplingRate, int channelConfig, PCMFormat audioFormat) {
        this.samplingRate = samplingRate;
        this.channelConfig = channelConfig;
        this.audioFormat = audioFormat;
    }

    /**
     * Default constructor. Setup recorder with default sampling rate 1 channel,
     * 16 bits pcm
     */
    public Mp3Recorder() {
        this(DEFAULT_SAMPLING_RATE, AudioFormat.CHANNEL_IN_MONO, PCMFormat.PCM_16BIT);
    }


    public void startRecording(File mp3Save) throws IOException {
        if (isRecording) {return;}
        //LogUtils.d("Start recording");
        //LogUtils.d( "BufferSize = " + bufferSize);

        this.mp3File = mp3Save;
        if (audioRecord == null) {
            initAudioRecorder();
        }
        audioRecord.startRecording();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                isRecording = true;
                //循环的从AudioRecord获取录音的PCM数据
                while (isRecording) {
                    int readSize = audioRecord.read(mPCMBuffer, 0, bufferSize);
                    if (readSize > 0) {
                        //待转换的PCM数据放到转换线程中
                        encodeThread.addChangeBuffer(mPCMBuffer,readSize);
                        calculateRealVolume(mPCMBuffer, readSize);
                    }
                }

                // 录音完毕，释放AudioRecord的资源
                try {
                    audioRecord.stop();
                    audioRecord.release();
                    audioRecord = null;

                    // 录音完毕，通知转换线程停止，并等待直到其转换完毕
                    Message msg = Message.obtain(encodeThread.getHandler(), Mp3EncodeThread.PROCESS_STOP);
                    msg.sendToTarget();

//                    LogUtils.d( "waiting for encoding thread");
                    encodeThread.join();
                    //LogUtils.d("done encoding thread");
                    //转换完毕后回调监听
                    if(finishListener != null){ finishListener.onFinish(mp3File.getPath());}
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (os != null) {
                        try {
                            os.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        };
        executor.execute(runnable);
    }


    public void stopRecording() throws IOException {
      //  LogUtils.d( "stop recording");
        isRecording = false;
    }

    //计算音量大小
    private void calculateRealVolume(short[] buffer, int readSize) {
        double sum = 0;
        for (int i = 0; i < readSize; i++) {
            sum += buffer[i] * buffer[i];
        }
        if (readSize > 0) {
            double amplitude = sum / readSize;
            mVolume = (int) Math.sqrt(amplitude);
        }
    }

    public int getVolume(){
        if (mVolume >= MAX_VOLUME) {
            return MAX_VOLUME;
        }
        return mVolume;
    }

    public int getMaxVolume(){
        return MAX_VOLUME;
    }


    public void setFinishListener(OnFinishListener listener){
        this.finishListener = listener;
    }

    private void initAudioRecorder() throws IOException {
        int bytesPerFrame = audioFormat.getBytesPerFrame();
		/* Get number of samples. Calculate the mPCMBuffer size (round up to the factor of given frame size) */
        int frameSize = AudioRecord.getMinBufferSize(samplingRate, channelConfig, audioFormat.getAudioFormat()) / bytesPerFrame;
        if (frameSize % FRAME_COUNT != 0) {
            frameSize = frameSize + (FRAME_COUNT - frameSize % FRAME_COUNT);
            //LogUtils.d( "Frame size: " + frameSize);
        }
        bufferSize = frameSize * bytesPerFrame;

        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, samplingRate, channelConfig, audioFormat.getAudioFormat(), bufferSize);

        mPCMBuffer = new short[bufferSize];


        init(samplingRate, 1, samplingRate, BIT_RATE,7);

        os = new FileOutputStream(mp3File);

        // Create and run thread used to encode data
        encodeThread = new Mp3EncodeThread(os, bufferSize);
        encodeThread.start();
        //给AudioRecord设置刷新监听，待录音帧数每次达到FRAME_COUNT，就通知转换线程转换一次数据
        audioRecord.setRecordPositionUpdateListener(encodeThread, encodeThread.getHandler());
        audioRecord.setPositionNotificationPeriod(FRAME_COUNT);
    }


    /**
     * 获取Lame版本号
     * @return
     */
    public native String getVersion();

    /**
     * 关闭
     */
    public native static void close();

    /**
     * PCM 2 MP3
     * @param buffer_l
     * @param buffer_r
     * @param samples
     * @param mp3buf
     * @return
     */
    public native static int encode(short[] buffer_l, short[] buffer_r, int samples, byte[] mp3buf);

    /**
     * 将MP3结尾信息写入buffer中
     * @param mp3buf
     * @return
     */
    public native static int flush(byte[] mp3buf);

    /**
     * 初始化Lame
     * @param inSampleRate
     * @param outChannel
     * @param outSampleRate
     * @param outBitrate
     * @param quality
     */
    public native static void init(int inSampleRate, int outChannel, int outSampleRate, int outBitrate, int quality);
}
