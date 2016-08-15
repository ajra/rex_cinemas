package com.rexcinemas.api.response;

import java.util.List;

/**
 * Created by Jeyamurugan on 8/13/2016.
 */
public class MovieListbean {
    /**
     * movie_id : 1091
     * movie_name : Kabali
     * movie_caption : PG
     * movie_image : /images/kabali.jpg
     * movie_session : [{"session_id":"sss001","show_time1":"12:30 PM"},{"session_id":"sss002","show_time":"03:30 PM"},{"session_id":"sss003","show_time":"21:00:00"}]
     */

    private String movie_id;
    private String movie_name;
    private String movie_caption;
    private String movie_image;
    /**
     * session_id : sss001
     * show_time1 : 12:30 PM
     */

    private List<MovieSessionBean> movie_session;

    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_caption() {
        return movie_caption;
    }

    public void setMovie_caption(String movie_caption) {
        this.movie_caption = movie_caption;
    }

    public String getMovie_image() {
        return movie_image;
    }

    public void setMovie_image(String movie_image) {
        this.movie_image = movie_image;
    }

    public List<MovieSessionBean> getMovie_session() {
        return movie_session;
    }

    public void setMovie_session(List<MovieSessionBean> movie_session) {
        this.movie_session = movie_session;
    }


}
