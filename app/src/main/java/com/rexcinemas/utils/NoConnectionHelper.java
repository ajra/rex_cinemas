package com.rexcinemas.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.rexcinemas.R;

public class NoConnectionHelper {

    private static final String TAG = NoConnectionHelper.class.getSimpleName();

    private static NoConnectionHelper sInstance = null;

    private final Context mContext;

    private final Handler mHandler;

    private boolean noConnectionVisible;

    private ViewGroup noConnectionView;

    private Activity mCurrentActivity;

    private NoConnectionHelper(Context context, Handler handler) {
        mContext = context;
        mCurrentActivity = (Activity) context;
        mHandler = handler;
    }

    public static void init(Context context, Handler handler) {
        sInstance = new NoConnectionHelper(context, handler);
    }

    public static NoConnectionHelper getInstance() {
        return sInstance;
    }

    public Activity getCurrentActivity() {
        return mCurrentActivity;
    }

    public void setCurrentActivity(FragmentActivity currentActivity) {
        this.mCurrentActivity = currentActivity;
    }

    public Context getContext() {
        return mContext;
    }

    public void showNoConnectionView() {
        if (!noConnectionVisible && mContext != null) {
            DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
            final WindowManager windowManager = (WindowManager) mContext.getApplicationContext()
                    .getSystemService(Context.WINDOW_SERVICE);

            LayoutInflater inflater = (LayoutInflater)
                    mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            noConnectionView = (ViewGroup) inflater.inflate(
                    R.layout.no_connection_view, null, false);

            final WindowManager.LayoutParams params = new WindowManager.LayoutParams();

            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            params.format = PixelFormat.TRANSPARENT;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;

            params.gravity = Gravity.TOP | Gravity.LEFT;
            params.x = 0;
            int y = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48,
                    dm);
            params.y = y;

            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    noConnectionVisible = true;
                    noConnectionView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            noConnectionView.setVisibility(View.GONE);
                        }
                    });
                    windowManager.addView(noConnectionView, params);
                }
            });
        }
    }

    public void showToast(final String message) {
        if (mContext != null && mHandler != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(mContext, message,
                            Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    public void unHideNoConViewIfHidden() {
        if (noConnectionView != null && mContext != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    noConnectionView.setVisibility(View.VISIBLE);
                }
            });
        }
    }

    public void hideNoConnectionView(final boolean remove) {
        if (noConnectionVisible && mContext != null) {
            final WindowManager windowManager = (WindowManager) mContext.getApplicationContext()
                    .getSystemService(Context.WINDOW_SERVICE);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (remove) {
                            noConnectionVisible = false;
                            windowManager.removeView(noConnectionView);
                        } else {
                            noConnectionView.setVisibility(View.GONE);
                        }
                    } catch (Exception ex) {
                        // Will get IllegalStateExceptions on occasions when the attached views in the WindowManager doesn't exist.
                        Log.wtf(TAG, "Couldn't Hide NoConnectionView: ", ex);
                    }
                }
            });
        }
    }

}
