package com.aqiang.day0714_gisim.mvp.presenter;

import android.util.Log;

import com.aqiang.day0714_gisim.mvp.contract.FindContract;
import com.aqiang.day0714_gisim.mvp.model.FindRepository;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.net.observer.BaseObservable;
import com.aqiang.net.observer.BaseObserver;
import com.aqiang.storage.sp.SPUtils;
import com.aqiang.usermodel.entity.UserEntity;
import com.baweigame.xmpplibrary.XmppManager;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    public void addFriend(String usercode, String friendcode) {
        if(mView.get() != null && mView != null){
            Observable<BaseResponseEntity<Boolean>> addFriend = mRepository.addFriend(usercode, friendcode);

            Observable<BaseResponseEntity<Boolean>> observable = Observable.create(new ObservableOnSubscribe<BaseResponseEntity<Boolean>>() {
                @Override
                public void subscribe(ObservableEmitter<BaseResponseEntity<Boolean>> emitter) throws Exception {
                    String friendJID = SPUtils.getInstance().getObject("user", "11") + "@" + XmppManager.getInstance().getConnection().getServiceName();
                    //EntityFullJid currentUser= XmppManager.getInstance().getXmppUserManager().getCurrentUser();//(String) SPUtils.get(MainActivity.this,"currentuser","");
                    boolean user = XmppManager.getInstance().getXmppFriendManager().addFriend(friendJID, (String) SPUtils.getInstance().getObject("user", "11"));
                    BaseResponseEntity<Boolean> booleanBaseResponseEntity = new BaseResponseEntity<>(1, user, "");
                    emitter.onNext(booleanBaseResponseEntity);
                }
            });

            Observable.merge(addFriend,observable)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new BaseObserver<BaseResponseEntity<Boolean>>() {
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
        mRepository = new FindRepository();
    }
}
