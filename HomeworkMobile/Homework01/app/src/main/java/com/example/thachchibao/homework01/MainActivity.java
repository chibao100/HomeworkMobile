package com.example.thachchibao.homework01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onColorClick(View a){
        int red=255; int green= 255; int blue= 255; int alpha = 255;
        TextView tvLight = (TextView)findViewById(R.id.tvLight);
        switch (a.getId()){
            case R.id.btnWhite: red = 255; green = 255;  blue= 255;
                break;
            case  R.id.btnGrey: red = 196; green = 196;  blue= 196;
                break;
            case  R.id.btnRed: red = 255; green = 0;  blue= 0;
                break;
            case  R.id.btnGreen: red = 0; green = 255;  blue= 0;
                break;
            case  R.id.btnBlue: red = 0; green = 0;  blue= 255;
                break;
            default: red = 255; green = 255;  blue= 255;
                break;
        }
        int myBackColor = android.graphics.Color.argb(255, red, green, blue);
        tvLight.setBackgroundColor(myBackColor);

    }
}
