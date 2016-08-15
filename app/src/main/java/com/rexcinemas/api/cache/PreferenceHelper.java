package com.rexcinemas.api.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.rexcinemas.utils.Const;

public class PreferenceHelper {

    private static final String TOKEN = "token";
    private static final String IMAGE_TYPE = "image_type";
    private static final String APP_VERSION = "app_version";
    private static final String APP_PACKAGE_NAME = "app_package_name";
    private SharedPreferences rexCinemasPreference;
    private Context context;
    /**
     * Instantiates a new Preference helper.
     *
     * @param context the context
     */
    public PreferenceHelper(Context context) {
        this.context = context;
        this.rexCinemasPreference = context.getSharedPreferences(Const.PREF_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Put token.
     *
     * @param token the token
     */
    public void putToken(String token) {

        SharedPreferences.Editor editor = this.rexCinemasPreference.edit();
        editor.putString(TOKEN, token);
        editor.apply();

    }
    public String getTOKEN() {
        return this.rexCinemasPreference.getString(TOKEN, "");

    }


    /**
     * Put image type.
     *
     * @param type the type
     */
    public void putImageType(String type) {
        Editor edit = rexCinemasPreference.edit();
        edit.putString(IMAGE_TYPE, type);
        edit.apply();

    }

    public String getImageType() {
        return rexCinemasPreference.getString(IMAGE_TYPE, "");

    }
    /**
     * Put app version.
     *
     * @param appVersion the app version
     */
    public void putAppVersion(String appVersion) {
        Editor edit = rexCinemasPreference.edit();
        edit.putString(APP_VERSION, appVersion);
        edit.apply();
    }

    public String getAppVersion() {
        return rexCinemasPreference.getString(APP_VERSION, null);
    }

    /**
     * Put app pack name.
     *
     * @param appVersion the app version
     */
    public void putAppPackName(String appVersion) {
        Editor edit = rexCinemasPreference.edit();
        edit.putString(APP_PACKAGE_NAME, appVersion);
        edit.apply();
    }

    public String getAppPackName() {
        return rexCinemasPreference.getString(APP_PACKAGE_NAME, null);
    }

}
