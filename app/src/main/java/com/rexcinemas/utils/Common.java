package com.rexcinemas.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class Common {
    public static <T> String getTAG(Class<T> clazz) {
        String className = clazz.getName();
        className = getClassNameFromPackageName(className);
        return className;
    }
    private static String getClassNameFromPackageName(String className) {
        if (className.contains(".")) {
            String[] classNameArr = className.split("\\.");
            className = classNameArr[classNameArr.length - 1];
        }
        return className;
    }
    /**
     * Returns the actual width of screen of device in pixels
     *
     * @param context
     *            context of activity
     * @return width in pixels
     */
    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Context context) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        if (Build.VERSION.SDK_INT >= 13) {
            DisplayMetrics metrics = new DisplayMetrics();
            ((Activity) context).getWindowManager().getDefaultDisplay()
                    .getMetrics(metrics);
            return metrics.widthPixels;

        } else {
            Display display = ((WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE))
                    .getDefaultDisplay();
            return display.getWidth();
        }
    }
}
