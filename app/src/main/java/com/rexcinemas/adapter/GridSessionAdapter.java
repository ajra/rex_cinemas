package com.rexcinemas.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.rexcinemas.App;
import com.rexcinemas.R;
import com.rexcinemas.activities.MoviesSessionActivity;
import com.rexcinemas.api.response.MovieSessionBean;
import com.rexcinemas.utils.AppLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeyamurugan on 8/13/2016.
 */


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
        System.out.println("sessionList grid" + sessionList.size());
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
        holder.sessionTimeBtn.setTypeface(App.lato_light);
        holder.sessionTimeBtn.setText(sessionList.get(position).getShow_time());


        System.out.println("pos" + position + "  name" + sessionList.get(position).getShow_time());

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
/*                        if(MoviesSessionActivity.selectSessionPos==position)
                        {
                            sessionList.get(position).setSessionSelected(false);
                            MoviesSessionActivity.selectMoviePoss=-1;
                            MoviesSessionActivity.selectSessionPos=-1;


                        }
                        else
                        {

                            sessionList.get(MoviesSessionActivity.selectSessionPos).setSessionSelected(false);

                            sessionList.get(position).setSessionSelected(true);
                            MoviesSessionActivity.selectMoviePoss=moviePos;
                            MoviesSessionActivity.selectMoviePoss=position;

                        }*/


                    } else if (MoviesSessionActivity.selectMoviePoss != moviePos) {
                        AppLog.Log(TAG, "next movie selected" + moviePos);


                        if (MoviesSessionActivity.selectMoviePoss != -1) {


/*
                            sessionList.get(MoviesSessionActivity.selectMoviePoss).
*/

                        }


                    }


                    notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        if (sessionList.get(position).isSessionSelected()) {
            holder.sessionTimeBtn.setBackgroundResource(R.drawable.next_btn_bg);
            holder.sessionTimeBtn.setTextColor(Color.parseColor("#ffffff"));


        } else {
            holder.sessionTimeBtn.setBackgroundResource(R.drawable.session_normal_bg);
            holder.sessionTimeBtn.setTextColor(Color.parseColor("#000000"));

        }


        return convertView;
    }


    static class ViewHolder {
        Button sessionTimeBtn;
    }


}

