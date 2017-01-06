package com.nanddgroup.task_digitaldistributionapplications.domain.usecase;

import android.util.Log;

import com.nanddgroup.task_digitaldistributionapplications.SessionRepository;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Nikita on 06.01.2017.
 */

public class GetStudentsFromDbByFilter extends UseCase<List<StudentEntity>> {
    private SessionRepository sessionRepository;
    private String courseName;
    private Integer courseMark;
    private Integer limit;

    @Inject
    public GetStudentsFromDbByFilter(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseMark(Integer courseMark) {
        this.courseMark = courseMark;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    @Override
    protected Observable<List<StudentEntity>> getUseCaseObservable() {
        if (!courseName.equals("") & courseMark != 0) {
            Log.wtf("DB_TEST", "1");
            return sessionRepository.getStudentsFromDbByFilter(courseName, courseMark, limit);
        } else {
            Log.wtf("DB_TEST", "2");
            return sessionRepository.getLimitNumberOfStudentsFromDb(limit);
        }
    }
}
