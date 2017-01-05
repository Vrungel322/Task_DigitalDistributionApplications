package com.nanddgroup.task_digitaldistributionapplications.presenters;

import android.support.annotation.Nullable;

import com.nanddgroup.task_digitaldistributionapplications.views.IAppMainView;

/**
 * Created by Nikita on 05.01.2017.
 */

public abstract class BasePresenter<V extends IAppMainView> implements IPesenter<V> {
    @Nullable
    private V view;

    @Override
    public void bind(V view) {
        this.view = view;
    }

    @Override
    public void unbind() {
        this.view = null;
    }

    @Nullable
    public V getView() {
        return view;
    }
}
