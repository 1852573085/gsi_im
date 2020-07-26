package com.aqiang.day0714_gisim.mvp.contract;

import com.aqiang.core.mvp.Repository;
import com.aqiang.core.mvp.model.IModel;
import com.aqiang.core.mvp.presenter.BasePresenter;
import com.aqiang.core.mvp.view.IView;
import com.aqiang.day0714_gisim.entity.LocalEntity;
import com.aqiang.day0714_gisim.entity.MsgEntity;
import com.aqiang.net.BaseResponseEntity;

import java.util.List;

import io.reactivex.Observable;

public interface LocalContract {
    interface LocalView extends IView{

    }

    interface LocalModel extends IModel{
        Observable<BaseResponseEntity<Boolean>> uploadLocal(LocalEntity localEntity);
    }

    abstract class LocalRepository extends Repository<LocalModel>{
        public abstract Observable<BaseResponseEntity<Boolean>> uploadLocal(LocalEntity localEntity);
    }

    abstract class LocalPresenter extends BasePresenter<LocalRepository,LocalView>{

        public LocalPresenter(LocalView localView) {
            super(localView);
        }

        public abstract void uploadLocal(LocalEntity localEntity);
    }
}
