package com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.R;
import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.model.NewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dell on 1/20/2017.
 */

public class NewsAdapter extends BaseAdapter {
    ArrayList<NewsModel> getNews;
    Context context;

    public NewsAdapter(Context context, ArrayList<NewsModel> getNews) {
        this.context = context;
        this.getNews = getNews;
    }

    @Override
    public int getCount() {
        return getNews.size();
    }

    @Override
    public Object getItem(int i) {
        return getNews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView=view;
        if(rowView==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            rowView=inflater.inflate(R.layout.item_new,viewGroup,false);
    }
        TextView title=(TextView)rowView.findViewById(R.id.title_news);
        ImageView img=(ImageView)rowView.findViewById(R.id.image_new);
        title.setText(getNews.get(i).getTitle());
        Picasso.with(context).load(getNews.get(i).getImagePath()).into(img);
        return rowView;
    }
}
