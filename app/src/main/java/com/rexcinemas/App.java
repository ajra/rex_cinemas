package com.rexcinemas;

import android.content.Context;
import android.graphics.Typeface;
import android.support.multidex.MultiDexApplication;

import com.rexcinemas.api.cache.PreferenceHelper;

public class App extends MultiDexApplication {
    public static PreferenceHelper preferenceHelper;
    public static String HOST_NAME = "http://www.rexcinemas.com.sg/demo/web/";
    private static App sInstance;
    public static Typeface lato_bold,lato_light,lato_regular;


    public App() {
        super();
        sInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        lato_bold = Typeface.createFromAsset(this.getAssets(), "fonts/lato-bold.ttf");
        lato_light = Typeface.createFromAsset(this.getAssets(), "fonts/lato-light.ttf");
        lato_regular = Typeface.createFromAsset(this.getAssets(), "fonts/lato-regular.ttf");
    }

    public static App getInstance() {
        return sInstance;
    }

    /**
     * Gets preference instance.
     *
     * @param context the context
     * @return the preference instance
     */
    public static PreferenceHelper getPreferenceInstance(Context context) {
        if (preferenceHelper == null) {
            preferenceHelper = new PreferenceHelper(context);
            return preferenceHelper;
        } else
            return preferenceHelper;
    }

}
