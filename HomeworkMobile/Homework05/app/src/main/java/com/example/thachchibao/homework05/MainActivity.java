package com.example.thachchibao.homework05;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {


    ListView lv;
    List<Recipe> myListRecipt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create default options which will be used for every
//  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
        .cacheInMemory(true)
                .cacheOnDisk(true)
        .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
        .defaultDisplayImageOptions(defaultOptions)
        .build();

        ImageLoader.getInstance().init(config); // Do it on Application start

        lv = (ListView)findViewById(R.id.lv);
        myListRecipt = new ArrayList<>();

        new RSS().execute("http://feeds.feedburner.com/elise/simplyrecipes");

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,Display.class);
                i.putExtra("link",myListRecipt.get(position).getLink());
                startActivity(i);
            }
        });


    }

    class  RSS extends AsyncTask<String,String,List<Recipe>>{

        @Override
        protected List<Recipe> doInBackground(String... params) {
            String xml = readURL(params[0]);
            myListRecipt = readXML(xml);
            return myListRecipt;
        }

        @Override
        protected void onPostExecute(List<Recipe> result) {
            super.onPostExecute(result);
            Myadapter adapter = new Myadapter(MainActivity.this,result);
            lv.setAdapter(adapter);
        }
    }

    private  String readURL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }

    private List<Recipe> readXML(String xml){
        XMLDOMParser parser = new XMLDOMParser();
        Document document = parser.getDocument(xml);
        NodeList nodeList = document.getElementsByTagName("item");
        NodeList nodeDescription = document.getElementsByTagName("description");
        String title="";
        String link="";
        String image="";
        //
        List<Recipe> list = new ArrayList<>();
        for(int i=0;i<nodeList.getLength();i++){
            // take title & link
            Element element = (Element) nodeList.item(i);
            title = parser.getValue(element,"title");
            link = parser.getValue(element,"link");
            // take image
            String cdata = nodeDescription.item(i + 2).getTextContent();
            Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
            Matcher matcher = p.matcher(cdata);
            if(matcher.find()){
                image = matcher.group(1);
            }
            list.add(new Recipe(title,link,image));
        }
        return list;
    }


}
