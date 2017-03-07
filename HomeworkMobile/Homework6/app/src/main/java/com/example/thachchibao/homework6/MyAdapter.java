package com.example.thachchibao.homework6;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ThachChiBao on 3/1/2017.
 */

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<CleveLand> mList;
    // Contructor


    public MyAdapter(Context mContext, List<CleveLand> mList) {
        this.mContext = mContext;
        this.mList= mList;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext,R.layout.row,null);
        TextView title = (TextView)v.findViewById(R.id.tvtitle);

        title.setText(mList.get(position).getPlace());
        return v;
    }
}
