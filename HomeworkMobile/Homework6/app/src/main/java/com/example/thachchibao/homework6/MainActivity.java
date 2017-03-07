package com.example.thachchibao.homework6;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    List<CleveLand> mList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.lv);
        mList = new ArrayList<>();
        //
        mList.add(new CleveLand("Cleveland Tower City","http://www.towercitycenter.com"));
        mList.add(new CleveLand("Browns Football Field","http://www.clevelandbrowns.com"));
        mList.add(new CleveLand("Cleveland State University","http://www.csuohio.edu"));
        mList.add(new CleveLand("Playhouse Square","http://www.playhousesquare.org"));
        mList.add(new CleveLand("Art Museum","http://www.clevelandart.org"));

        MyAdapter adapter = new MyAdapter(MainActivity.this,mList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,Choose.class);
                i.putExtra("place",mList.get(position).getPlace());
                i.putExtra("link",mList.get(position).getWeb());
                startActivity(i);
            }
        });
    }
}
