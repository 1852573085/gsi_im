package com.aqiang.usermodel.mvp.contract;

import com.aqiang.core.mvp.Repository;
import com.aqiang.core.mvp.model.IModel;
import com.aqiang.core.mvp.presenter.BasePresenter;
import com.aqiang.core.mvp.view.IView;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.usermodel.entity.UserEntity;

import io.reactivex.Observable;

public interface UserContract {
    interface UserView extends IView {
        String getUser();
        String getPwd();
        void succee();
    }

    interface UserModel extends IModel{
        Observable<BaseResponseEntity<UserEntity>> login(UserEntity userEntity);
        Observable<BaseResponseEntity<UserEntity>> register(UserEntity userEntity);
    }

    abstract class UserRepository extends Repository<UserModel>{
        public abstract Observable<BaseResponseEntity<UserEntity>> login(UserEntity userEntity);
        public abstract Observable<BaseResponseEntity<UserEntity>> register(UserEntity userEntity);
    }

    abstract class UserPresenter extends BasePresenter<UserRepository,UserView>{

        public UserPresenter(UserView userView) {
            super(userView);
        }

        public abstract void login(UserEntity userEntity);
        public abstract void register(UserEntity userEntity);
    }
}
