package com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.Category_New;
import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.R;
import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.model.CategoryNew;

import java.util.ArrayList;

/**
 * Created by dell on 1/20/2017.
 */

public class CategoryAdapter extends BaseAdapter {
    ArrayList<CategoryNew> list;
    Context context;

    public CategoryAdapter(Context context, ArrayList<CategoryNew> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View cateView=view;
        if(cateView==null){
            LayoutInflater inflater=LayoutInflater.from(context);
            cateView=inflater.inflate(R.layout.item_categoty,viewGroup,false);

        }
        TextView tv=(TextView)cateView.findViewById(R.id.tv_title);
        tv.setText(list.get(i).getTitle());
        return cateView;
    }
}
