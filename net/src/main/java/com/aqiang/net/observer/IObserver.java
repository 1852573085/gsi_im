package com.aqiang.net.observer;

public interface IObserver<T> {
    void next(T t);
    void error(String msg);
}
