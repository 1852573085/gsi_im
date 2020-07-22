package com.aqiang.day0714_gisim.mvp.presenter;

import android.util.Log;

import com.aqiang.day0714_gisim.mvp.contract.FindContract;
import com.aqiang.day0714_gisim.mvp.model.FindRepository;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.net.observer.BaseObservable;
import com.aqiang.net.observer.BaseObserver;
import com.aqiang.usermodel.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

public class FindPresenter extends FindContract.FindPresenter {
    public FindPresenter(FindContract.FindView findView) {
        super(findView);
    }

    @Override
    public void findFriend(String username, String nick) {
        if(mView.get() != null && mView != null){
            Observable<BaseResponseEntity<List<UserEntity>>> friend = mRepository.findFriend(username, nick);
            BaseObservable.doObservable(friend, new BaseObserver<BaseResponseEntity<List<UserEntity>>>() {
                @Override
                public void next(BaseResponseEntity<List<UserEntity>> listBaseResponseEntity) {
                    mView.get().setAdapter(listBaseResponseEntity.getData());
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
        mRepository = new FindRepository();
    }
}
