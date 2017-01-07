package com.nanddgroup.task_digitaldistributionapplications.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Nikita on 07.01.2017.
 */

public class Utility {
    public static String millisToDate(Integer millis){

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        long milliSeconds= Long.parseLong(millis.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
