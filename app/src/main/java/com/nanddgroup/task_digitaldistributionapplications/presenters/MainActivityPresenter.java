package com.nanddgroup.task_digitaldistributionapplications.presenters;

import com.nanddgroup.task_digitaldistributionapplications.FilterParams;
import com.nanddgroup.task_digitaldistributionapplications.IConstants;
import com.nanddgroup.task_digitaldistributionapplications.domain.BaseUseCaseSubscriber;
import com.nanddgroup.task_digitaldistributionapplications.domain.usecase.GetStudentsFromDbByFilterUseCase;
import com.nanddgroup.task_digitaldistributionapplications.domain.usecase.GetStudentsFromDbByPagesUseCase;
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
    private GetStudentsFromDbByFilterUseCase getStudentsFromDbByFilterUseCase;
    private GetStudentsFromDbByPagesUseCase getStudentsFromDbByPagesUseCase;
    private Integer limit = IConstants.PAGE_SIZE;

    @Inject
    public MainActivityPresenter(GetStudentsUseCase getStudentsUseCase, GetStudentsFromDbByFilterUseCase getStudentsFromDbByFilterUseCase,
                                 GetStudentsFromDbByPagesUseCase getStudentsFromDbByPagesUseCase) {
        this.getStudentsUseCase = getStudentsUseCase;
        this.getStudentsFromDbByFilterUseCase = getStudentsFromDbByFilterUseCase;
        this.getStudentsFromDbByPagesUseCase = getStudentsFromDbByPagesUseCase;
    }

    @Override
    public void uploadData() {
        getStudentsUseCase.execute(getStudentsFromServerSubscriber());
    }

    @Override
    public void filterData(FilterParams filterParams) {
        getStudentsFromDbByFilterUseCase.setFilterParams(filterParams);
        getStudentsFromDbByFilterUseCase.setLimit(limit);
        getStudentsFromDbByFilterUseCase.execute(getStudentsFromDbByFilterSubscriber());
    }

    @Override
    public void uploadOneMorePage() {
        increaseLimit();
        getStudentsFromDbByPagesUseCase.setLimit(limit);
        getStudentsFromDbByPagesUseCase.execute(getStudentsFromDbSubscriber());
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
                    getView().showOneMorePageData(studentEntities);
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
                    getStudentsFromDbByPagesUseCase.setLimit(IConstants.PAGE_SIZE);
                    getStudentsFromDbByPagesUseCase.execute(getStudentsFromDbSubscriber());
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

    private void increaseLimit() {
        limit = limit + IConstants.PAGE_SIZE;
    }

    @Override
    public void unbind() {
        getStudentsFromDbByPagesUseCase.unsubscribe();
        getStudentsFromDbByFilterUseCase.unsubscribe();
        getStudentsUseCase.unsubscribe();
        super.unbind();
    }


}
