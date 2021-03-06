package com.nanddgroup.task_digitaldistributionapplications.data;

import com.nanddgroup.task_digitaldistributionapplications.FilterParams;
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
        return restApi.getAllStudentsFromServer()
                .flatMap(this::saveStudentsToDb);
    }

    @Override
    public Observable<List<StudentEntity>> saveStudentsToDb(List<StudentEntity> students) {
        return Observable.from(students)
                .map(studentEntity -> {
                    dbHelper.insert(studentEntity);
                    return studentEntity;
                })
                .toList();
    }

    @Override
    public Observable<List<StudentEntity>> getStudentsFromDbByFilter(FilterParams filterParams, Integer limit) {
        return dbHelper.getAllFilteredStudentsFromDb(filterParams.getFilterParam_name(), filterParams.getFilterParam_mark(), limit);
    }

//    @Override
//    public Observable<List<StudentEntity>> getStudentsFromDb() {
//        return dbHelper.getAllStudentsFromDb();
//    }

    @Override
    public Observable<List<StudentEntity>> getLimitNumberOfStudentsFromDb(Integer limit) {
        return dbHelper.getLimitNumberOfStudentsFromDb(limit);
    }
}
