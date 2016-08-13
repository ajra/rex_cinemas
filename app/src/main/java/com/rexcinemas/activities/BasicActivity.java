package com.rexcinemas.activities;

import android.content.Intent;
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
import com.rexcinemas.utils.Common;

public abstract class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.v("On Create", "On Create ---------->  " + getTAG() + " <----------  ");
    }

    @Override
    protected void onDestroy() {
        Log.v("On Destroy", "On Destroy ---------->  " + getTAG() + " <----------  ");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        Log.v("On onStart", "On onStart ---------->  " + getTAG() + " <----------  ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v("On onStart", "On onRestart ---------->  " + getTAG() + " <----------  ");
    }

    public String getTAG() {
        return Common.getTAG(this.getClass());
    }
}
