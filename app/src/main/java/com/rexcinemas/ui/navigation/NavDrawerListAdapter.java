package com.rexcinemas.ui.navigation;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rexcinemas.App;
import com.rexcinemas.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SRadhakrishnan on 13-08-2016.
 */
public class NavDrawerListAdapter extends BaseAdapter {
    private Context context;
    private List<HomeMenu> navDrawerItems;
    public NavDrawerListAdapter(Context context, List<HomeMenu> navDrawerItems){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.row_layout, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.img_android);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.tv_android);

        imgIcon.setImageResource(navDrawerItems.get(position).imageId);
        txtTitle.setText(navDrawerItems.get(position).menuName);
        txtTitle.setTextSize(14);
        txtTitle.setTypeface(App.lato_regular);

        return convertView;
    }
}
