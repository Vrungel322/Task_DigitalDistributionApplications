package com.nanddgroup.task_digitaldistributionapplications.rest;

import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Nikita on 05.01.2017.
 */

public interface Api {

    @GET("api/test/sampleData")
    Observable<List<StudentEntity>> getAllStudents();
}
