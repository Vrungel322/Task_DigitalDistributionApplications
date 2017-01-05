package com.nanddgroup.task_digitaldistributionapplications.rest;

import android.content.Context;

import com.nanddgroup.task_digitaldistributionapplications.StudentEntity;

import rx.Observable;

/**
 * Created by Nikita on 05.01.2017.
 */

public class RestApi {
    private final Api api;
    private Context c;

    public RestApi(Api api, Context c) {
        this.api = api;
        this.c = c;
    }

    public Observable<StudentEntity> getAllContacts() {
        return api.getAllStudents();
    }
}
