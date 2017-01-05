package com.nanddgroup.task_digitaldistributionapplications.rest;

import android.content.Context;

import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;

import java.util.List;

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

    public Observable<List<StudentEntity>> getAllStudents() {
        return api.getAllStudents();
    }
}
