package com.nanddgroup.task_digitaldistributionapplications.presenters;

import com.nanddgroup.task_digitaldistributionapplications.FilterParams;

/**
 * Created by Nikita on 05.01.2017.
 */

public interface IMainActivityPresenter {
    void uploadData();
    void filterData(FilterParams filterParams);
    void uploadOneMorePage();
}
