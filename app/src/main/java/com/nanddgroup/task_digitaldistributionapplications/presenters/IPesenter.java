package com.nanddgroup.task_digitaldistributionapplications.presenters;

import com.nanddgroup.task_digitaldistributionapplications.views.IAppMainView;

/**
 * Created by Nikita on 05.01.2017.
 */

public interface IPesenter<V extends IAppMainView> {
    void bind(V view);

    void unbind();
}
