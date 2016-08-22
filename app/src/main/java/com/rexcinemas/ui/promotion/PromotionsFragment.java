package com.rexcinemas.ui.promotion;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.rexcinemas.R;

import com.squareup.picasso.Picasso;

public class PromotionsFragment extends Fragment {
    ImageView promoBigImage;



    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    /**
     * Interface for communicating data
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentCreated(int number);
    }

    private View rootView;
    private int number;

    private OnFragmentInteractionListener listener;


    int currentPromo;

    public static Fragment newInstance(int number) {
        PromotionsFragment instance;
        instance = new PromotionsFragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_promotion, container, false);

        promoBigImage=(ImageView)rootView.findViewById(R.id.promoBigImage);
        setValues(currentPromo);
        return rootView;
    }

    public void setValues(int currentPromo)
    {


       /* String movieUrl ="";

        movieUrl="http://rexcinemas.com.sg//web//images/kabali.jpg";
        Picasso.with(getActivity())
                .load(Uri.parse(movieUrl)).placeholder(R.drawable.bg).error(R.drawable.bg)
                .into(promoBigImage);
*/

        if(currentPromo==0)
        {
            promoBigImage.setImageResource(R.drawable.promotions1);
        }
        else if(currentPromo==1)
        {
            promoBigImage.setImageResource(R.drawable.promotions2);

       }
        else if(currentPromo==2)
        {
            promoBigImage.setImageResource(R.drawable.promotions3);


        }


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // for the first page.
            number = PromotionHomeFragment.selectedPromotion;

            randomNumber();

        }
    }

    /**
     * random number between 10 and 100
     */
    public void randomNumber() {
        if (promoBigImage != null) {
            setValues(PromotionHomeFragment.selectedPromotion);
        }
    }

    /**
     * @return number randomized.
     */
    public int getNumber() {
        return number;
    }
}