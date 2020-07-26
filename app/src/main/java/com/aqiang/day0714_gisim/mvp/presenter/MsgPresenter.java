package com.aqiang.day0714_gisim.mvp.presenter;

import android.util.Log;

import com.aqiang.day0714_gisim.entity.MsgEntity;
import com.aqiang.day0714_gisim.mvp.contract.MsgContract;
import com.aqiang.day0714_gisim.mvp.model.MsgRepository;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.net.observer.BaseObservable;
import com.aqiang.net.observer.BaseObserver;

import java.util.List;

import io.reactivex.Observable;

public class MsgPresenter extends MsgContract.MsgPresenter {
    public MsgPresenter(MsgContract.MsgView msgView) {
        super(msgView);
    }

    @Override
    public void addMsg(MsgEntity msgEntity) {
        if(mView != null && mView.get() != null){
            Observable<BaseResponseEntity<Boolean>> addMsg = mRepository.addMsg(msgEntity);
            BaseObservable.doObservable(addMsg, new BaseObserver<BaseResponseEntity<Boolean>>() {
                @Override
                public void next(BaseResponseEntity<Boolean> booleanBaseResponseEntity) {

                }

                @Override
                public void error(String msg) {

                }
            });
        }
    }

    @Override
    public void getMsg(String user1, String user2) {
        if(mView != null && mView.get() != null){
            Observable<BaseResponseEntity<List<MsgEntity>>> msg = mRepository.getMsg(user1, user2);
            BaseObservable.doObservable(msg, new BaseObserver<BaseResponseEntity<List<MsgEntity>>>() {
                @Override
                public void next(BaseResponseEntity<List<MsgEntity>> listBaseResponseEntity) {
                    mView.get().initAdapter(listBaseResponseEntity.getData());
                }

                @Override
                public void error(String msg) {
                    Log.d("swq",""+msg);
                }
            });
        }
    }

    @Override
    protected void createRepository() {
        mRepository = new MsgRepository();
    }
}
