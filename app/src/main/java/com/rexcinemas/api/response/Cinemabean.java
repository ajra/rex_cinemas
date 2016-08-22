package com.rexcinemas.api.response;

import java.util.List;

public class Cinemabean {


    public List<MovieDateBean> rexbeach_list;

    public List<MovieDateBean> rexmac_list;

    public Cinemabean() {
    }

    public List<MovieDateBean> getRexbeach_list() {
        return rexbeach_list;
    }

    public void setRexbeach_list(List<MovieDateBean> rexbeach_list) {
        this.rexbeach_list = rexbeach_list;
    }

    public List<MovieDateBean> getRexmac_list() {
        return rexmac_list;
    }

    public void setRexmac_list(List<MovieDateBean> rexmac_list) {
        this.rexmac_list = rexmac_list;
    }
}
