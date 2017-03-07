package com.example.thachchibao.homework6;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ThachChiBao on 3/1/2017.
 */

public class Choose extends Activity {

    public String place;
    public String link;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose);

        Intent i = getIntent();
        place = i.getStringExtra("place");
        link = i.getStringExtra("link");


        tv = (TextView)findViewById(R.id.tv);
        tv.setText(place);
    }


    public void Choose(View a){
        if(a.getId() == R.id.map){

            Uri gmmIntentUri = Uri.parse("geo:41.501029,-81.703808?q=" + Uri.encode(place));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        }else {
            Intent i = new Intent(Choose.this,Display.class);
            i.putExtra("link",link);
            startActivity(i);
        }
    }


}
