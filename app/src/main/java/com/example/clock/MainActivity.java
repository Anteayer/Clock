package com.example.clock;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler;
    private TextView text;
    ResizableImageView img;
    private Drawable bg_a;
    private Drawable bg_b;

    int count = 0;
    int index = 2;
    String index_name = "bkg_";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Utillity.hideBottomUIMenu(this);

        text = findViewById(R.id.clock);

        mHandler = new Handler();
        mHandler.post(mUpdate);

        img = findViewById(R.id.img);
        img.setBackground(getResources().getDrawable(R.mipmap.bkg_1));
        img.setAlpha(0.7f);
    }





    private Runnable mUpdate = new Runnable() {
        public void run() {


            if(count == 10) { // 可以自己设定秒数来更换壁纸
                index_name = index_name + index;
                index ++;
                CreateImg(index_name);
                index_name = "bkg_";
                count = 0;
                if(index == 9) index = 1;
            }

            text.setGravity(Gravity.CENTER);
            text.setText(CreateClock());
            mHandler.postDelayed(this, 1000);
            count++;
        }
    };


    public void CreateImg(String img_name) {
        char[] arr = img_name.toCharArray();
        if(arr[4] == '1') {
            arr[4] = '9';
        } else {
            arr[4] = (char) (arr[4] - 1);
        }
        String mgi_name = new String(arr);

        bg_a = getResources().getDrawable(Utillity.getResourceID(R.mipmap.class, mgi_name));
        bg_b = getResources().getDrawable(Utillity.getResourceID(R.mipmap.class, img_name));


        img = findViewById(R.id.img);
        img.setBackground(getResources().getDrawable(Utillity.getResourceID(R.mipmap.class, img_name)));
        TransitionDrawable td = new TransitionDrawable(new Drawable[]{bg_a, bg_b});
        img.setBackgroundDrawable(td);
        td.startTransition(1000);
    }

    public String CreateClock() {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        return dateFormat.format(now);
    }

}
