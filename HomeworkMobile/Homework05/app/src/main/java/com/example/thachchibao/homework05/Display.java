package com.example.thachchibao.homework05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by ThachChiBao on 2/28/2017.
 */

public class Display extends Activity {

    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display);

        webView = (WebView)findViewById(R.id.web);


        Intent i = getIntent();
        String link = i.getStringExtra("link");
        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());

    }
}
