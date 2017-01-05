package com.nanddgroup.task_digitaldistributionapplications.presenters;

import com.nanddgroup.task_digitaldistributionapplications.StudentEntity;
import com.nanddgroup.task_digitaldistributionapplications.domain.BaseUseCaseSubscriber;
import com.nanddgroup.task_digitaldistributionapplications.domain.usecase.GetStudentsUseCase;
import com.nanddgroup.task_digitaldistributionapplications.views.IMainActivityView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Nikita on 05.01.2017.
 */

public class MainActivityPresenter extends BasePresenter<IMainActivityView> implements IMainActivityPresenter {
    private GetStudentsUseCase getStudentsUseCase;

    @Inject
    public MainActivityPresenter(GetStudentsUseCase getStudentsUseCase) {
        this.getStudentsUseCase = getStudentsUseCase;
    }

    @Override
    public void bind(IMainActivityView view) {
        super.bind(view);
    }

    @Override
    public void uploadData() {
        getStudentsUseCase.execute(getStudentsSubscriber());
    }

    public BaseUseCaseSubscriber<List<StudentEntity>> getStudentsSubscriber() {
        return new BaseUseCaseSubscriber<List<StudentEntity>>(){
            @Override
            public void onNext(List<StudentEntity> studentEntities) {
                super.onNext(studentEntities);
                if(getView() != null) {
                    getView().showData(studentEntities);
                }
            }
        };
    }

    @Override
    public void unbind() {
        getStudentsUseCase.unsubscribe();
        super.unbind();
    }
}