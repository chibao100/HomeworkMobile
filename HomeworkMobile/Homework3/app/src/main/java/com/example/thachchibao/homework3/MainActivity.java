package com.example.thachchibao.homework3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;

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

         new JSONTask().execute("http://mobilehomework03.gear.host/Home/TakeData");


         /*
        TextView tv = (TextView)findViewById(R.id.tv1);
        tv.setText("Hello men");


        lv1 = (ListView)findViewById(R.id.listview1);
        mProductList = new ArrayList<>();
        mProductList.add(new Product(1,"Iphone 4",500,"Good phone"));
        mProductList.add(new Product(2,"Iphone 4",600,"Good phone 2"));
        mProductList.add(new Product(3,"Iphone 4",700,"Good phone 3 "));
        mProductList.add(new Product(5,"Iphone 4",800,"Good phone 4"));
        mProductList.add(new Product(6,"Iphone 4",900,"Good phone 5"));
        mProductList.add(new Product(9,"Iphone 4",100,"Good phone 6"));
        mProductList.add(new Product(44,"Iphone 4",200,"Good phone 8"));

        adapter = new MyAdapter(MainActivity.this,mProductList);
        lv1.setAdapter(adapter);
        */
    }

    public class JSONTask extends AsyncTask<String,String,List<Recipe>> {
        @Override
        protected List<Recipe> doInBackground(String... params){
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection)url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                String finalJson = buffer.toString();

                StringBuffer names = new StringBuffer();
                JSONObject parrentObject = new JSONObject(finalJson);
                JSONArray parrentArray = parrentObject.getJSONArray("myrecipes");

                List<Recipe> list = new ArrayList<>();

                for(int i=0;i<parrentArray.length();i++){
                    JSONObject obj = parrentArray.getJSONObject(i);
                    int id = obj.getInt("id");
                    String name = obj.getString("name");
                    String introduction = obj.getString("introduce");
                    String time = obj.getString("time");
                    String ingredient = obj.getString("ingredient");
                    String direction = obj.getString("direction");
                    String image = obj.getString("image");
                    String image2 = obj.getString("image2");
                    //
                    Recipe t = new Recipe(id,name,introduction,time,ingredient,direction,image,image2);
                    list.add(t);
                }

                return  list;



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(connection!=null){
                    connection.disconnect();
                }

                try {
                    if(reader!=null){
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }



        @Override
        protected void onPostExecute(List<Recipe> result) {
            super.onPostExecute(result);
            MyAdapter adapter = new MyAdapter(MainActivity.this,result);
            lv.setAdapter(adapter);
        }
    }
}
