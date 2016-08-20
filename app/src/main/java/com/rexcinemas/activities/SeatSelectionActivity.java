package com.rexcinemas.activities;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.rexcinemas.R;
import com.rexcinemas.utils.AppLog;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SeatSelectionActivity extends AppCompatActivity {


    @Bind(R.id.webView)
    WebView webView;
    public String TAG="SEAT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);
        ButterKnife.bind(this);


        webView.getSettings().setJavaScriptEnabled(true);
        AppLog.Log(TAG,"http://rexcinemas.com.sg/web/booking.php/session_id="+getIntent().getStringExtra("session_id"));
        webView.loadUrl("http://rexcinemas.com.sg/web/booking.php/session_id="+getIntent().getStringExtra("session_id"));
    }
}
