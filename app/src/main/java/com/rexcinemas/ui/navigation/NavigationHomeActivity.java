package com.rexcinemas.ui.navigation;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.rexcinemas.R;
import com.rexcinemas.activities.BasicActivity;
import com.rexcinemas.ui.check_booking.CheckBookingFragment;
import com.rexcinemas.ui.cinemas.CinemasFragment;
import com.rexcinemas.ui.now_showing.NowMainShowingFragment;
import com.rexcinemas.ui.promotion.PromotionFragment;
import com.rexcinemas.utils.Common;
import com.rexcinemas.utils.ConnectionService;

import java.util.ArrayList;

public class NavigationHomeActivity extends BasicActivity implements View.OnClickListener {

    public static boolean bl_isNavigationOpened =  false;
    public static boolean CATEGORY_ENABLED_DISABLED = false;
    public static boolean showingMenu = false;
    FrameLayout scrollRl;
    RelativeLayout outsideRl;
    RecyclerView navigationRecyclerView;
    ListView navigationListView;
    ImageView headerMenu;
    LinearLayoutManager linearLayoutManager;
    ArrayList<HomeMenu> homeMenuList;
    public static String selectedTabPosition;
    public static int currentMenu = 0;
    FragmentManager fragmentManager;
    Fragment fragment;
    Context context;
    private int[] menuIconArray = {
            R.drawable.now_showing,
            R.drawable.cinemas,
            R.drawable.check_booking,
            R.drawable.promotion
    };
    private String[] menuNameArray = {
            "Now Showing","Cinemas","Check booking","Promotion"
    };

    Intent mConnectionServiceIntent;
    private static boolean sServiceIsRunning = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_home2);
        homeMenuList = new ArrayList<>();
        context = this;
        for(int i=0; i<menuIconArray.length;i++)
        {
            HomeMenu homeMenu = new HomeMenu(menuNameArray[i],menuIconArray[i]);
            homeMenuList.add(homeMenu);
        }
        outsideRl = (RelativeLayout) findViewById(R.id.rl_outside);
        scrollRl = (FrameLayout) findViewById(R.id.rl_scroll);
        headerMenu = (ImageView) findViewById(R.id.iv_headermenu);
        navigationRecyclerView = (RecyclerView) findViewById(R.id.navigation_recycler_view);

        navigationListView = (ListView) findViewById(R.id.navList);
        headerMenu.setOnClickListener(NavigationHomeActivity.this);

        //list view
        NavDrawerListAdapter navDrawerListAdapter = new NavDrawerListAdapter(context,homeMenuList);
        navigationListView.setAdapter(navDrawerListAdapter);
        navigationListView.setOnItemClickListener(new SlideMenuClickListener());
        //Recycler view
        MenuAdapter menuAdapter = new MenuAdapter(context,homeMenuList);
        linearLayoutManager = new LinearLayoutManager(this);
        navigationRecyclerView.setLayoutManager(linearLayoutManager);
        navigationRecyclerView.setHasFixedSize(true);
        navigationRecyclerView.setAdapter(menuAdapter);
        scrollRl.setVisibility(View.VISIBLE);
        scrollRl.getLayoutParams().width = (int) (Common.getScreenWidth(NavigationHomeActivity.this) * ((float) 80 / 100));
        scrollRl.setX(-Common.getScreenWidth(NavigationHomeActivity.this) * ((float) 80 / 100));
        displayView(0);
        // syncing service
        mConnectionServiceIntent = new Intent(this, ConnectionService.class);
        startSyncService();
    }

    private void startSyncService() {
        if (!sServiceIsRunning) {
            sServiceIsRunning = true;
            startService(mConnectionServiceIntent);
        }
    }

    private void stopSyncService() {
        if (sServiceIsRunning) {
            sServiceIsRunning = false;
            stopService(mConnectionServiceIntent);
        }
    }


    /**
     * Slide menu item click listener
     */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            selectedTabPosition = "" + position;
            currentMenu = position;
            displayView(position);
        }

    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        Intent intent = null;
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new NowMainShowingFragment();
                if (selectedTabPosition == null) {
                    selectedTabPosition = "0";
                }

               /* NowMainShowingFragment.newInstance(selectedTabPosition);
                Bundle args = new Bundle();
                args.putString("selectedTabPosition", selectedTabPosition);
                fragment.setArguments(args);*/
                break;
            case 1:
                fragment = new CinemasFragment();
                break;
            case 2:
                fragment = new CheckBookingFragment();
                break;
            case 3:
                fragment = new PromotionFragment();
                break;
            default:
                break;
        }

        if (fragment != null) {

            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }
        menuChanges();
    }



    @Override
    public void onClick(View view) {
        if(view == headerMenu) {
            menuChanges();
        }
    }

    private void menuChanges() {
        if (!showingMenu) {
            //Disable Swipe
            bl_isNavigationOpened = true;
            showingMenu = true;
            outsideRl.animate().translationX((Common.getScreenWidth(this) * (float) 80 / 100));
            scrollRl.animate().translationX(0).setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }
            });


        } else {
            if (CATEGORY_ENABLED_DISABLED) {

                bl_isNavigationOpened = false;
                showingMenu = false;
                outsideRl.animate().translationX(0).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        if(CATEGORY_ENABLED_DISABLED)
                        {
                            CATEGORY_ENABLED_DISABLED = false;
                        }
                    }
                });
                scrollRl.animate().translationX(-(Common.getScreenWidth(this) * (float) 80 / 100));
            } else {
                bl_isNavigationOpened = false;
                showingMenu = false;
                outsideRl.animate().translationX(0);
                scrollRl.animate().translationX(-(Common.getScreenWidth(this) * (float) 80 / 100));
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopSyncService();
    }
}
