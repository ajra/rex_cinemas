package com.rexcinemas.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.rexcinemas.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SeatSelectionActivity extends AppCompatActivity {


    @Bind(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selection);
        ButterKnife.bind(this);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://rexcinemas.com.sg/web/booking.php");
    }
}
