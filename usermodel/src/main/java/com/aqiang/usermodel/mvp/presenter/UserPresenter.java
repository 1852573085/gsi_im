package com.aqiang.usermodel.mvp.presenter;

import android.util.Log;

import com.aqiang.net.BaseResponseEntity;
import com.aqiang.net.observer.BaseObservable;
import com.aqiang.net.observer.BaseObserver;
import com.aqiang.usermodel.entity.UserEntity;
import com.aqiang.usermodel.mvp.contract.UserContract;
import com.aqiang.usermodel.mvp.model.UserRepository;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserPresenter extends UserContract.UserPresenter {
    public UserPresenter(UserContract.UserView userView) {
        super(userView);
    }

    @Override
    public void login(final UserEntity userEntity) {
        if(mView.get() != null && mView != null){
            Observable<BaseResponseEntity<UserEntity>> login = mRepository.login(userEntity);
            BaseObservable.doObservable(login, new BaseObserver<BaseResponseEntity<UserEntity>>() {
                @Override
                public void next(BaseResponseEntity<UserEntity> userEntityBaseResponseEntity) {
                    if(userEntityBaseResponseEntity.getCode() == 200){
                        mView.get().succee();
                    }
                }

                @Override
                public void error(String msg) {
                    Log.d("swq",""+msg);
                }
            });
        }
    }

    @Override
    public void register(UserEntity userEntity) {
        if(mView.get() != null && mView != null) {
            Observable<BaseResponseEntity<UserEntity>> register = mRepository.register(userEntity);
            BaseObservable.doObservable(register, new BaseObserver<BaseResponseEntity<UserEntity>>() {
                @Override
                public void next(BaseResponseEntity<UserEntity> userEntityBaseResponseEntity) {
                    if(userEntityBaseResponseEntity.getCode() == 200){
                        mView.get().succee();
                    }
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
        mRepository = new UserRepository();
    }
}
