package com.nanddgroup.task_digitaldistributionapplications.data.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nanddgroup.task_digitaldistributionapplications.IConstants;
import com.nanddgroup.task_digitaldistributionapplications.data.mappers.StudentEntityToContentValueMapper;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.Course;
import com.nanddgroup.task_digitaldistributionapplications.rest.entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nikita on 05.01.2017.
 */

public class DbStudentHelper {

    private static final String TABLE_NAME = "Students";
    private final DBHelper helper;
    private StudentEntityToContentValueMapper mapper;

    public DbStudentHelper(DBHelper helper, StudentEntityToContentValueMapper mapper) {
        this.helper = helper;
        this.mapper = mapper;
        createTable();
    }

    public void createTable() {
        SQLiteDatabase db = helper.getWritableDatabase();
        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " +
                "(" +
                IConstants.DB.STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                IConstants.DB.STUDENT_ORIGINAL_ID + " TEXT NOT NULL," +
                IConstants.DB.STUDENT_FIRST_NAME + " TEXT," +
                IConstants.DB.STUDENT_LAST_NAME + " TEXT," +
                IConstants.DB.STUDENT_BIRTHDAY + " TEXT NOT NULL," +
                IConstants.DB.STUDENT_MARK_COURSE_0 + " INTEGER," +
                IConstants.DB.STUDENT_MARK_COURSE_1 + " INTEGER," +
                IConstants.DB.STUDENT_MARK_COURSE_2 + " INTEGER," +
                IConstants.DB.STUDENT_MARK_COURSE_3 + " INTEGER," +
                IConstants.DB.STUDENT_NAME_COURSE_0 + " TEXT," +
                IConstants.DB.STUDENT_NAME_COURSE_1 + " TEXT," +
                IConstants.DB.STUDENT_NAME_COURSE_2 + " TEXT," +
                IConstants.DB.STUDENT_NAME_COURSE_3 + " TEXT," +
                " UNIQUE (" + IConstants.DB.STUDENT_ORIGINAL_ID + ") ON CONFLICT REPLACE" +
                ")" +
                ";";
        db.execSQL(CREATE_TABLE);
    }

    public long insert(StudentEntity studentEntity) {
        SQLiteDatabase db = helper.getWritableDatabase();
        long id = db.insert(TABLE_NAME, null, mapper.transform(studentEntity));
        return id;
    }

    public Observable<List<StudentEntity>> getAllStudentsFromDb() {
        String selectAll = "SELECT * FROM " + TABLE_NAME;
        return select(selectAll)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<StudentEntity>> getAllFilteredStudentsFromDb(String courseName, Integer courseMark) {
        String selectByName = "SELECT * FROM " + TABLE_NAME + " WHERE " + "( " +
                IConstants.DB.STUDENT_NAME_COURSE_0 + " = " + courseName + " OR " +
                IConstants.DB.STUDENT_NAME_COURSE_1 + " = " + courseName + " OR " +
                IConstants.DB.STUDENT_NAME_COURSE_2 + " = " + courseName + " OR " +
                IConstants.DB.STUDENT_NAME_COURSE_3 + " = " + courseName +
                ") " ;
        String selectByMark = "SELECT * FROM " + TABLE_NAME + " WHERE " + "( " +
                IConstants.DB.STUDENT_MARK_COURSE_0 + " = " + courseMark + " OR " +
                IConstants.DB.STUDENT_MARK_COURSE_1 + " = " + courseMark + " OR " +
                IConstants.DB.STUDENT_MARK_COURSE_2 + " = " + courseMark + " OR " +
                IConstants.DB.STUDENT_MARK_COURSE_3 + " = " + courseMark +
                ") ";
//        return select(selectByName)
        //todo right selection
        return select(selectByMark)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    private Observable<List<StudentEntity>> select(String query) {
//        Log.e("helper", query);
        return Observable.create(subscriber -> {
            List<StudentEntity> messages = synchronousSelect(query);
            for (StudentEntity st : messages){
                Log.e("helper",st.getFirstName()+" | " +  String.valueOf(st.getCourses().get(0).getMark()));
            }
            subscriber.onNext(messages);
            subscriber.onCompleted();
        });
    }

    private List<StudentEntity> synchronousSelect(String query) {
        List<StudentEntity> students = new ArrayList<>();
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                students.add(fetchStudent(cursor));
            }
            cursor.close();
        }
        return students;
    }

    private StudentEntity fetchStudent(Cursor cursor) {
        String studentId = DBCursorUtils.getString(cursor, IConstants.DB.STUDENT_ORIGINAL_ID);
        String studentName = DBCursorUtils.getString(cursor, IConstants.DB.STUDENT_FIRST_NAME);
        String studentSurname = DBCursorUtils.getString(cursor, IConstants.DB.STUDENT_LAST_NAME);
        String studentBirthday = DBCursorUtils.getString(cursor, IConstants.DB.STUDENT_BIRTHDAY);

        String studentCourse0Name = DBCursorUtils.getString(cursor, IConstants.DB.STUDENT_NAME_COURSE_0);
        String studentCourse1Name = DBCursorUtils.getString(cursor, IConstants.DB.STUDENT_NAME_COURSE_1);
        String studentCourse2Name = DBCursorUtils.getString(cursor, IConstants.DB.STUDENT_NAME_COURSE_2);
        String studentCourse3Name = DBCursorUtils.getString(cursor, IConstants.DB.STUDENT_NAME_COURSE_3);

        Integer studentCourse0Mark = DBCursorUtils.getInt(cursor, IConstants.DB.STUDENT_MARK_COURSE_0);
        Integer studentCourse1Mark = DBCursorUtils.getInt(cursor, IConstants.DB.STUDENT_MARK_COURSE_1);
        Integer studentCourse2Mark = DBCursorUtils.getInt(cursor, IConstants.DB.STUDENT_MARK_COURSE_2);
        Integer studentCourse3Mark = DBCursorUtils.getInt(cursor, IConstants.DB.STUDENT_MARK_COURSE_3);

        List<Course> courses = new ArrayList<>();
        courses.add(new Course(studentCourse0Mark, studentCourse0Name));
        courses.add(new Course(studentCourse1Mark, studentCourse1Name));
        courses.add(new Course(studentCourse2Mark, studentCourse2Name));
        courses.add(new Course(studentCourse3Mark, studentCourse3Name));

        return new StudentEntity(Integer.parseInt(studentBirthday), courses, studentName, studentId, studentSurname);
    }
}
