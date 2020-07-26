package com.aqiang.day0714_gisim.mvp.contract;

import com.aqiang.core.mvp.Repository;
import com.aqiang.core.mvp.model.IModel;
import com.aqiang.core.mvp.presenter.BasePresenter;
import com.aqiang.core.mvp.view.IView;
import com.aqiang.day0714_gisim.entity.MsgEntity;
import com.aqiang.net.BaseResponseEntity;

import java.util.List;

import io.reactivex.Observable;

public interface MsgContract {
    interface MsgView extends IView{
        void initAdapter(List<MsgEntity> list);
    }

    interface MsgModel extends IModel{
        Observable<BaseResponseEntity<Boolean>> addMsg(MsgEntity msgEntity);

        Observable<BaseResponseEntity<List<MsgEntity>>> getMsg(String user1,String user2);
    }

    abstract class MsgRepository extends Repository<MsgModel>{
        public abstract Observable<BaseResponseEntity<Boolean>> addMsg(MsgEntity msgEntity);

        public abstract Observable<BaseResponseEntity<List<MsgEntity>>> getMsg(String user1,String user2);
    }

    abstract class MsgPresenter extends BasePresenter<MsgRepository,MsgView>{

        public MsgPresenter(MsgView msgView) {
            super(msgView);
        }

        public abstract void addMsg(MsgEntity msgEntity);

        public abstract void getMsg(String user1,String user2);
    }
}
