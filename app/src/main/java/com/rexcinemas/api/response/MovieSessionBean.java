package com.rexcinemas.api.response;

/**
 * Created by Jeyamurugan on 8/13/2016.
 */

public class MovieSessionBean {
    private String session_id;
    private String show_time;
    private boolean sessionSelected;

    public boolean isSessionSelected() {
        return sessionSelected;
    }

    public void setSessionSelected(boolean sessionSelected) {
        this.sessionSelected = sessionSelected;
    }

    public String getSession_id() {
        return session_id;
    }

    public void setSession_id(String session_id) {
        this.session_id = session_id;
    }

    public String getShow_time() {
        return show_time;
    }

    public void setShow_time1(String show_time) {
        this.show_time = show_time;
    }
}