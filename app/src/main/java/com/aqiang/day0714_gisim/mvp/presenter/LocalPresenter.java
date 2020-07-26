package com.aqiang.day0714_gisim.mvp.presenter;

import com.aqiang.day0714_gisim.entity.LocalEntity;
import com.aqiang.day0714_gisim.mvp.contract.LocalContract;
import com.aqiang.day0714_gisim.mvp.model.LocalRepository;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.net.observer.BaseObservable;
import com.aqiang.net.observer.BaseObserver;

import io.reactivex.Observable;

public class LocalPresenter extends LocalContract.LocalPresenter {

    public LocalPresenter(LocalContract.LocalView localView) {
        super(localView);
    }

    @Override
    public void uploadLocal(LocalEntity localEntity) {
        if(mView.get() != null && mView != null){
            Observable<BaseResponseEntity<Boolean>> uploadLocal = mRepository.uploadLocal(localEntity);
            BaseObservable.doObservable(uploadLocal, new BaseObserver<BaseResponseEntity<Boolean>>() {
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
    protected void createRepository() {
        mRepository = new LocalRepository();
    }
}
