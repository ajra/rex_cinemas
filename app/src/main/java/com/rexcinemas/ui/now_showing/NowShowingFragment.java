package com.rexcinemas.ui.now_showing;

import android.app.Activity;
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
import android.widget.RatingBar;
import android.widget.TextView;

import com.rexcinemas.App;
import com.rexcinemas.R;
import com.rexcinemas.activities.MoviesSessionActivity;
import com.rexcinemas.api.response.MovieBean;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NowShowingFragment extends Fragment {
    ImageView showBigImage;
    TextView showNameText;
    TextView showCaption;
    RatingBar showRatting;

/*
    @Bind(R.id.showBigImage)
    ImageView showBigImage;
    @Bind(R.id.showNameText)
    TextView showNameText;
    @Bind(R.id.showCaption)
    TextView showCaption;
    @Bind(R.id.showRatting)
    RatingBar showRatting;
*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
/*
        ButterKnife.unbind(this);
*/
    }

    /**
     * Interface for communicating data
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentCreated(int number);
    }

    private View rootView;
    private TextView textView;
    private int number;

    private OnFragmentInteractionListener listener;

    MovieBean currentMovie;

    public static Fragment newInstance(int number) {
        NowShowingFragment instance;
        instance = new NowShowingFragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_now_showing, container, false);
/*
        ButterKnife.bind(rootView);
*/
        showBigImage=(ImageView)rootView.findViewById(R.id.showBigImage);
        showNameText=(TextView)rootView.findViewById(R.id.showNameText);
        showCaption=(TextView)rootView.findViewById(R.id.showCaption);
        showRatting=(RatingBar)rootView.findViewById(R.id.showRatting);

        currentMovie = NowMainShowingFragment.nowShowingList.get(NowMainShowingFragment.selectedShow);
        Log.i("Fragment", "number: " + NowMainShowingFragment.selectedShow + "");
        /*ButterKnife.bind(this, rootView);*/
        setValues(currentMovie);
        showBigImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dateIntent=new Intent(getActivity(), MoviesSessionActivity.class);
                dateIntent.putExtra("page","now");
                dateIntent.putExtra("movie",showNameText.getText().toString());
                getActivity().startActivity(dateIntent);
            }
        });
        return rootView;
    }

    public void setValues(MovieBean currentMovie)
    {

        showNameText.setText(currentMovie.getMovie_name());
        showCaption.setText("("+currentMovie.getMovie_caption()+")");

        showNameText.setTypeface(App.lato_bold);
        showCaption.setTypeface(App.lato_light);


        String movieUrl ="";

/*
        movieUrl="http://rexcinemas.com.sg//web//images/kabali.jpg";
*/
        movieUrl=currentMovie.getMovie_url();
        Picasso.with(getActivity())
                .load(Uri.parse(movieUrl)).placeholder(R.drawable.bg).error(R.drawable.bg)
                .into(showBigImage);



    }
/*    @Override
    public void onAttach(Activity activity) {
        try {
            listener = (OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
        super.onAttach(activity);
    }*/

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // for the first page.
            number = NowMainShowingFragment.selectedShow;

            Log.i("Fragment", "number: " + NowMainShowingFragment.selectedShow);

/*
            listener.onFragmentCreated(NowMainShowingFragment.selectedShow);
*/
            Log.i("Fragment", "call activity: " + NowMainShowingFragment.selectedShow);
            randomNumber();

        }
    }

    /**
     * random number between 10 and 100
     */
    public void randomNumber() {
        if (showNameText != null) {
            setValues(NowMainShowingFragment.nowShowingList.get(NowMainShowingFragment.selectedShow));
        }
    }

    /**
     * @return number randomized.
     */
    public int getNumber() {
        return number;
    }
}