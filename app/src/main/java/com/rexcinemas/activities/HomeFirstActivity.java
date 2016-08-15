package com.rexcinemas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.rexcinemas.R;
import com.rexcinemas.ui.navigation.NavigationHomeActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFirstActivity extends AppCompatActivity {

    @Bind(R.id.nowShowingLayout)
    LinearLayout nowShowingLayout;
    @Bind(R.id.cinemasLayout)
    LinearLayout cinemasLayout;
    @Bind(R.id.checkBookingLayout)
    LinearLayout checkBookingLayout;
    @Bind(R.id.promotions)
    LinearLayout promotions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_first);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.nowShowingLayout, R.id.cinemasLayout, R.id.checkBookingLayout, R.id.promotions})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nowShowingLayout:
                Intent homeIntent = new Intent(this, NavigationHomeActivity.class);
                startActivity(homeIntent);
                finish();
                break;
            case R.id.cinemasLayout:
                break;
            case R.id.checkBookingLayout:
                break;
            case R.id.promotions:
                break;
        }
    }
}
