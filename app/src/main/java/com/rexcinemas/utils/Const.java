package com.rexcinemas.utils;


import com.rexcinemas.App;

/**
 * @author Arputharaj R
 */
public class Const {

    public static final String TAG = "RexCinemas";
    // prefname
    public static String PREF_NAME = "RexCinemas_PRERENCE";
    // web services
    public static class ServiceType {

        public static final String HOST_URL = App.HOST_NAME;
    }
    // service parameters
        public static class Params {
        public static String MESSAGE_TYPE = "msgType";
        public static String PROPERTIES = "properties";
        public static String TARGET_ITEM = "targetItem";
        public static String MOBILE_NO = "mobileNumber";
        public static String PASSWORD = "password";
    }

    // service codes
    public class ServiceCode {
        /*Item DetailsService */
        public static final int ITEM_DETAILS = 6;
    }

}
