package com.nanddgroup.task_digitaldistributionapplications.data.mappers;

import android.content.ContentValues;

import com.nanddgroup.task_digitaldistributionapplications.IConstants;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;

/**
 * Created by Nikita on 05.01.2017.
 */

public class StudentEntityToContentValueMapper implements Mapper<StudentEntity, ContentValues> {
    @Override
    public ContentValues transform(StudentEntity obj) throws RuntimeException {
        ContentValues contentValues = new ContentValues();
        contentValues.put(IConstants.DB.STUDENT_ORIGINAL_ID , obj.getId());
        contentValues.put(IConstants.DB.STUDENT_FIRST_NAME , obj.getFirstName());
        contentValues.put(IConstants.DB.STUDENT_LAST_NAME , obj.getLastName());
        contentValues.put(IConstants.DB.STUDENT_BIRTHDAY , obj.getBirthday());
        contentValues.put(IConstants.DB.STUDENT_MARK_COURSE_0 , obj.getCourses().get(0).getMark());
        contentValues.put(IConstants.DB.STUDENT_MARK_COURSE_1 , obj.getCourses().get(1).getMark());
        contentValues.put(IConstants.DB.STUDENT_MARK_COURSE_2 , obj.getCourses().get(2).getMark());
        contentValues.put(IConstants.DB.STUDENT_MARK_COURSE_3 , obj.getCourses().get(3).getMark());
        contentValues.put(IConstants.DB.STUDENT_NAME_COURSE_0 , obj.getCourses().get(0).getName());
        contentValues.put(IConstants.DB.STUDENT_NAME_COURSE_1 , obj.getCourses().get(1).getName());
        contentValues.put(IConstants.DB.STUDENT_NAME_COURSE_2 , obj.getCourses().get(2).getName());
        contentValues.put(IConstants.DB.STUDENT_NAME_COURSE_3 , obj.getCourses().get(3).getName());
        return contentValues;
    }
}