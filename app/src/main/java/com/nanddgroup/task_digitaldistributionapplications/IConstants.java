package com.nanddgroup.task_digitaldistributionapplications;

/**
 * Created by Nikita on 05.01.2017.
 */

public interface IConstants {
    String DOMEN = "ddapp-sfa-api.azurewebsites.net";
    String BASE_URL = "http://" + DOMEN + "/";
    Integer PAGE_SIZE = 20;

    interface DB{
        String STUDENT_ID = "STUDENT_ID";
        String STUDENT_ORIGINAL_ID = "STUDENT_ORIGINAL_ID";
        String STUDENT_FIRST_NAME = "STUDENT_FIRST_NAME";
        String STUDENT_LAST_NAME = "STUDENT_LAST_NAME";
        String STUDENT_BIRTHDAY = "STUDENT_BIRTHDAY";

        String STUDENT_MARK_COURSE_0 = "MARK_COURSE_0";
        String STUDENT_MARK_COURSE_1 = "MARK_COURSE_1";
        String STUDENT_MARK_COURSE_2 = "MARK_COURSE_2";
        String STUDENT_MARK_COURSE_3 = "MARK_COURSE_3";

        String STUDENT_NAME_COURSE_0 = "COURSE_0";
        String STUDENT_NAME_COURSE_1 = "COURSE_1";
        String STUDENT_NAME_COURSE_2 = "COURSE_2";
        String STUDENT_NAME_COURSE_3 = "COURSE_3";
    }

}
