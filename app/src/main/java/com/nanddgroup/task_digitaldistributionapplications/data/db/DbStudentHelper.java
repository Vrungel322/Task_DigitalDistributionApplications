package com.nanddgroup.task_digitaldistributionapplications.data.db;

/**
 * Created by Nikita on 05.01.2017.
 */

public class DbStudentHelper {

    private static final String TABLE_NAME = "Contacts";
    private final DBHelper helper;

    public DbStudentHelper(DBHelper helper) {
        this.helper = helper;
        createTable();
    }

    public void createTable() {
//        SQLiteDatabase db = helper.getWritableDatabase();
//        String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " " +
//                "(" +
//                ContactAbs.ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
//                ContactAbs.CONTACT_ID + " TEXT NOT NULL," +
//                ContactAbs.CONTACT_NAME + " TEXT," +
//                ContactAbs.CONTACT_SURNAME + " TEXT," +
//                ContactAbs.CONTACT_NICKNAME + " TEXT NOT NULL," +
//                ContactAbs.CONTACT_IS_ONLINE + " INTEGER NOT NULL," +
////                ContactAbs.GROUP_CHAT_MEMBERS + " TEXT " +
//                ContactAbs.CONTACT_IMG_URI + " TEXT, " +
//                " UNIQUE (" + ContactAbs.CONTACT_ID + ") ON CONFLICT REPLACE" +
//                ")" +
//                ";";
//        db.execSQL(CREATE_TABLE);
    }
}
