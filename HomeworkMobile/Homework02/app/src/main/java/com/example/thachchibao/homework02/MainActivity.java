package com.example.thachchibao.homework02;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public  void onPickClick(View a){
        if(a.getId()==R.id.button2){
            Intent i = new Intent(MainActivity.this,Choose.class);
            startActivity(i);
        }
    }
}
