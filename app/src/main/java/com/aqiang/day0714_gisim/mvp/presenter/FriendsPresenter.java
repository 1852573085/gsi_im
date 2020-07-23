package com.aqiang.day0714_gisim.mvp.presenter;

import android.util.Log;

import com.aqiang.day0714_gisim.mvp.contract.FriendsContract;
import com.aqiang.day0714_gisim.mvp.model.FriendsRepository;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.net.observer.BaseObservable;
import com.aqiang.net.observer.BaseObserver;
import com.aqiang.usermodel.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

public class FriendsPresenter extends FriendsContract.FriendsPresenter {
    public FriendsPresenter(FriendsContract.FriendsView friendsView) {
        super(friendsView);
    }

    @Override
    public void getFriends(String usercode) {
        if(mView.get() != null && mView != null){
            Observable<BaseResponseEntity<List<UserEntity>>> friends = mRepository.getFriends(usercode);
            BaseObservable.doObservable(friends, new BaseObserver<BaseResponseEntity<List<UserEntity>>>() {
                @Override
                public void next(BaseResponseEntity<List<UserEntity>> listBaseResponseEntity) {
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
        mRepository = new FriendsRepository();
    }
}
