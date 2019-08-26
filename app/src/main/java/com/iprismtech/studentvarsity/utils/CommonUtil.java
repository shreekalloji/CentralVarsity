package com.iprismtech.studentvarsity.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by volive on 1/30/2019.
 */

public class CommonUtil {
    public static void initializeLocationAlaram(Context context) {
        try {
            Log.e("initializeLocationAam: ", "");
            Calendar calendar = Calendar.getInstance();
            AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
            Intent serviceIntent = new Intent(context, RequestService.class);
            serviceIntent.setAction(RequestService.ACTION_LOC_ALARM);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, serviceIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10 * 1000, pendingIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
