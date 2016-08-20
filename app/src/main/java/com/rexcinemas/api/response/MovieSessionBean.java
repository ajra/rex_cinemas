package com.rexcinemas.api.response;

/**
 * Created by Jeyamurugan on 8/13/2016.
 */

public class MovieSessionBean {
    private String movie_sessionid;
    private String movie_time;
    private boolean sessionSelected;

    public boolean isSessionSelected() {
        return sessionSelected;
    }

    public void setSessionSelected(boolean sessionSelected) {
        this.sessionSelected = sessionSelected;
    }

    public String getMovie_sessionid() {
        return movie_sessionid;
    }

    public void setMovie_sessionid(String movie_sessionid) {
        this.movie_sessionid = movie_sessionid;
    }

    public String getMovie_time() {
        return movie_time;
    }

    public void setMovie_time(String movie_time) {
        this.movie_time = movie_time;
    }
}