package com.rexcinemas.api.response;

import java.util.List;

public class MovieDateBean {
    public MovieDateBean(String movie_date, boolean dateSelected) {
        this.movie_date = movie_date;
        this.dateSelected = dateSelected;
    }

    public String movie_date;

    public boolean dateSelected;

    public boolean isDateSelected() {
        return dateSelected;
    }

    public void setDateSelected(boolean dateSelected) {
        this.dateSelected = dateSelected;
    }




    public String getMovie_date() {
        return movie_date;
    }

    public void setMovie_date(String movie_date) {
        this.movie_date = movie_date;
    }


}
