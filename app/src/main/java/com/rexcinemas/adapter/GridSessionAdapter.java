package com.rexcinemas.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.rexcinemas.App;
import com.rexcinemas.R;
import com.rexcinemas.api.response.MovieSessionBean;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeyamurugan on 8/13/2016.
 */


public class GridSessionAdapter extends BaseAdapter {

    private Context mContext;
    private List<MovieSessionBean> sessionList = new ArrayList();
    private SparseArray<SparseBooleanArray> checkedPositions;
    int groupPosition, sessionListPosition;

    public GridSessionAdapter(Context context, List<MovieSessionBean> sessionListValues) {
        mContext = context;
        this.sessionList = sessionListValues;
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

         /*   System.out.println("position" + checkedPositions.get(groupPosition));
            if (checkedPositions.get(groupPosition) != null) {
                boolean isChecked = checkedPositions.get(groupPosition).get(sessionListPosition);


                if(!isChecked){
                }

            } else {
                System.out.println("false");
            }
*/

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

                if (sessionList.get(position).isSessionSelected()) {
                    sessionList.get(position).setSessionSelected(false);
                } else {
                    sessionList.get(position).setSessionSelected(true);
                }
                notifyDataSetChanged();

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

