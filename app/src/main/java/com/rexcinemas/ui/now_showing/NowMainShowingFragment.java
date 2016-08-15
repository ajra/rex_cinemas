package com.rexcinemas.ui.now_showing;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rexcinemas.R;
import com.rexcinemas.api.response.MovieBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NowMainShowingFragment extends Fragment implements NowShowingFragment.OnFragmentInteractionListener {
    /*
        @Bind(R.id.showViewPager)
    */
    ViewPager showViewPager;

    ShowPagerAdapter showAdapter;
    List<Integer> showList = new ArrayList<>();
    public static int selectedShow = 0;

    TextView textsamle;
    View rootView;
    Context context;


    public static List<MovieBean> nowShowingList = new ArrayList<MovieBean>();


    String moviePhp = "[{\"movie_id\":\"1091\",\"movie_name\":\"Kabali\",\"movie_caption\":\"PG\",\"movie_ratinng\":\"4.5\",\"movie_image\":\"http:\\/\\/rexcinemas.com.sg\\/web\\/images\\/kabali.jpg\"},{\"movie_id\":\"1092\",\"movie_name\":\"Iraivi\",\"movie_caption\":\"PG\",\"movie_ratinng\":\"4\",\"movie_image\":\"http:\\/\\/rexcinemas.com.sg\\/web\\/images\\/kabali.jpg\"},{\"movie_id\":\"1093\",\"movie_name\":\"Theri\",\"movie_caption\":\"PG\",\"movie_ratinng\":\"4.5\",\"movie_image\":\"http:\\/\\/rexcinemas.com.sg\\/web\\/images\\/kabali.jpg\"}]";


    public NowMainShowingFragment() {
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
        ButterKnife.bind(rootView);
        context = getActivity();


        showViewPager = (ViewPager) rootView.findViewById(R.id.showViewPager);

/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        showAdapter = new ShowPagerAdapter(getFragmentManager(), getActivity());


        callMovieService();

        return rootView;
    }


    public void setupViewPager() {
        showViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

/*                if (byType.equalsIgnoreCase(getString(R.string.byCatg))) {
                    CategoryId = mCategoryId.get(position);
                } else if (byType.equalsIgnoreCase(getString(R.string.byBrand))) {
                    brandId = mBrandId.get(position);
                }*/
                selectedShow = showList.get(position);

                showViewPager.setCurrentItem(position);

                System.out.println("pod" + position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


    }


    public void callMovieService() {
        GsonBuilder gsonBUilder = new GsonBuilder();
        Gson gson = gsonBUilder.create();
        nowShowingList = Arrays.asList(gson.fromJson(moviePhp, MovieBean[].class));


        for (int i = 0; i < nowShowingList.size(); i++) {
            showList.add(i);
        }
        for (int i = 0; i < showList.size(); i++) {
            showAdapter.addFragment(new NowShowingFragment(), i + "");
        }
        showViewPager.setAdapter(showAdapter);
        showViewPager.setCurrentItem(0);
        setupViewPager();
    }

    @Override
    public void onFragmentCreated(int number) {
    }

    static class ShowPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();
        Context context;


        public ShowPagerAdapter(FragmentManager fm, Context context) {

            super(fm);
            //   this.prefHelper = new PreferenceHelper(context);
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
