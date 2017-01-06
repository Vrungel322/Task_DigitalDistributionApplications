package com.nanddgroup.task_digitaldistributionapplications.presenters;

import com.nanddgroup.task_digitaldistributionapplications.domain.BaseUseCaseSubscriber;
import com.nanddgroup.task_digitaldistributionapplications.domain.usecase.GetStudentsFromDbByFilter;
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

    @Inject
    public MainActivityPresenter(GetStudentsUseCase getStudentsUseCase, GetStudentsFromDbByFilter getStudentsFromDbByFilter) {
        this.getStudentsUseCase = getStudentsUseCase;
        this.getStudentsFromDbByFilter = getStudentsFromDbByFilter;
    }



    @Override
    public void uploadData() {
        getStudentsUseCase.execute(getStudentsSubscriber());
    }

    @Override
    public void filterData(String courseName, Integer courseMark) {
        getStudentsFromDbByFilter.setCourseName(courseName);
        getStudentsFromDbByFilter.setCourseMark(courseMark);
        getStudentsFromDbByFilter.execute(getStudentsFromDbByFilterSubscriber());
    }

    public BaseUseCaseSubscriber<List<StudentEntity>> getStudentsSubscriber() {
        return new BaseUseCaseSubscriber<List<StudentEntity>>() {
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
                    getView().showData(studentEntities);
                    getView().hideProgress();
                }
            }

            @Override
            public void onCompleted() {
                super.onCompleted();
                if (getView() != null) {
                    getView().hideProgress();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (getView() != null) {
                    getView().hideProgress();
                    getView().showMessage("Something went wrong");
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
        getStudentsFromDbByFilter.unsubscribe();
        getStudentsUseCase.unsubscribe();
        super.unbind();
    }


}
