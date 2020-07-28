package com.aqiang.day0714_gisim.mvp.view.activity;

import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import com.aqiang.core.mvp.view.BaseActivity;
import com.aqiang.day0714_gisim.R;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

public class VideoActivity extends BaseActivity implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private Boolean isStart = false;
    private SurfaceView mSvActVideo;
    private Button mBtActVideoStart;
    private MediaRecorder mediaRecorder;
    private String path;
    @Override
    protected int bindLayout() {
        return R.layout.activity_video;
    }

    @Override
    protected void createPresenter() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

        mSvActVideo = (SurfaceView) findViewById(R.id.sv_act_video);
        mBtActVideoStart = (Button) findViewById(R.id.bt_act_video_start);
    }

    @Override
    protected void initData() {
        SurfaceHolder holder = mSvActVideo.getHolder();
        holder.addCallback(this);
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    protected void initEvent() {
        mBtActVideoStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isStart){
                    if(mediaRecorder == null){
                        mediaRecorder = new MediaRecorder();
                    }
                    Camera camera = Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK);
                    camera.unlock();
                    camera.setDisplayOrientation(90);
                    mediaRecorder.setCamera(camera);
                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
                    mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);

                    path = getSdPath();
                    if (path != null) {
                        File file = new File(path + File.separator + "video");
                        if (!file.exists()) {
                            file.mkdir();
                        }
                        path = file + File.separator + getData() + ".mp4";
                        Log.d("swq", "" + path);
                        mediaRecorder.setOutputFile(path);

                        try {
                            mediaRecorder.prepare();
                            mediaRecorder.start();
                            mBtActVideoStart.setText("停止");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //开始录制

                        isStart = true;
                    }
                }else {
                    try {
                        mediaRecorder.stop();
                        mediaRecorder.reset();
                        mediaRecorder.release();
                        mediaRecorder = null;
                        mBtActVideoStart.setText("开始");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    isStart = false;
                }
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        surfaceHolder = holder;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        surfaceHolder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private String getSdPath() {
        return Environment.getExternalStoragePublicDirectory("DCIM").getAbsolutePath();
    }

    private String getData() {
        Calendar ca = Calendar.getInstance();
        // 获取年份
        int year = ca.get(Calendar.YEAR);
        // 获取月份
        int month = ca.get(Calendar.MONTH);
        // 获取日
        int day = ca.get(Calendar.DATE);
        // 分
        int minute = ca.get(Calendar.MINUTE);
        // 小时
        int hour = ca.get(Calendar.HOUR);
        // 秒
        int second = ca.get(Calendar.SECOND);

        String date = "" + year + (month + 1) + day + hour + minute + second;

        return date;
    }
}
