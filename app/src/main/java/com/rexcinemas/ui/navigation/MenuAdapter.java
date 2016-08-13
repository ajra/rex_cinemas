package com.rexcinemas.ui.navigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rexcinemas.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SRadhakrishnan on 13-08-2016.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private ArrayList<HomeMenu> android;
    private Context context;
    public MenuAdapter(Context context,ArrayList<HomeMenu> android) {
        this.android = android;
        this.context = context;
        }
    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(view);
        }
    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_android.setText(android.get(i).menuName);
        viewHolder.img_android.setImageResource(android.get(i).imageId);
     //   Picasso.with(context).load(android.get(i).getAndroid_image_url()).resize(240, 120).into(viewHolder.img_android);
        }
    @Override
    public int getItemCount() {
        return android.size();
        }

public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_android;
        private ImageView img_android;
        public ViewHolder(View view) {
            super(view);

                tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView) view.findViewById(R.id.img_android);
            }
        }

}
