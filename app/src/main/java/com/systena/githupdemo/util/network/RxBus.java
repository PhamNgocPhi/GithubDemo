package com.systena.githupdemo.util.network;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxBus {

    private PublishSubject<String> sPublishSubject;
    private static RxBus sInstance;

    public static RxBus getInstance() {
        if(sInstance == null){
            sInstance = new RxBus();
            sInstance.sPublishSubject = PublishSubject.create();
        }
        return sInstance;
    }

    private PublishSubject<Object> bus = PublishSubject.create();

    public void send(Object o) {
        bus.onNext(o);
    }

    public Observable<Object> toObservable() {
        return bus;
    }
}
