package com.iprismtech.studentvarsity.base;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.iprismtech.studentvarsity.activities.SplashScreenActivity;


/**
 * Created by DEVELOPER on 13-Dec-17.
 */

public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final Context myContext;
    private final Class<?> myActivityClass;
    PendingIntent intent;

    public MyExceptionHandler(Context context, Class<?> c) {
        myContext = context;
        myActivityClass = c;
    }

    public void uncaughtException(Thread thread, final Throwable exception) {
        exception.printStackTrace();
        intent = PendingIntent.getActivity(myContext, 0,
                new Intent(myContext, SplashScreenActivity.class), PendingIntent.FLAG_ONE_SHOT);
        /*AlarmManager mgr = (AlarmManager) myContext.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 500, intent);*/
        System.exit(2);
        //android.os.Process.killProcess(android.os.Process.myPid());
    }
}
