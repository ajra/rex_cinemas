package com.rexcinemas.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rexcinemas.App;
import com.rexcinemas.R;
import com.rexcinemas.ui.navigation.NavigationHomeActivity;
import com.rexcinemas.utils.Common;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeFirstActivity extends BasicActivity {

    @Bind(R.id.nowShowingLayout)
    LinearLayout nowShowingLayout;
    @Bind(R.id.cinemasLayout)
    LinearLayout cinemasLayout;
    @Bind(R.id.checkBookingLayout)
    LinearLayout checkBookingLayout;
    @Bind(R.id.promotions)
    LinearLayout promotions;
    @Bind(R.id.nowShowingTV)
    TextView nowShowingTV;
    @Bind(R.id.cinemasTV)
    TextView cinemasTV;
    @Bind(R.id.promotionTV)
    TextView promotionTV;
    @Bind(R.id.checkBookingTV)
    TextView checkBookingTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_first);
        ButterKnife.bind(this);
        setFonts();
    }

    private void setFonts() {
        nowShowingTV.setTypeface(App.lato_regular);
        cinemasTV.setTypeface(App.lato_regular);
        promotionTV.setTypeface(App.lato_regular);
        checkBookingTV.setTypeface(App.lato_regular);

    }

    @OnClick({R.id.nowShowingLayout, R.id.cinemasLayout, R.id.checkBookingLayout, R.id.promotions})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nowShowingLayout:
                Intent homeIntent = new Intent(this, NavigationHomeActivity.class);
                startActivity(homeIntent);
                break;
            case R.id.cinemasLayout:
                Common.showToastMessage(this,getString(R.string.under_development));
                break;
            case R.id.checkBookingLayout:
                Common.showToastMessage(this,getString(R.string.under_development));
                break;
            case R.id.promotions:
                Common.showToastMessage(this,getString(R.string.under_development));
                break;
        }
    }
}
