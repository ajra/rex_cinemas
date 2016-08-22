package com.rexcinemas.ui.cinemas;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rexcinemas.App;
import com.rexcinemas.R;
import com.rexcinemas.activities.HomeFirstActivity;
import com.rexcinemas.activities.SeatSelectionActivity;
import com.rexcinemas.adapter.DateAdapter;
import com.rexcinemas.adapter.cinemas.CineMovieAdapter;
import com.rexcinemas.api.net.RetroClient;
import com.rexcinemas.api.response.MovieDateBean;
import com.rexcinemas.api.response.MovieListBean;
import com.rexcinemas.api.response.MoviesResponse;
import com.rexcinemas.utils.AppLog;
import com.rexcinemas.utils.Common;
import com.rexcinemas.utils.RecyclerUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CinemasFragment extends Fragment implements View.OnClickListener {
    RecyclerView showDateRv;
    RecyclerView showSessionRv;
    Button backBtn;
    Button nextBtn;
    Spinner theatreSpinner;
    ImageView appLogo;

    List<MovieListBean> movieDateBeanList = new ArrayList<MovieListBean>();
    Set<String> dateSet = new HashSet<>();
    List<MovieDateBean> dateList = new ArrayList<>();
    List<MoviesResponse> moviesResponse = new ArrayList<>();


    int selectedPos = 0;

    public static int selectMoviePoss = -1;
    public static int selectSessionPos = -1;


    public Context context;
    Dialog dialog;
    public String movieName = "";


    public String rexCineamName = "";

    ArrayAdapter<CharSequence> adapter;
    DateAdapter dateAdapter;
    CineMovieAdapter movieAdapter;


    View rootView;


    String TAG = "Cinema Fragment";

    boolean firstTime = false;

    public CinemasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for context fragment
        rootView = inflater.inflate(R.layout.content_movies_session, container, false);
        try {
            context = getActivity();

            rexCineamName = getString(R.string.rex_beach);

            init(rootView);

            setSpinnerAdapter();

            if (Common.isNetworkAvailable(context)) {
                firstTime = true;
                callMovieListService();
            } else
                Common.showToastMessage(context, getResources().getString(R.string.dialog_no_inter_message));

        } catch (Exception e) {
            AppLog.handleException(TAG, e);
        }
        return rootView;
    }

    public void init(View rootView) {


/*
        movieName = getIntent().getStringExtra("movie");
*/
        try {
            showDateRv = (RecyclerView) rootView.findViewById(R.id.showDateRv);
            showSessionRv = (RecyclerView) rootView.findViewById(R.id.showSessionRv);

            nextBtn = (Button) rootView.findViewById(R.id.nextBtn);
            backBtn = (Button) rootView.findViewById(R.id.backBtn);
            appLogo = (ImageView) rootView.findViewById(R.id.appLogo);
            theatreSpinner = (Spinner) rootView.findViewById(R.id.theatreSpinner);

            backBtn.setOnClickListener(this);
            nextBtn.setOnClickListener(this);

            appLogo.setVisibility(View.GONE);
            setTypeFace();
            setLayoutManger();
            createAdapter();

        } catch (Exception e) {
            AppLog.handleException(TAG, e);
        }
    }

    public void setLayoutManger() {
        try {
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            showDateRv.setLayoutManager(layoutManager);

            LinearLayoutManager layoutManagerMovie = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            showSessionRv.setLayoutManager(layoutManagerMovie);
        } catch (Exception e) {
            AppLog.handleException(TAG, e);
        }

    }

    public void createAdapter() {
        adapter = ArrayAdapter.createFromResource(context, R.array.type_sp_array, R.layout.spinner_text);

        adapter.setDropDownViewResource(R.layout.spinner_text);
        dateAdapter = new DateAdapter(context, dateList);

        showDateRv.setAdapter(dateAdapter);

        movieAdapter = new CineMovieAdapter(context, movieDateBeanList);

        showSessionRv.setAdapter(movieAdapter);
    }

    public void setTypeFace() {
        backBtn.setTypeface(App.lato_regular);
        nextBtn.setTypeface(App.lato_regular);

    }

    public void setSpinnerAdapter() throws NullPointerException {
        try {

            theatreSpinner.setAdapter(adapter);

            theatreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                         @Override
                                                         public void onItemSelected(AdapterView<?> parent, View view,
                                                                                    int position, long id) {


                                                             if (position == 0) {
                                                                 rexCineamName = getString(R.string.rex_beach);

                                                                 if (moviesResponse != null && moviesResponse.size() > 0)
                                                                     setCinemasDateList(moviesResponse.get(0));


                                                             } else {
                                                                 rexCineamName = getString(R.string.rex_mac);

                                                                 if (moviesResponse != null && moviesResponse.size() > 0)
                                                                     setCinemasDateList(moviesResponse.get(1));


                                                             }
                                                         }

                                                         @Override
                                                         public void onNothingSelected(AdapterView<?> parent) {
                                                             // TODO Auto-generated method stub

                                                         }
                                                     }

            );
        } catch (Exception e) {
            AppLog.handleException(TAG, e);
        }

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
        try {
            if (movieDateBeanList.size() > 0) {


                movieAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            AppLog.handleException(TAG, e);
        }

    }

    public void setOntouch() {
        try {
            showDateRv.addOnItemTouchListener(
                    new RecyclerUtils.RecyclerItemClickListener(context, new RecyclerUtils.RecyclerItemClickListener.OnItemClickListener() {
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



                                    selectedPos = position;

                                    dateList.get(position).setDateSelected(true);

                                    setMovieList(rexCineamName, dateList.get(position).getMovie_date());
                                    dateAdapter.notifyItemChanged(position);

                                }
                            } catch (Exception e) {
                                AppLog.handleException(TAG, e);
                            }
                        }
                    })
            );
        } catch (Exception e) {
            AppLog.handleException(TAG, e);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backBtn:
                Intent homefirts = new Intent(context, HomeFirstActivity.class);
                startActivity(homefirts);
                break;
            case R.id.nextBtn:
                try {

                    if (selectMoviePoss != -1) {
                        AppLog.Log(TAG, movieDateBeanList.get(selectMoviePoss).getMovie_name() + " " + movieDateBeanList.get(selectMoviePoss).getMovie_session().get(selectSessionPos).getMovie_sessionid());

                        Intent browserIntent = new Intent(context, SeatSelectionActivity.class);
                        browserIntent.putExtra("session_id", movieDateBeanList.get(selectMoviePoss).getMovie_session().get(selectSessionPos).getMovie_sessionid());

                        startActivity(browserIntent);
                    } else {
                        Common.showToastMessage(context, "Please select any one movie");
                    }
                } catch (Exception e) {
                    AppLog.handleException(TAG, e);
                }
                break;
        }
    }


    private void showDialogue() {
        try {
            if (dialog != null)
                dismissDialogue();
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            dialog.setContentView(R.layout.progress);
            dialog.show();
        } catch (Exception e) {
            AppLog.handleException(TAG, e);
        }
    }

    protected void dismissDialogue() {
        dialog.dismiss();
        dialog = null;
    }

    public void callMovieListService() {
        try {
            showDialogue();
            Call<String> response = null;


            response = RetroClient.getRetroClient().getAllCinemas();


            response.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    dismissDialogue();
                    if (response.body() != null) {
                        GsonBuilder gsonBUilder = new GsonBuilder();
                        Gson gson = gsonBUilder.create();
                        moviesResponse = Arrays.asList(gson.fromJson(response.body(), MoviesResponse[].class));
                        firstTime = false;
                        setCinemasDateList(moviesResponse.get(0));
                    }

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    dismissDialogue();
                }
            });

        } catch (Exception e) {
            AppLog.handleException(TAG, e);
        }
    }

    public void setCinemasDateList(MoviesResponse moviesResponse1) {

        dateSet.clear();
        dateList.clear();
        movieDateBeanList.clear();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");


        Set<Date> setDate = new HashSet<>();
        try {

            for (MovieListBean bean : moviesResponse1.getMovie_list()) {

                try {

                    setDate.add(sdf.parse(bean.getMovie_date()));
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


            if (dateList.size() > 0) {

                setAdapter();
                setMovieList(rexCineamName, dateList.get(0).getMovie_date());


            } else {
                if (movieAdapter != null) {
                    movieAdapter.notifyDataSetChanged();
                }

                if (firstTime) {
                    firstTime = false;
                    theatreSpinner.setSelection(1);

                } else {
                    firstTime = false;
                    Common.showToastMessage(context, "No Movies Found ");
                }


            }
        } catch (Exception e) {
            AppLog.handleException("date for", e);
        }


    }

    public void setMovieList(String rexCinemas, String dateValue) {
        try {
            movieDateBeanList.clear();
            int cinemaPos = 0;
            if (rexCineamName.equalsIgnoreCase(getString(R.string.rex_beach))) {
                cinemaPos = 0;


            } else {
                cinemaPos = 1;
            }
            for (int i = 0; i < moviesResponse.get(cinemaPos).getMovie_list().size(); i++) {


                MovieListBean movieListBean = moviesResponse.get(cinemaPos).getMovie_list().get(i);

                if (dateValue.equalsIgnoreCase(movieListBean.getMovie_date())) {

/*
                        movieListBean.setMovie_name(movieName);
*/


                    movieDateBeanList.add(movieListBean);


                }


            }


            AppLog.Log("movie mac", movieDateBeanList.size() + "");
            setMovieAdapter();


        } catch (Exception e) {
            AppLog.handleException("mov", e);
        }


    }


}

