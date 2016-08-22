package com.rexcinemas.api.response;

import java.util.List;

public class MoviesResponse {
    /**
     * movie_name : BABU BANGARAM (PG)
     * movie_list : [{"cinema_name":"REX Cinemas BeachRD","movie_date":"16/08/2016","movie_url":"","movie_session":[{"movie_sessionid":"23075","movie_time":"21:00:00"}]}]
     */

    private String movie_name;
    private String cinema_name;

    public String getCinema_name() {
        return cinema_name;
    }

    public void setCinema_name(String cinema_name) {
        this.cinema_name = cinema_name;
    }

    /**
     * cinema_name : REX Cinemas BeachRD
     * movie_date : 16/08/2016
     * movie_url :
     * movie_session : [{"movie_sessionid":"23075","movie_time":"21:00:00"}]
     */

    private List<MovieListBean> movie_list;

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public List<MovieListBean> getMovie_list() {
        return movie_list;
    }

    public void setMovie_list(List<MovieListBean> movie_list) {
        this.movie_list = movie_list;
    }


}
