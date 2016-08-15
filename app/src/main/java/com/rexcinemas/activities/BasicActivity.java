package com.rexcinemas.activities;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.rexcinemas.R;
import com.rexcinemas.api.receivers.NetworkChangeReceiver;
import com.rexcinemas.utils.Common;
import com.rexcinemas.utils.NoConnectionHelper;

public abstract class BasicActivity extends AppCompatActivity {

    private NetworkChangeReceiver receiver;
    private boolean stoppedActivity;
    IntentFilter filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (NoConnectionHelper.getInstance() != null) {
            NoConnectionHelper.getInstance().unHideNoConViewIfHidden();
        }
        Log.v("On Create", "On Create ---------->  " + getTAG() + " <----------  ");
    }

    @Override
    protected void onDestroy() {
        Log.v("On Destroy", "On Destroy ---------->  " + getTAG() + " <----------  ");
        super.onDestroy();
       // unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        stoppedActivity = false;
        if (NoConnectionHelper.getInstance() != null) {
            NoConnectionHelper.getInstance().setCurrentActivity(this);
        }
       /* //monitor network status
        filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver(this);
        registerReceiver(receiver, filter);*/
        Log.v("On onResume", "On onResume ---------->  " + getTAG() + " <----------  ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v("On onStart", "On onStart ---------->  " + getTAG() + " <----------  ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        stoppedActivity = true;
        Log.v("On onStart", "On onStart ---------->  " + getTAG() + " <----------  ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v("On onStart", "On onRestart ---------->  " + getTAG() + " <----------  ");
    }

    @Override
    protected void onPause() {
        super.onPause();
     //   unregisterReceiver(receiver);
    }

    public String getTAG() {
        return Common.getTAG(this.getClass());
    }

    public boolean isStoppedActivity() {
        return stoppedActivity;
    }
}
