package com.rexcinemas.api.response;

import java.util.List;

/**
 * Created by Jeyamurugan on 8/13/2016.
 */
public class MovieDateBean {

    public String theatre_name;
    public String movie_date;

    public boolean dateSelected;

    public boolean isDateSelected() {
        return dateSelected;
    }

    public void setDateSelected(boolean dateSelected) {
        this.dateSelected = dateSelected;
    }

    public List<MovieListbean> movie_list;

    public String getTheatre_name() {
        return theatre_name;
    }

    public void setTheatre_name(String theatre_name) {
        this.theatre_name = theatre_name;
    }

    public String getMovie_date() {
        return movie_date;
    }

    public void setMovie_date(String movie_date) {
        this.movie_date = movie_date;
    }

    public List<MovieListbean> getMovie_list() {
        return movie_list;
    }

    public void setMovie_list(List<MovieListbean> movie_list) {
        this.movie_list = movie_list;
    }
}
