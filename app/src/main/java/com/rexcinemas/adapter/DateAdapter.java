package com.rexcinemas.adapter;

import android.content.Context;
import android.nfc.Tag;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rexcinemas.App;
import com.rexcinemas.R;
import com.rexcinemas.api.response.MovieDateBean;
import com.rexcinemas.utils.AppLog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {


    Context context;
    List<MovieDateBean> movidateList;

    public DateAdapter(Context context, List<MovieDateBean> movidateList) {
        this.context = context;
        this.movidateList = movidateList;


    }

    @Override
    public DateViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie_date, viewGroup, false);
        return new DateViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(DateViewHolder holder, final int i) {

        try {

            final  MovieDateBean movieDateBean = movidateList.get(i);



            // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(movidateList.get(i).getMovie_date());



            String showDate=new SimpleDateFormat("EEE dd MMM").format(date);
            AppLog.Log("date",showDate);
            if(movieDateBean.isDateSelected())
            {
                holder.dateLayout.setBackgroundResource(R.drawable.date_selected_bg);

            }
            else
            {
                holder.dateLayout.setBackgroundResource(R.drawable.date_normal_bg);

            }
//Sun 10 Jan
            String dayVal=showDate.substring(0,3);
            String dateVal=showDate.substring(4,6);
             String monthVal=showDate.substring(6);


            holder.showDateText.setText(dateVal);
            holder.showDayText.setText(dayVal);
            holder.showMonthText.setText(monthVal);


            holder.showDateText.setTypeface(App.lato_regular);
            holder.showDayText.setTypeface(App.lato_regular);
            holder.showMonthText.setTypeface(App.lato_regular);


/*
            holder.dateLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(movieDateBean.isDateSelected())
                    {

                    }
                    else
                    {
                        movieDateBean.setDateSelected(true);
                        notifyDataSetChanged();
                    }
                }
            });
*/

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return movidateList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public  MovieDateBean  getItem(int position)
    {


        System.out.println("dd"+movidateList.size());
        return movidateList.get(position);
    }
    public class DateViewHolder extends RecyclerView.ViewHolder {

        public TextView showDayText,showDateText,showMonthText;
        LinearLayout dateLayout;


        public DateViewHolder(View v) {
            super(v);


            showDateText = (TextView) v.findViewById(R.id.showDateText);
            showDayText = (TextView) v.findViewById(R.id.showDayText);
            showMonthText = (TextView) v.findViewById(R.id.showMonthText);
            dateLayout=(LinearLayout)v.findViewById(R.id.dateLayout);


        }
    }
}
