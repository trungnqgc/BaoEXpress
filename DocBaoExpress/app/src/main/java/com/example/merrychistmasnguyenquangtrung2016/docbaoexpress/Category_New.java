package com.example.merrychistmasnguyenquangtrung2016.docbaoexpress;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.controller.CategoryAdapter;
import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.model.CategoryNew;

import java.util.ArrayList;

public class Category_New extends AppCompatActivity implements AdapterView.OnItemClickListener {
ListView lis_view;
 public static ArrayList<CategoryNew> list_Categoty;
    CategoryAdapter adapter;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category__new);
        lis_view=(ListView)findViewById(R.id.category);
        list_Categoty=new ArrayList<>();
        list_Categoty.addAll(getList());
        adapter=new CategoryAdapter(this,list_Categoty);
        lis_view.setAdapter(adapter);
        lis_view.setOnItemClickListener(this);

    }

    public ArrayList<CategoryNew> getList(){
        ArrayList<CategoryNew> arr=new ArrayList<>();
        arr.add(new CategoryNew("Thời sự ","http://vnexpress.net/rss/thoi-su.rss"));
        arr.add(new CategoryNew("Thế giới ","http://vnexpress.net/rss/the-gioi.rss"));
        arr.add(new CategoryNew("Kinh doanh ","http://vnexpress.net/rss/kinh-doanh.rss"));
        arr.add(new CategoryNew("Giải trí ","http://vnexpress.net/rss/giai-tri.rss"));
        arr.add(new CategoryNew("Thể thao ","http://vnexpress.net/rss/the-thao.rss"));
        arr.add(new CategoryNew("Pháp luật ","http://vnexpress.net/rss/phap-luat.rss"));
        arr.add(new CategoryNew("Giáo dục","http://vnexpress.net/rss/giao-duc.rss"));
        arr.add(new CategoryNew("Sức khỏe ","http://vnexpress.net/rss/suc-khoe.rss"));
        return arr;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(this,MainActivity.class);
        intent.putExtra("cate",list_Categoty.get(i).getPath().toString());
        intent.putExtra("position_listcate",i);
        startActivity(intent);
    }
}
