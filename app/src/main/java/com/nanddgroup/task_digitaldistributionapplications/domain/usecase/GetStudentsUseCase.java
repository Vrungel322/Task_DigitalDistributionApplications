package com.nanddgroup.task_digitaldistributionapplications.domain.usecase;

import com.nanddgroup.task_digitaldistributionapplications.SessionRepository;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Nikita on 05.01.2017.
 */

public class GetStudentsUseCase extends UseCase<List<StudentEntity>> {
    private SessionRepository sessionRepository;

    @Inject
    public GetStudentsUseCase(SessionRepository sessionRepository) {
        super();
        this.sessionRepository = sessionRepository;
    }

    @Override
    protected Observable<List<StudentEntity>> getUseCaseObservable() {
        return sessionRepository.loadAllStudents();
    }
}
