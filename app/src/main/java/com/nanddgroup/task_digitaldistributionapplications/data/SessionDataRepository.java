package com.nanddgroup.task_digitaldistributionapplications.data;

import android.util.Log;

import com.nanddgroup.task_digitaldistributionapplications.SessionRepository;
import com.nanddgroup.task_digitaldistributionapplications.data.db.DbStudentHelper;
import com.nanddgroup.task_digitaldistributionapplications.rest.RestApi;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Nikita on 05.01.2017.
 */

public class SessionDataRepository implements SessionRepository {
    private RestApi restApi;
    private DbStudentHelper dbHelper;

    public SessionDataRepository(RestApi restApi, DbStudentHelper dbHelper) {
        this.restApi = restApi;
        this.dbHelper = dbHelper;
    }

    @Override
    public Observable<List<StudentEntity>> loadAllStudents() {
        return restApi.getAllStudents()
                .flatMap(this::saveStudentsToDb);
    }



    @Override
    public Observable<List<StudentEntity>> saveStudentsToDb(List<StudentEntity> students) {
        return Observable.from(students)
                .map(studentEntity -> {
                    dbHelper.insert(studentEntity);
                    Log.wtf("DB_TEST", studentEntity.getFirstName());
                    return studentEntity;
                })
                .toList();
    }
}
