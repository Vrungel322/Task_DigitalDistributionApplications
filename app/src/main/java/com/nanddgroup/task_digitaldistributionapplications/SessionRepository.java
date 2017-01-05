package com.nanddgroup.task_digitaldistributionapplications;

import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Nikita on 05.01.2017.
 */

public interface SessionRepository {

    Observable<List<StudentEntity>> loadAllStudents();

    Observable<List<StudentEntity>> saveStudentsToDb(List<StudentEntity> students);
}
