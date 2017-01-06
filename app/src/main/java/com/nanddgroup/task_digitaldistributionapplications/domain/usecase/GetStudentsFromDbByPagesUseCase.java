package com.nanddgroup.task_digitaldistributionapplications.domain.usecase;

import com.nanddgroup.task_digitaldistributionapplications.SessionRepository;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Nikita on 06.01.2017.
 */

public class GetStudentsFromDbByPagesUseCase extends UseCase<List<StudentEntity>> {
    private SessionRepository sessionRepository;
    private Integer limit;

    @Inject
    public GetStudentsFromDbByPagesUseCase(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    protected Observable<List<StudentEntity>> getUseCaseObservable() {
        return sessionRepository.getLimitNumberOfStudentsFromDb(limit);
    }
}
