package com.example.thachchibao.homework3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.List;

/**
 * Created by ThachChiBao on 2/24/2017.
 */


public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private List<Recipe> mRecipeList;
    // Contructor


    public MyAdapter(Context mContext, List<Recipe> mRecipeList) {
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
        ImageView image = (ImageView)v.findViewById(R.id.vimage);
        final ProgressBar progressBar = (ProgressBar)v.findViewById(R.id.progressBar);
        TextView name = (TextView)v.findViewById(R.id.tvname);
        LinearLayout ln = (LinearLayout)v.findViewById(R.id.detail1);
        ImageButton bt = (ImageButton) v.findViewById(R.id.detail2);
        // set texts
        name.setText(mRecipeList.get(position).getName());
        ImageLoader.getInstance().displayImage(mRecipeList.get(position).getImage(), image, new ImageLoadingListener() {
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

        final String image2 = mRecipeList.get(position).getImage2();

        final String R_name = mRecipeList.get(position).getName();
        final String R_introduction = mRecipeList.get(position).getIntroduction();
        final String R_time = mRecipeList.get(position).getTime();
        final String R_ingredient = mRecipeList.get(position).getIngredient();
        final String R_direction = mRecipeList.get(position).getDirection();
        final String R_image1 = mRecipeList.get(position).getImage();




        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i = new Intent(v2.getContext(),Detail1.class);
                i.putExtra("R_name",R_name );
                i.putExtra("R_introduction",R_introduction);
                i.putExtra("R_time",R_time);
                i.putExtra("R_ingredient",R_ingredient);
                i.putExtra("R_direction", R_direction);
                i.putExtra("R_image1",R_image1);

                v2.getContext().startActivity(i);
            }
        });


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v2) {
                Intent i = new Intent(v2.getContext(),Detail2.class);
                i.putExtra("image2", image2);
                v2.getContext().startActivity(i);
            }
        });


        return v;
    }


}

