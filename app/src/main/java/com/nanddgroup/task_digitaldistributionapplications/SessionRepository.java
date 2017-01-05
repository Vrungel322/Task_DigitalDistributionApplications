package com.nanddgroup.task_digitaldistributionapplications;

import java.util.List;

import rx.Observable;

/**
 * Created by Nikita on 05.01.2017.
 */

public interface SessionRepository {

    Observable<List<com.nanddgroup.task_digitaldistributionapplications.StudentEntity>> loadAllStudents();
}
