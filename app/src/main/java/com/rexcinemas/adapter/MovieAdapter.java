package com.rexcinemas.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rexcinemas.App;
import com.rexcinemas.R;
import com.rexcinemas.activities.MoviesSessionActivity;
import com.rexcinemas.api.response.MovieListBean;
import com.rexcinemas.api.response.MovieSessionBean;
import com.rexcinemas.utils.AppLog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {


    Context context;
    List<MovieListBean> movieList;

    public MovieAdapter(Context context, List<MovieListBean> movieList) {
        this.context = context;
        this.movieList = movieList;


    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie_list, viewGroup, false);
        return new MovieViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int i) {

        try {

            final MovieListBean movieListbean = movieList.get(i);
/*
            String movieImageUrl = movieListbean.getMovie_url().replaceAll("/", "//");
*/

            holder.movieNameText.setText(movieListbean.getMovie_name());


/*
            Picasso.with(context)
                    .load(Uri.parse("http://rexcinemas.com.sg//web//" + movieImageUrl)).placeholder(R.drawable.thumb01).error(R.drawable.thumb01)
                    .into(holder.movieThumbImage);
*/

            holder.movieNameText.setTypeface(App.lato_bold);

            holder.sessionGridView.setNumColumns(3);

/*
            movieListbean.getMovie_session()
*/

            if (movieListbean.getMovie_session().size() > 1) {
                for (int j = 0; j < movieListbean.getMovie_session().size(); j++) {


                    movieListbean.getMovie_session().get(j).setSessionSelected(false);
                }
            } else {
                movieListbean.getMovie_session().get(0).setSessionSelected(false);

            }
            GridSessionAdapter sessionAdapter = new GridSessionAdapter(context, i, movieListbean.getMovie_session());
            holder.sessionGridView.setAdapter(sessionAdapter);

            int totalHeight = 0;
            for (int size = 0; size < sessionAdapter.getCount(); size++) {
                LinearLayout linearLayout = (LinearLayout) sessionAdapter.getView(
                        size, null, holder.sessionGridView);

/*
                RelativeLayout rlLayout = (RelativeLayout) relativeLayout.getChildAt(0);
*/

                linearLayout.measure(0, 0);
                linearLayout.measure(0, 0);

                totalHeight += linearLayout.getMeasuredHeight();

            }


            int rowCunt = sessionAdapter.getCount();


            ViewGroup.LayoutParams params = holder.sessionGridView.getLayoutParams();
           /* if (params != null) {

                if (rowCunt == 1) {
                    params.height = totalHeight;
                } else {
                    params.height = totalHeight;
                }

            }*/


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public MovieListBean getItem(int position) {

        return movieList.get(position);
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        public TextView movieNameText;

        ImageView movieThumbImage;
        GridView sessionGridView;


        public MovieViewHolder(View v) {
            super(v);

            movieThumbImage = (ImageView) v.findViewById(R.id.movieThumbImage);

            movieNameText = (TextView) v.findViewById(R.id.movieNameText);

            sessionGridView = (GridView) v.findViewById(R.id.sessionGridView);


        }
    }


    public class GridSessionAdapter extends BaseAdapter {

        private Context mContext;
        private List<MovieSessionBean> sessionList = new ArrayList();
        private int checkedPositions = -1;
        int moviePos, sessionListPosition;

        public String TAG = "Session";

        public GridSessionAdapter(Context context, int moviePos, List<MovieSessionBean> sessionListValues) {
            mContext = context;
            this.sessionList = sessionListValues;
            this.moviePos = moviePos;

        }


        @Override
        public int getCount() {
            return sessionList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int arg0) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            sessionListPosition = position;

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_movie_session, null);
                holder = new ViewHolder();

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.sessionTimeBtn = (Button) convertView.findViewById(R.id.sessionTimeBtn);
            holder.sessionTimeBtn.setTypeface(App.lato_regular);
            holder.sessionTimeBtn.setText(convertTimeTO12Hour(sessionList.get(position).getMovie_time()));







            holder.sessionTimeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {


                        if (MoviesSessionActivity.selectMoviePoss == -1) {
                            MoviesSessionActivity.selectSessionPos = position;
                            MoviesSessionActivity.selectMoviePoss = moviePos;
                            sessionList.get(position).setSessionSelected(true);

                        } else if (MoviesSessionActivity.selectMoviePoss == moviePos) {
                            AppLog.Log(TAG, "same movie selected" + moviePos);


                            if (sessionList.get(position).isSessionSelected()) {
                                sessionList.get(position).setSessionSelected(false);
                                MoviesSessionActivity.selectSessionPos = -1;
                                MoviesSessionActivity.selectMoviePoss = -1;
                            } else {
                                sessionList.get(MoviesSessionActivity.selectSessionPos).setSessionSelected(false);
                                sessionList.get(position).setSessionSelected(true);
                                MoviesSessionActivity.selectSessionPos = position;


                            }

                        } else if (MoviesSessionActivity.selectMoviePoss != moviePos) {
                            AppLog.Log(TAG, "next movie selected" + moviePos + "session " + MoviesSessionActivity.selectSessionPos);


                            if (MoviesSessionActivity.selectSessionPos != -1) {
                                movieList.get(MoviesSessionActivity.selectMoviePoss).getMovie_session().get(MoviesSessionActivity.selectSessionPos).setSessionSelected(false);
                                MovieAdapter.super.notifyItemChanged(MoviesSessionActivity.selectMoviePoss);
                                sessionList.get(position).setSessionSelected(true);
                                MoviesSessionActivity.selectSessionPos = position;
                                MoviesSessionActivity.selectMoviePoss = moviePos;
                            }


                        }


                        notifyDataSetChanged();
                    } catch (Exception e) {
                        AppLog.handleException(TAG, e);
                    }

                }
            });


            if (sessionList.get(position).isSessionSelected()) {
                holder.sessionTimeBtn.setBackgroundResource(R.drawable.next_btn_bg);
                holder.sessionTimeBtn.setTextColor(Color.parseColor("#ffffff"));


            } else {
                holder.sessionTimeBtn.setBackgroundResource(R.drawable.session_normal_bg);
                holder.sessionTimeBtn.setTextColor(Color.parseColor("#ffffff"));

            }


            return convertView;
        }

        public String convertTimeTO12Hour(String timeVal) {
            String time_12hour = "";

            SimpleDateFormat f1 = new SimpleDateFormat("hh:mm:ss");

            Date pmDate = null;
            try {
                pmDate = f1.parse(timeVal);
            } catch (ParseException e) {
                AppLog.handleException("time", e);


            }
            time_12hour = new SimpleDateFormat("hh:mm a").format(pmDate);

            return time_12hour;

        }

        public class ViewHolder {
            Button sessionTimeBtn;
        }


    }
}