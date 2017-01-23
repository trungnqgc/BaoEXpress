package com.example.merrychistmasnguyenquangtrung2016.docbaoexpress;

import android.app.ProgressDialog;
import android.app.backup.BackupDataOutput;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.controller.CategoryAdapter;
import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.controller.ViewPageAdapter;

public class Infor_New extends AppCompatActivity  {
    private ViewPager viewPager;
    ViewPageAdapter adapter;
    Spinner spinner;
    Intent intent;
    boolean f=false;
    CategoryAdapter spinAdapter=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor__new);
        viewPager=(ViewPager)findViewById(R.id.viewPage);
        spinner=(Spinner)findViewById(R.id.spinne);
        spinAdapter=new CategoryAdapter(this,Category_New.list_Categoty);
        adapter=new ViewPageAdapter(this,MainActivity.list);
        spinner.setAdapter(spinAdapter);
        viewPager.setAdapter(adapter);
        intent=getIntent();
        int link=intent.getExtras().getInt("position");
        int position=intent.getExtras().getInt("i");
        spinner.setSelection(position);



        viewPager.setCurrentItem(link);
        addEvent();

    }

    private void addEvent() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                   if(f==true){
                       Intent intent=new Intent(Infor_New.this,MainActivity.class);
                       intent.putExtra("cate",Category_New.list_Categoty.get(i).getPath());
                       intent.putExtra("position_listcate",i);
                       startActivity(intent);
                   }
                else{
                       f=true;

                   }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return super.onKeyDown(keyCode, event);
    }




}
