package com.nanddgroup.task_digitaldistributionapplications.data;

import com.nanddgroup.task_digitaldistributionapplications.SessionRepository;
import com.nanddgroup.task_digitaldistributionapplications.StudentEntity;
import com.nanddgroup.task_digitaldistributionapplications.data.db.DbStudentHelper;
import com.nanddgroup.task_digitaldistributionapplications.rest.RestApi;

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
        return restApi.getAllContacts()
//                .flatMap(/*todo save to db (143 srssionDataRepository*/)))
        ;
    }
}
