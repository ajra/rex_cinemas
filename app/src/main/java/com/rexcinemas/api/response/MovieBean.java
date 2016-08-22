package com.rexcinemas.api.response;

public class MovieBean {
    /**
     * movie_id : 1091
     * movie_name : Kabali
     * movie_caption : PG
     * movie_ratinng : 4.5
     * movie_image : http://rexcinemas.com.sg/web/images/kabali.jpg
     */

/*
    private String movie_id;
*/
    private String movie_name;
    private String movie_caption;
/*
    private String movie_ratinng;
*/
    private String movie_url;

/*
    public String getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(String movie_id) {
        this.movie_id = movie_id;
    }
*/

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

    /*public String getMovie_ratinng() {
        return movie_ratinng;
    }

    public void setMovie_ratinng(String movie_ratinng) {
        this.movie_ratinng = movie_ratinng;
    }

  */
    public String getMovie_url() {
        return movie_url;
    }

    public void setMovie_url(String movie_url) {
        this.movie_url = movie_url;
    }

/*[{"movie_id":"1091","movie_name":"Kabali","movie_caption":"PG","movie_ratinng":"4.5","movie_image":"http:\/\/rexcinemas.com.sg\/web\/images\/kabali.jpg"},{"movie_id":"1092","movie_name":"Iraivi","movie_caption":"PG","movie_ratinng":"4","movie_image":"http:\/\/rexcinemas.com.sg\/web\/images\/kabali.jpg"},{"movie_id":"1093","movie_name":"Theri","movie_caption":"PG","movie_ratinng":"4.5","movie_image":"http:\/\/rexcinemas.com.sg\/web\/images\/kabali.jpg"}]*/
}
