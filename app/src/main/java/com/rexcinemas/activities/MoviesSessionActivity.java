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
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rexcinemas.App;
import com.rexcinemas.R;
import com.rexcinemas.adapter.DateAdapter;
import com.rexcinemas.adapter.MovieAdapter;
import com.rexcinemas.api.net.RetroClient;
import com.rexcinemas.api.response.MovieBean;
import com.rexcinemas.api.response.MovieDateBean;
import com.rexcinemas.api.response.MovieListbean;
import com.rexcinemas.api.response.MovieSessionBean;
import com.rexcinemas.ui.now_showing.NowShowingFragment;
import com.rexcinemas.utils.AppLog;
import com.rexcinemas.utils.Common;
import com.rexcinemas.utils.RecyclerUtils;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    List<MovieDateBean> movieDateBeanList = new ArrayList<MovieDateBean>();

    DateAdapter dateAdapter;


    List<MovieListbean> movieList = new ArrayList<>();

    MovieAdapter movieAdapter;
    int selectedPos = 0;

    public static int selectMoviePoss = -1;
    public static int selectSessionPos = -1;

    public String TAG="Session Activity";
    String dateJson = "[{\n" +
            "\t\"theatre_name\": \"Sathyam\",\n" +
            "\t\"movie_date\": \"Sat 09 Jan\",\n" +
            "\t\"movie_list\": [{\n" +
            "\t\t\"movie_id\": \"1091\",\n" +
            "\t\t\"movie_name\": \"Start Wars: The Force Awakens\",\n" +
            "\t\t\"movie_caption\": \"PG\",\n" +
            "\t\t\"movie_image\": \"/images/kabali.jpg\",\n" +
            "\t\t\"movie_session\": [{\n" +
            "\t\t\t\"session_id\": \"sss001\",\n" +
            "\t\t\t\"show_time\": \"12:30 PM\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"session_id\": \"sss002\",\n" +
            "\t\t\t\"show_time\": \"03:30 PM\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"session_id\": \"sss003\",\n" +
            "\t\t\t\"show_time\": \"01:30 PM\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"movie_id\": \"1091\",\n" +
            "\t\t\"movie_name\": \"JOY\",\n" +
            "\t\t\"movie_caption\": \"PG\",\n" +
            "\t\t\"movie_image\": \"/images/kabali.jpg\",\n" +
            "\t\t\"movie_session\": [{\n" +
            "\t\t\t\"session_id\": \"sss001\",\n" +
            "\t\t\t\"show_time\": \"12:30 PM\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"session_id\": \"sss002\",\n" +
            "\t\t\t\"show_time\": \"03:30 PM\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"session_id\": \"sss003\",\n" +
            "\t\t\t\"show_time\": \"01:30 PM\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"movie_id\": \"1091\",\n" +
            "\t\t\"movie_name\": \"The Danish Girl\",\n" +
            "\t\t\"movie_caption\": \"PG\",\n" +
            "\t\t\"movie_image\": \"/images/kabali.jpg\",\n" +
            "\t\t\"movie_session\": [{\n" +
            "\t\t\t\"session_id\": \"sss001\",\n" +
            "\t\t\t\"show_time\": \"12:30 PM\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"session_id\": \"sss002\",\n" +
            "\t\t\t\"show_time\": \"03:30 PM\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"session_id\": \"sss003\",\n" +
            "\t\t\t\"show_time\": \"01:30 PM\"\n" +
            "\t\t}]\n" +
            "\t}]\n" +
            "}, {\n" +
            "\t\"theatre_name\": \"Uthayam\",\n" +
            "\t\"movie_date\": \"Sun 10 Jan\",\n" +
            "\t\"movie_list\": [{\n" +
            "\t\t\"movie_id\": \"1091\",\n" +
            "\t\t\"movie_name\": \"Jungle Book\",\n" +
            "\t\t\"movie_caption\": \"PG\",\n" +
            "\t\t\"movie_image\": \"/images/kabali.jpg\",\n" +
            "\t\t\"movie_session\": [{\n" +
            "\t\t\t\"session_id\": \"sss001\",\n" +
            "\t\t\t\"show_time\": \"12:30 PM\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"session_id\": \"sss002\",\n" +
            "\t\t\t\"show_time\": \"03:30 PM\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"session_id\": \"sss003\",\n" +
            "\t\t\t\"show_time\": \"01:30 PM\"\n" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"movie_id\": \"1091\",\n" +
            "\t\t\"movie_name\": \"Kanchana 2 \",\n" +
            "\t\t\"movie_caption\": \"PG\",\n" +
            "\t\t\"movie_image\": \"/images/kabali.jpg\",\n" +
            "\t\t\"movie_session\": [{\n" +
            "\t\t\t\"session_id\": \"sss001\",\n" +
            "\t\t\t\"show_time\": \"12:30 PM\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"session_id\": \"sss002\",\n" +
            "\t\t\t\"show_time\": \"03:30 PM\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"session_id\": \"sss003\",\n" +
            "\t\t\t\"show_time\": \"01:30 PM\"\n" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "" +
            "\t\t}]\n" +
            "\t}, {\n" +
            "\t\t\"movie_id\": \"1091\",\n" +
            "\t\t\"movie_name\": \"Conjurion 2\",\n" +
            "\t\t\"movie_caption\": \"PG\",\n" +
            "\t\t\"movie_image\": \"/images/kabali.jpg\",\n" +
            "\t\t\"movie_session\": [{\n" +
            "\t\t\t\"session_id\": \"sss001\",\n" +
            "\t\t\t\"show_time\": \"12:30 PM\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"session_id\": \"sss002\",\n" +
            "\t\t\t\"show_time\": \"03:30 PM\"\n" +
            "\t\t}, {\n" +
            "\t\t\t\"session_id\": \"sss003\",\n" +
            "\t\t\t\"show_time\": \"01:30 PM\"\n" +
            "\t\t}]\n" +
            "\t}]\n" +
            "}]";

    public Context context;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_movies_session);
        ButterKnife.bind(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        showDateRv.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManagerMovie = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        showSessionRv.setLayoutManager(layoutManagerMovie);

        context=getApplicationContext();

        setTypeFace();

/*        if(Common.isNetworkAvailable(getApplicationContext()))
            callMovieListService();
        else
            Common.showToastMessage(context,getResources().getString(R.string.dialog_no_inter_message));*/
        setAdapter();


    }

    public void setTypeFace() {
        backBtn.setTypeface(App.lato_regular);
        nextBtn.setTypeface(App.lato_regular);

    }


    public void setAdapter() {
        try {


            selectMoviePoss=-1;
            selectSessionPos=-1;

            GsonBuilder gsonBUilder = new GsonBuilder();
            Gson gson = gsonBUilder.create();
            movieDateBeanList = Arrays.asList(gson.fromJson(dateJson, MovieDateBean[].class));

            movieDateBeanList.get(0).setDateSelected(true);
            selectedPos = 0;

            dateAdapter = new DateAdapter(getApplicationContext(), movieDateBeanList);
            showDateRv.setAdapter(dateAdapter);
            dateAdapter.notifyDataSetChanged();


            setMovieAdapter(0);

            setOntouch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setMovieAdapter(int moviePos) {


      /*  for (MovieListbean bean : movieDateBeanList.get(moviePos).getMovie_list()) {

            for (MovieSessionBean sessionBean) {

            }
        }*/

        movieAdapter = new MovieAdapter(getApplicationContext(), movieDateBeanList.get(moviePos).getMovie_list());

        showSessionRv.setAdapter(movieAdapter);
        movieAdapter.notifyDataSetChanged();

    }

    public void setOntouch() {
        showDateRv.addOnItemTouchListener(
                new RecyclerUtils.RecyclerItemClickListener(getApplicationContext(), new RecyclerUtils.RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // TODO Handle item click


                        if(selectMoviePoss!=-1)
                        {
                            movieDateBeanList.get(selectedPos).getMovie_list().get(selectMoviePoss).getMovie_session().get(selectSessionPos).setSessionSelected(false);

                        }

                        if (dateAdapter.getItem(position).isDateSelected()) {

                        } else {

                            selectMoviePoss = -1;
                            selectSessionPos = -1;
                            movieDateBeanList.get(selectedPos).setDateSelected(false);

                            selectedPos = position;

                            movieDateBeanList.get(position).setDateSelected(true);
                            dateAdapter.notifyDataSetChanged();
                            setMovieAdapter(position);
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
/*
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
*/

                if(selectMoviePoss!=-1) {
                    AppLog.Log(TAG, movieDateBeanList.get(selectedPos).getMovie_list().get(selectMoviePoss).getMovie_id() + " " + movieDateBeanList.get(selectedPos).getMovie_list().get(selectMoviePoss).getMovie_session().get(selectSessionPos).getSession_id());

                    Intent browserIntent = new Intent(this, SeatSelectionActivity.class);

                    startActivity(browserIntent);
                }
                else
                {
                    Common.showToastMessage(getApplicationContext(),"Please select any one movie");
                }
                break;
        }
    }

    private void showDialogue() {
        if(dialog != null)
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

    public void callMovieListService() {
        showDialogue();
        Call<String> response = RetroClient.getRetroClient().getMovieTimes();
        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                dismissDialogue();
                if(response.body() != null)
                {
                    GsonBuilder gsonBUilder = new GsonBuilder();
                    Gson gson = gsonBUilder.create();
                    movieDateBeanList = Arrays.asList(gson.fromJson(response.body(), MovieDateBean[].class));

                    setAdapter();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                dismissDialogue();
            }
        });



    }
}
