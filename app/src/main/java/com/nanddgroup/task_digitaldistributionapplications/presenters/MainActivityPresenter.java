package com.nanddgroup.task_digitaldistributionapplications.presenters;

import com.nanddgroup.task_digitaldistributionapplications.IConstants;
import com.nanddgroup.task_digitaldistributionapplications.domain.BaseUseCaseSubscriber;
import com.nanddgroup.task_digitaldistributionapplications.domain.usecase.GetStudentsFromDbByFilter;
import com.nanddgroup.task_digitaldistributionapplications.domain.usecase.GetStudentsFromDbByPages;
import com.nanddgroup.task_digitaldistributionapplications.domain.usecase.GetStudentsUseCase;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;
import com.nanddgroup.task_digitaldistributionapplications.views.IMainActivityView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Nikita on 05.01.2017.
 */

public class MainActivityPresenter extends BasePresenter<IMainActivityView> implements IMainActivityPresenter {
    private GetStudentsUseCase getStudentsUseCase;
    private GetStudentsFromDbByFilter getStudentsFromDbByFilter;
    private GetStudentsFromDbByPages getStudentsFromDbByPages;
    private Integer limit = IConstants.PAGE_SIZE;

    @Inject
    public MainActivityPresenter(GetStudentsUseCase getStudentsUseCase, GetStudentsFromDbByFilter getStudentsFromDbByFilter,
                                 GetStudentsFromDbByPages getStudentsFromDbByPages) {
        this.getStudentsUseCase = getStudentsUseCase;
        this.getStudentsFromDbByFilter = getStudentsFromDbByFilter;
        this.getStudentsFromDbByPages = getStudentsFromDbByPages;
    }

    @Override
    public void uploadData() {
        getStudentsUseCase.execute(getStudentsFromServerSubscriber());
    }

    @Override
    public void filterData(String courseName, Integer courseMark) {
        getStudentsFromDbByFilter.setCourseName(courseName);
        getStudentsFromDbByFilter.setCourseMark(courseMark);
        getStudentsFromDbByFilter.setLimit(limit);
        getStudentsFromDbByFilter.execute(getStudentsFromDbByFilterSubscriber());
    }

    @Override
    public void uploadOneMorePage() {
        limit = limit + IConstants.PAGE_SIZE;
        getStudentsFromDbByPages.setLimit(limit);
        getStudentsFromDbByPages.execute(getStudentsFromDbSubscriber());
    }

    public BaseUseCaseSubscriber<List<StudentEntity>> getStudentsFromDbSubscriber() {
        return new BaseUseCaseSubscriber<List<StudentEntity>>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showProgress();
                    getView().showMessage("Start loading data from database");
                }
            }

            @Override
            public void onNext(List<StudentEntity> studentEntities) {
                super.onNext(studentEntities);
                if (getView() != null) {
                    getView().showData(studentEntities);
                    getView().hideProgress();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (getView() != null) {
                    getView().hideProgress();
                    getView().showMessage("Something went wrong... Restart app");
                }
            }
        };
    }

    public BaseUseCaseSubscriber<List<StudentEntity>> getStudentsFromServerSubscriber() {
        return new BaseUseCaseSubscriber<List<StudentEntity>>() {
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showProgress();
                    getView().showMessage("Start loading data from server");
                }
            }

            @Override
            public void onNext(List<StudentEntity> studentEntities) {
                super.onNext(studentEntities);
                if (getView() != null) {
                    getStudentsFromDbByPages.setLimit(IConstants.PAGE_SIZE);
                    getStudentsFromDbByPages.execute(getStudentsFromDbSubscriber());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (getView() != null) {
                    getView().hideProgress();
                    getView().showMessage("Something went wrong... Restart app");
                }
            }
        };
    }

    public BaseUseCaseSubscriber<List<StudentEntity>> getStudentsFromDbByFilterSubscriber() {
        return new BaseUseCaseSubscriber<List<StudentEntity>>(){
            @Override
            public void onStart() {
                super.onStart();
                if (getView() != null) {
                    getView().showProgress();
                }
            }

            @Override
            public void onNext(List<StudentEntity> studentEntities) {
                super.onNext(studentEntities);
                if (getView() != null) {
                    getView().showFilteredData(studentEntities);
                    getView().hideProgress();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (getView() != null) {
                    getView().showProgress();
                    getView().showMessage("Error in filtering");
                }
            }
        };
    }

    @Override
    public void unbind() {
        getStudentsFromDbByPages.unsubscribe();
        getStudentsFromDbByFilter.unsubscribe();
        getStudentsUseCase.unsubscribe();
        super.unbind();
    }


}
