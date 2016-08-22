package com.rexcinemas.api.receivers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import com.rexcinemas.utils.AppLog;
import com.rexcinemas.utils.Common;

public class NetworkChangeReceiver  extends BroadcastReceiver {
    private static final String LOG_TAG = "CheckNetworkStatus";

    static Activity act;
    public NetworkChangeReceiver(Activity activityDashBoard) {
        act = activityDashBoard;
    }


    @Override
    public void onReceive(final Context context, Intent intent) {
        AlertDialog alertDialog = null;
        Log.v(LOG_TAG, "Receieved notification about network status");
        if(!Common.isNetworkAvailable(context)) {
            try {
                alertDialog = new AlertDialog.Builder(context).create();

                alertDialog.setTitle("Info");
                alertDialog.setMessage("Internet not available, Cross check your internet connectivity and try again");
                alertDialog.setIcon(android.R.drawable.ic_dialog_alert);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent1 = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                        context.startActivity(intent1);
                    }
                });

                alertDialog.show();
            } catch (Exception e) {
                AppLog.handleException(LOG_TAG, e);
            }
        }
    }
}
