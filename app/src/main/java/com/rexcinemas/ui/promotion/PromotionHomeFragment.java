package com.rexcinemas.ui.promotion;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rexcinemas.R;
import com.rexcinemas.api.net.RetroClient;
import com.rexcinemas.api.response.MovieBean;
import com.rexcinemas.ui.now_showing.NowShowingFragment;
import com.rexcinemas.utils.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */

public class PromotionHomeFragment extends Fragment implements PromotionsFragment.OnFragmentInteractionListener {
    Context context;
    View rootView;
    ViewPager promotionPager;
    PromotionsPagerAdapter promotionsAdapter;
    List<Integer> promoPositionList = new ArrayList<>();
    protected static List<MovieBean> promotionList = new ArrayList<>();

    public static int selectedPromotion = 0;
    Dialog dialog;


    public PromotionHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_main_now_showing, container, false);
        init(rootView);
      /*  if (Common.isNetworkAvailable(context))
            callMovieService();
        else
            Common.showToastMessage(context, getResources().getString(R.string.dialog_no_inter_message));*/

        for (int i = 0; i < 3; i++) {
            promoPositionList.add(i);
            promotionsAdapter.addFragment(new PromotionsFragment(), i + "");
        }
        promotionPager.setAdapter(promotionsAdapter);
        promotionPager.setCurrentItem(0);
        setupViewPager();

        return rootView;
    }

    public void init(View rootView) {
        context = getActivity();
        promotionPager = (ViewPager) rootView.findViewById(R.id.showViewPager);
        promotionsAdapter = new PromotionsPagerAdapter(getFragmentManager(), getActivity());


    }

    public void setupViewPager() {
        promotionPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectedPromotion = promoPositionList.get(position);
                promotionPager.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }

    private void showDialogue() {
        if (dialog != null)
            dismissDialogue();
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.progress);
        dialog.show();
    }

    protected void dismissDialogue() {
        dialog.dismiss();
        dialog = null;
    }

    public void callMovieService() {
        showDialogue();
        Call<String> response = RetroClient.getRetroClient().getNowShowingMovies();
        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dismissDialogue();
                if (response.body() != null) {
                    GsonBuilder gsonBUilder = new GsonBuilder();
                    Gson gson = gsonBUilder.create();
                    promotionList = Arrays.asList(gson.fromJson(response.body(), MovieBean[].class));
                    for (int i = 0; i < promotionList.size(); i++) {
                        promoPositionList.add(i);
                        promotionsAdapter.addFragment(new NowShowingFragment(), i + "");
                    }
                    promotionPager.setAdapter(promotionsAdapter);
                    promotionPager.setCurrentItem(0);
                    setupViewPager();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dismissDialogue();
            }
        });


    }

    @Override
    public void onFragmentCreated(int number) {
    }

    static class PromotionsPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();
        Context context;


        public PromotionsPagerAdapter(FragmentManager fm, Context context) {

            super(fm);
            this.context = context;
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentTitles.add(title);
            mFragments.add(fragment);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {


            return mFragmentTitles.get(position);
        }


    }
}


