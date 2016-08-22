package com.rexcinemas.api.response;

import java.util.List;

public  class MovieListBean {
    private String cinema_name;
    private String movie_date;
    private String movie_url;
    private String movie_name;


    public MovieListBean(String cinema_name, String movie_date, String movie_url, String movie_name, List<MovieSessionBean> movie_session) {
        this.cinema_name = cinema_name;
        this.movie_date = movie_date;
        this.movie_url = movie_url;
        this.movie_name = movie_name;
        this.movie_session = movie_session;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    /**
     * movie_sessionid : 23075
     * movie_time : 21:00:00
     */

    private List<MovieSessionBean> movie_session;

    public String getCinema_name() {
        return cinema_name;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    public String getMovie_date() {
        return movie_date;
    }

    public void setMovie_date(String movie_date) {
        this.movie_date = movie_date;
    }

    public String getMovie_url() {
        return movie_url;
    }

    public void setMovie_url(String movie_url) {
        this.movie_url = movie_url;
    }

    public List<MovieSessionBean> getMovie_session() {
        return movie_session;
    }

    public void setMovie_session(List<MovieSessionBean> movie_session) {
        this.movie_session = movie_session;
    }


}


