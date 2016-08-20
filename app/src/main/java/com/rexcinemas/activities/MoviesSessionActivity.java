package com.rexcinemas.activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rexcinemas.App;
import com.rexcinemas.R;
import com.rexcinemas.adapter.DateAdapter;
import com.rexcinemas.adapter.MovieAdapter;
import com.rexcinemas.api.net.RetroClient;
import com.rexcinemas.api.response.Cinemabean;
import com.rexcinemas.api.response.MovieBean;
import com.rexcinemas.api.response.MovieDateBean;
import com.rexcinemas.api.response.MovieListBean;
import com.rexcinemas.api.response.MovieSessionBean;
import com.rexcinemas.api.response.MoviesResponse;
import com.rexcinemas.ui.now_showing.NowShowingFragment;
import com.rexcinemas.utils.AppLog;
import com.rexcinemas.utils.Common;
import com.rexcinemas.utils.RecyclerUtils;

import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesSessionActivity extends AppCompatActivity {

    @Bind(R.id.showDateRv)
    RecyclerView showDateRv;
    @Bind(R.id.showSessionRv)
    RecyclerView showSessionRv;
    @Bind(R.id.backBtn)
    Button backBtn;
    @Bind(R.id.nextBtn)
    Button nextBtn;
    @Bind(R.id.theatreSpinner)
    Spinner theatreSpinner;

    List<MovieListBean> movieDateBeanList = new ArrayList<MovieListBean>();


    Set<String> dateSet = new HashSet<>();
    List<MovieDateBean> dateList = new ArrayList<>();


    DateAdapter dateAdapter;


    MovieAdapter movieAdapter;
    int selectedPos = 0;

    public static int selectMoviePoss = -1;
    public static int selectSessionPos = -1;

    public String TAG = "Session Activity";

    public Context context;
    Dialog dialog;
    public String pageType = "";
    public String movieName = "";


    public String rexCineamName = "";

    ArrayAdapter<CharSequence> adapter;


    MoviesResponse moviesResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_movies_session);
        ButterKnife.bind(this);
        context = getApplicationContext();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        showDateRv.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManagerMovie = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        showSessionRv.setLayoutManager(layoutManagerMovie);
        init();
        adapter = ArrayAdapter.createFromResource(this, R.array.type_sp_array, R.layout.spinner_text);

        adapter.setDropDownViewResource(R.layout.spinner_text);

        movieAdapter = new MovieAdapter(getApplicationContext(), movieDateBeanList);

        showSessionRv.setAdapter(movieAdapter);

        dateAdapter = new DateAdapter(getApplicationContext(), dateList);

        showDateRv.setAdapter(dateAdapter);
        rexCineamName = getString(R.string.rex_beach);

        setSpinnerAdapter();


        if (Common.isNetworkAvailable(getApplicationContext()))
            callMovieListService();
        else
            Common.showToastMessage(context, getResources().getString(R.string.dialog_no_inter_message));


    }

    public void init() {
        context = getApplicationContext();
        pageType = getIntent().getStringExtra("page");
        if (pageType.equalsIgnoreCase("now")) {
            movieName = getIntent().getStringExtra("movie");
        }


        setTypeFace();

    }

    public void setTypeFace() {
        backBtn.setTypeface(App.lato_regular);
        nextBtn.setTypeface(App.lato_regular);

    }

    public void setSpinnerAdapter() {

        theatreSpinner.setAdapter(adapter);

        theatreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                     @Override
                                                     public void onItemSelected(AdapterView<?> parent, View view,
                                                                                int position, long id) {


                                                         if (position == 0) {
                                                             rexCineamName = getString(R.string.rex_beach);

                                                             if (moviesResponse != null)
                                                                 setCinemasDateList(moviesResponse);


                                                         } else {
                                                             if (moviesResponse != null)
                                                                 rexCineamName = getString(R.string.rex_mac);

                                                             setCinemasDateList(moviesResponse);


                                                         }
                                                     }

                                                     @Override
                                                     public void onNothingSelected(AdapterView<?> parent) {
                                                         // TODO Auto-generated method stub

                                                     }
                                                 }

        );

    }

    public void setAdapter() {
        try {


            selectMoviePoss = -1;
            selectSessionPos = -1;

            dateList.get(0).setDateSelected(true);
            selectedPos = 0;


            dateAdapter.notifyDataSetChanged();


            setOntouch();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setMovieAdapter() {
        if (movieDateBeanList.size() > 0) {


            movieAdapter.notifyDataSetChanged();
        }

    }

    public void setOntouch() {
        showDateRv.addOnItemTouchListener(
                new RecyclerUtils.RecyclerItemClickListener(getApplicationContext(), new RecyclerUtils.RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click


                        try {
                            if (selectedPos == position) {

                            } else {

                                selectMoviePoss = -1;
                                selectSessionPos = -1;

                                dateList.get(selectedPos).setDateSelected(false);
                                dateAdapter.notifyItemChanged(selectedPos);

                                System.out.println("pos---" + position + dateList.size() + dateList.get(position).getMovie_date());


                                selectedPos = position;

                                dateList.get(position).setDateSelected(true);
                                setMovieList(rexCineamName, dateList.get(position).getMovie_date());


                                dateAdapter.notifyItemChanged(position);

/*
                                dateAdapter.notifyItemChanged(position);
*/
                            }
                        } catch (Exception e) {
                            AppLog.handleException("onTouch", e);
                        }
                    }
                })
        );

    }


    @OnClick({R.id.backBtn, R.id.nextBtn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backBtn:
                finish();
                break;
            case R.id.nextBtn:


                if (selectMoviePoss != -1) {
                    AppLog.Log(TAG, movieDateBeanList.get(selectMoviePoss).getMovie_name() + " " + movieDateBeanList.get(selectMoviePoss).getMovie_session().get(selectSessionPos).getMovie_sessionid());

                    Intent browserIntent = new Intent(this, SeatSelectionActivity.class);
                    browserIntent.putExtra("session_id", movieDateBeanList.get(selectMoviePoss).getMovie_session().get(selectMoviePoss).getMovie_sessionid());

                    startActivity(browserIntent);
                } else {
                    Common.showToastMessage(getApplicationContext(), "Please select any one movie");
                }
                break;
        }
    }

    private void showDialogue() {
        if (dialog != null)
            dismissDialogue();
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.progress);
        dialog.show();
    }

    protected void dismissDialogue() {
        dialog.dismiss();
        dialog = null;
    }

    public void callMovieListService() {
        showDialogue();
        Call<String> response = null;

        if (pageType.equalsIgnoreCase("now")) {
            response = RetroClient.getRetroClient().getMovieTimes(movieName);


        } else {
            response = RetroClient.getRetroClient().getAllCinemas();

        }
        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dismissDialogue();
                if (response.body() != null) {
                    GsonBuilder gsonBUilder = new GsonBuilder();
                    Gson gson = gsonBUilder.create();
                    if (pageType.equalsIgnoreCase("now")) {
                        moviesResponse = gson.fromJson(response.body(), MoviesResponse.class);


                        setCinemasDateList(moviesResponse);


                    } else {

                    }

                    //                     MoviesResponse moviesResponse= Arrays.asList(gson.fromJson(response.body(), MovieDateBean.class));


                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dismissDialogue();
            }
        });


    }

    public void setCinemasDateList(MoviesResponse moviesResponse) {
        dateSet.clear();
        dateList.clear();
        movieDateBeanList.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        Set<Date> setDate = new HashSet<>();
        try {
            for (MovieListBean bean : moviesResponse.getMovie_list()) {
                if (rexCineamName.equalsIgnoreCase(bean.getCinema_name()))
/*
                dateSet.add(bean.getMovie_date());
*/

                    try {

                        setDate.add(sdf.parse(bean.getMovie_date()));
                        System.out.println("sdf" + sdf.parse(bean.getMovie_date()));
                    } catch (ParseException e) {
                        AppLog.handleException(TAG, e);

                    }

            }


            if (setDate.size() > 0) {
                List<Date> ex = new ArrayList<Date>(setDate);

                Collections.sort(ex);


                if (ex != null && ex.size() > 0) {
                    for (Date dtString : ex) {
                        try {

                            dateList.add(new MovieDateBean(sdf.format(dtString) + "", false));

                        } catch (Exception e) {
                            AppLog.handleException(TAG, e);

                        }


                        AppLog.Log("date", dateList.size() + "");


                    }
                }

            }
/*
            AppLog.Log("date", dateList.size() + "");
*/


            if (dateList.size() > 0) {
                setAdapter();
                setMovieList(rexCineamName, dateList.get(0).getMovie_date());

            } else {
                if (movieAdapter != null) {
                    movieAdapter.notifyDataSetChanged();
                }

                Common.showToastMessage(getApplicationContext(), "No Movies Found ");


            }
        } catch (Exception e) {
            AppLog.handleException("date for", e);
        }


    }

    public void setMovieList(String rexCinemas, String dateValue) throws NullPointerException {
        try {
            movieDateBeanList.clear();
            movieAdapter.notifyDataSetChanged();

            for (int i = 0; i < moviesResponse.getMovie_list().size(); i++) {


                MovieListBean movieListBean = moviesResponse.getMovie_list().get(i);

                if (dateValue.equalsIgnoreCase(movieListBean.getMovie_date())) {

                    if (movieListBean.getCinema_name().equalsIgnoreCase(rexCinemas)) {
                        movieListBean.setMovie_name(movieName);


                        movieDateBeanList.add(movieListBean);


                    }

                }


            /*dialog.dismiss();*/

            }

            AppLog.Log("movie mac", movieDateBeanList.size() + "");

            setMovieAdapter();

        } catch (Exception e) {
            AppLog.handleException("mac list", e);
        }


    }


}

