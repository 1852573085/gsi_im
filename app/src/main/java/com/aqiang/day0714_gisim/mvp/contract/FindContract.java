package com.aqiang.day0714_gisim.mvp.contract;

import com.aqiang.core.mvp.Repository;
import com.aqiang.core.mvp.model.IModel;
import com.aqiang.core.mvp.presenter.BasePresenter;
import com.aqiang.core.mvp.view.IView;
import com.aqiang.day0714_gisim.mvp.model.service.FindService;
import com.aqiang.net.BaseResponseEntity;
import com.aqiang.usermodel.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;

public interface FindContract {
    interface FindView extends IView{
        void setAdapter(List<UserEntity> list);
    }

    interface FindModel extends IModel{
        Observable<BaseResponseEntity<List<UserEntity>>> findFriend(String username,String nick);

        Observable<BaseResponseEntity<Boolean>> addFriend(String usercode,String friendcode);
    }

    abstract class FindRepository extends Repository<FindModel>{
        public abstract Observable<BaseResponseEntity<List<UserEntity>>> findFriend(String username,String nick);

        public abstract Observable<BaseResponseEntity<Boolean>> addFriend(String usercode,String friendcode);
    }

    abstract class FindPresenter extends BasePresenter<FindRepository,FindView>{

        public FindPresenter(FindView findView) {
            super(findView);
        }

        public abstract void findFriend(String username,String nick);

        public abstract void addFriend(String usercode,String friendcode);
    }
}
