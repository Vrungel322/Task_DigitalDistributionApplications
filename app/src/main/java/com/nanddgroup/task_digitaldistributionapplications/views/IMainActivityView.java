package com.nanddgroup.task_digitaldistributionapplications.views;

import com.nanddgroup.task_digitaldistributionapplications.StudentEntity;

import java.util.List;

/**
 * Created by Nikita on 05.01.2017.
 */

public interface IMainActivityView extends IAppMainView {
    void showData(List<StudentEntity> studentEntities);
    void showProgress();
    void hideProgress();
}
