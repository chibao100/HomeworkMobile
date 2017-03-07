package com.example.thachchibao.homework3;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by ThachChiBao on 2/24/2017.
 */

public class Detail1 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail1);

        String R_name;
        String R_introduction;
        String R_time;
        String R_ingredient;
        String R_direction;
        String R_image1;


        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                R_name = null;
                R_introduction = null;
                R_time = null;
                R_ingredient = null;
                R_direction = null;
                R_image1 = null;
            } else {
                R_name = extras.getString("R_name");
                R_introduction = extras.getString("R_introduction");
                R_time = extras.getString("R_time");
                R_ingredient = extras.getString("R_ingredient");
                R_direction = extras.getString("R_direction");
                R_image1 = extras.getString("R_image1");
            }
        } else {
            R_name= (String) savedInstanceState.getSerializable("R_name");
            R_introduction = (String) savedInstanceState.getSerializable("R_introduction");
            R_time= (String) savedInstanceState.getSerializable("R_time");
            R_ingredient= (String) savedInstanceState.getSerializable("R_ingredient");
            R_direction= (String) savedInstanceState.getSerializable("R_direction");
            R_image1= (String) savedInstanceState.getSerializable("R_image1");
        }
        //


        ImageView image = (ImageView)findViewById(R.id.imageView2);
        TextView name = (TextView)findViewById(R.id.name);
        TextView introduction = (TextView)findViewById(R.id.introduction);
        TextView time = (TextView)findViewById(R.id.time1);
        TextView ingredient = (TextView)findViewById(R.id.ingredient);
        TextView direction = (TextView)findViewById(R.id.direction);
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar);

        ImageLoader.getInstance().displayImage(R_image1, image, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                progressBar.setVisibility(View.GONE);
            }
        });
        name.setText(R_name);
        introduction.setText(R_introduction);
        time.setText(R_time);
        ingredient.setText(R_ingredient);
        direction.setText(R_direction);
    }
}
