package com.nanddgroup.task_digitaldistributionapplications.views;

import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;

import java.util.List;

/**
 * Created by Nikita on 05.01.2017.
 */

public interface IMainActivityView extends IAppMainView {
//    void showData(List<StudentEntity> studentEntities);

    void showOneMorePageData(List<StudentEntity> studentEntities);

    void showFilteredData(List<StudentEntity> studentEntities);

    void showProgress();

    void hideProgress();

}
