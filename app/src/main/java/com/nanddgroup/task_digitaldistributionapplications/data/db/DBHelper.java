package com.nanddgroup.task_digitaldistributionapplications.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nikita on 05.01.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "TemporaryDb";
    private static int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

    public static void deleteHistory(Context context) {
        context.deleteDatabase(DB_NAME);
    }
}
