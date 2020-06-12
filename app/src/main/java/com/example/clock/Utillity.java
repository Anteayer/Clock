package com.example.clock;

import android.os.Build;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Field;

class Utillity {

    static void hideBottomUIMenu(AppCompatActivity activity) {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    static int getResourceID(Class<?> category, String name) {
        try {
            Field res = category.getDeclaredField(name);
            return res.getInt(res);
        } catch (Exception ex) {
            return 0;
        }
    }
}
