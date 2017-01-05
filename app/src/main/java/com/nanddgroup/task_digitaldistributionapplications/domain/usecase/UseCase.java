package com.nanddgroup.task_digitaldistributionapplications.domain.usecase;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by Nikita on 05.01.2017.
 */

public abstract class UseCase<T> {
    private Subscription subscription = Subscriptions.empty();
    private Observable<T> observable;

    public UseCase() {
    }

    public void execute(Subscriber<T> subscriber) {
        Log.e("subscription", "subscribe " + observable);
        if (observable == null)
            observable = getUseCaseObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .cache()
                    .doOnError((t) -> observable = null)
                    .doOnCompleted(() -> observable = null);
        subscription = observable.subscribe(subscriber);
    }

    protected abstract Observable<T> getUseCaseObservable();

    public boolean isWorking() {
        return observable != null;
    }

    public void unsubscribe() {
        Log.e("subscription", "unsubscribe");
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
