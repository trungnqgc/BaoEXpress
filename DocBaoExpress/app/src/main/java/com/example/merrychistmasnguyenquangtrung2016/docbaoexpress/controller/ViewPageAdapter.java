package com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.R;
import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.model.NewsModel;

import java.util.ArrayList;

/**
 * Created by dell on 1/22/2017.
 */

public class ViewPageAdapter extends PagerAdapter {
    ArrayList<NewsModel> arr;
    Context context;

    public ViewPageAdapter(Context context, ArrayList<NewsModel> arr) {
        this.context = context;
        this.arr = arr;
    }

    @Override
    public int getCount() {
        return arr.size();
    }

    //neu view dda co roi thi dung lai khong khoi tao nua.tai du dung view
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    //giai phong bo nho cho no khi khoong hien thi len nua


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

       container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.view_page,container,false);
        WebView webview =(WebView)view.findViewById(R.id.webview);
        final ProgressDialog dialog=ProgressDialog.show(context,"Loadding","Đang load vui lòng chờ....");
        webview.getSettings().getJavaScriptEnabled();
        webview.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dialog.dismiss();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(context,"Không thể load được website",Toast.LENGTH_LONG).show();
                super.onReceivedError(view, request, error);

            }
        });
        webview.loadUrl(arr.get(position).getPath());
        container.addView(view);
        return view;
    }
}
