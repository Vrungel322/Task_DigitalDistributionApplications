package com.nanddgroup.task_digitaldistributionapplications.domain;

import android.util.Log;

import rx.Subscriber;

/**
 * Created by Nikita on 05.01.2017.
 */

public class BaseUseCaseSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        Log.e("exception", String.valueOf(e));
    }

    @Override
    public void onNext(T t) {

    }
}
