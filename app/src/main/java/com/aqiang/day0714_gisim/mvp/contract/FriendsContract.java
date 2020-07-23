package com.aqiang.day0714_gisim.mvp.contract;

import com.aqiang.core.mvp.Repository;
import com.aqiang.core.mvp.model.IModel;
import com.aqiang.core.mvp.presenter.BasePresenter;
import com.aqiang.core.mvp.view.IView;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.usermodel.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

public interface FriendsContract {
    interface FriendsView extends IView{
        void initAdapter(List<UserEntity> list);
    }

    interface FriendsModel extends IModel{
        Observable<BaseResponseEntity<List<UserEntity>>> getFriends(String usercode);
    }

    abstract class FriendsRepository extends Repository<FriendsModel>{
        public abstract Observable<BaseResponseEntity<List<UserEntity>>> getFriends(String usercode);
    }

    abstract class FriendsPresenter extends BasePresenter<FriendsRepository,FriendsView>{

        public FriendsPresenter(FriendsView friendsView) {
            super(friendsView);
        }

        public abstract void getFriends(String usercode);
    }
}
