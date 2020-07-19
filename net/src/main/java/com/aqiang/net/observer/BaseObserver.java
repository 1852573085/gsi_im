package com.aqiang.net.observer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> ,IObserver<T> {
    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        next(t);
    }

    @Override
    public void onError(Throwable e) {
        error(e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
