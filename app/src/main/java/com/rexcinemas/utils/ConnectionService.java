package com.rexcinemas.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.rexcinemas.BuildConfig;
import com.rexcinemas.activities.BasicActivity;
import com.rexcinemas.ui.navigation.NavigationHomeActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SRadhakrishnan on 15-08-2016.
 */
public class ConnectionService extends Service {

    public final static long SYNC_TIME_PERIOD = 5000;

    public final static long NO_CONNECTION_TIMER_PERIOD = 1000;

    public final static long REMINDER_TIMER_PERIOD = 2000;

    /**
     * How many dirty syncs until we do an update from server 240 times = 20 minutes after last
     * dirty object synced 12 times = 1 minute (good for testing)
     */
    public final static int SYNC_UPDATE_COUNTER = BuildConfig.DEBUG ? 12 : 240;

    private static final String TAG = ConnectionService.class.getSimpleName();

    public static String sCurrentReminderTime = "";

    private static Timer sTimer = new Timer();

    private static Timer sNoConTimer = new Timer();

    private static Timer sReminderTimer = new Timer();


    private static NoConTask sNoConSyncDataTask = null;

    private Context mContext;

    // Counter for each upload sync, when counter reaches a certain #, perform
    // an upload sync
    private int mDownloadSyncCounter;

    @Override
    public void onCreate() {
        super.onCreate();
        // When app starts or resumes, we'll sync right away
        mDownloadSyncCounter = SYNC_UPDATE_COUNTER;
        if (sNoConSyncDataTask == null) {
            sNoConSyncDataTask = new NoConTask();
        }

        mContext = this;
        sNoConTimer.schedule(sNoConSyncDataTask, 0, NO_CONNECTION_TIMER_PERIOD);
    }

    @Override
    public void onDestroy() {
        int numPurged = sTimer.purge();
        sNoConSyncDataTask.cancel();
        sNoConTimer.purge();
        sReminderTimer.purge();
        sNoConSyncDataTask = null;
        super.onDestroy();
    }

    ;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void checkConnection() {
        if (Common.isOnline(mContext) && appInForeground()) {
            NoConnectionHelper.getInstance().hideNoConnectionView(true);
        } else if (appInForeground()) {
            NoConnectionHelper.getInstance().showNoConnectionView();
        }
    }

    private boolean appInForeground() {
        boolean canShow = NoConnectionHelper.getInstance() != null
                && NoConnectionHelper.getInstance().getCurrentActivity() != null;
        if (canShow
                && NoConnectionHelper.getInstance()
                .getCurrentActivity() instanceof NavigationHomeActivity) {
            NavigationHomeActivity ma = (NavigationHomeActivity) NoConnectionHelper.getInstance()
                    .getCurrentActivity();
            if (ma.isStoppedActivity()) {
                canShow = false;
            }
        } else if (canShow
                && NoConnectionHelper.getInstance().getCurrentActivity() instanceof BasicActivity) {
            BasicActivity ba = (BasicActivity) NoConnectionHelper.getInstance().getCurrentActivity();
            if (ba.isStoppedActivity()) {
                canShow = false;
            }
        }
        return canShow;
    }

    private class NoConTask extends TimerTask {

        @Override
        public void run() {
            try {
                checkConnection();
            } catch (Exception e) {
                Log.e(TAG, "Exception checking connection");
            }
        }
    }
}
