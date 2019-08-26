package com.iprismtech.studentvarsity.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class DownloadService extends Service {

    static String pointName = "Point B";
    static final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d("Service", pointName);
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("Service", "Point A");
        pointName = "Point C";
        new Thread(runnable).start();
        return START_STICKY;
    }
}
