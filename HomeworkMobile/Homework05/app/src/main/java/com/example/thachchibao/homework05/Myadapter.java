package com.example.thachchibao.homework05;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

/**
 * Created by ThachChiBao on 2/28/2017.
 */

public class Myadapter extends BaseAdapter {

    private Context mContext;
    private List<Recipe> mRecipeList;
    // Contructor


    public Myadapter(Context mContext, List<Recipe> mRecipeList) {
        this.mContext = mContext;
        this.mRecipeList= mRecipeList;
    }

    @Override
    public int getCount() {
        return mRecipeList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecipeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext,R.layout.row,null);
        ImageView vimage = (ImageView)v.findViewById(R.id.vimage);
        TextView title = (TextView)v.findViewById(R.id.tvtitle);
        final ProgressBar progressBar = (ProgressBar)v.findViewById(R.id.progressBar);


        //

        title.setText(mRecipeList.get(position).getTitle());
        //Picasso.with(mContext).load(mRecipeList.get(position).getImage()).into(image);
        ImageLoader.getInstance().displayImage(mRecipeList.get(position).getImage(), vimage, new ImageLoadingListener() {
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


        return v;
    }
}
