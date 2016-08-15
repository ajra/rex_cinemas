package com.rexcinemas.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rexcinemas.App;
import com.rexcinemas.R;
import com.rexcinemas.api.response.MovieListbean;

import java.util.List;

/**
 * Created by Jeyamurugan on 8/13/2016.
 */


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {


    Context context;
    List<MovieListbean> movieList;

    public MovieAdapter(Context context, List<MovieListbean> movieList) {
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

            final MovieListbean movieListbean = movieList.get(i);
            String movieImageUrl = movieListbean.getMovie_image().replaceAll("/", "//");

            holder.movieNameText.setText(movieListbean.getMovie_name());

/*
            Picasso.with(context)
                    .load(Uri.parse("http://rexcinemas.com.sg//web//" + movieImageUrl)).placeholder(R.drawable.thumb01).error(R.drawable.thumb01)
                    .into(holder.movieThumbImage);
*/

            holder.movieNameText.setTypeface(App.lato_bold);

            holder.sessionGridView.setNumColumns(3);
            GridSessionAdapter sessionAdapter = new GridSessionAdapter(context, movieListbean.getMovie_session());
            holder.sessionGridView.setAdapter(sessionAdapter);

            System.out.println("adapter " + sessionAdapter.getCount());
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
                System.out.println("total height" + size + totalHeight);
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


    public MovieListbean getItem(int position) {

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
}