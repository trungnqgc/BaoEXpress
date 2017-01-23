package com.example.merrychistmasnguyenquangtrung2016.docbaoexpress;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.Utils.XMLDOMParse;
import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.controller.NewsAdapter;
import com.example.merrychistmasnguyenquangtrung2016.docbaoexpress.model.NewsModel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
   public static ArrayList<NewsModel> list;
   NewsAdapter adapter=null;
    ListView lv;
    String  cate="";
    int current_positionCate;
    public static final int REQUEST_CODE=100;
     BroadcastReceiver broadcastReceiver=new BroadcastReceiver() {
         @Override
         public void onReceive(Context context, Intent intent) {
             ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
             //lay ra thong tin cua connecton
             NetworkInfo wifi=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
             NetworkInfo data3G=connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

             boolean isWifi=wifi.isConnectedOrConnecting();
             boolean is3G=data3G.isConnectedOrConnecting();
             boolean connec=is3G || isWifi;
             if(connec==true){
                 lv.setEnabled(true);
                 getData();

             }
             else {
                 Intent in=new Intent(MainActivity.this,NotConnectionErr.class);
                 startActivity(in);
             }
         }
     };

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.list_view);
        list=new ArrayList<>();
        Intent intent=getIntent();
        cate=intent.getExtras().getString("cate");
        current_positionCate=intent.getExtras().getInt("position_listcate");
        lv.setOnItemClickListener(this);


    }

    private void getData() {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ReadNews rn=new ReadNews();
                rn.execute(cate);
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(MainActivity.this,Infor_New.class);
        intent.putExtra("position",i);
        intent.putExtra("i",current_positionCate);
        startActivityForResult(intent,REQUEST_CODE);

    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK){
           cate=data.getExtras().getString("linkpath");
            current_positionCate=data.getExtras().getInt("po");
            getData();
            adapter.notifyDataSetChanged();

        }
    }
    */


    public class ReadNews extends AsyncTask<String,Void,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            ArrayList<NewsModel> list=new ArrayList<>();
            StringBuilder builder=new StringBuilder();
            try {
                URL url=new URL(strings[0]);
                URLConnection connection=url.openConnection();
                InputStream inputStream=connection.getInputStream();
                BufferedReader bf=new BufferedReader(new InputStreamReader(inputStream));

                String line="";
                while ((line=bf.readLine()) != null){
                    builder.append(line);
                }


                inputStream.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }




            return builder.toString();

        }

        @Override
        protected void onPostExecute(String s) {


           XMLDOMParse domparser=new XMLDOMParse();
            Document document=domparser.getDocument(s);

            NodeList nodeList=document.getElementsByTagName("item");
            NodeList nodelistdescription=document.getElementsByTagName("description");
            String hinhanh="";
            for (int i=0;i<nodeList.getLength();i++){
                String cdata=nodelistdescription.item(i+1).getTextContent();
                Pattern pattern=Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher=pattern.matcher(cdata);
                if(matcher.find()){
                     hinhanh=matcher.group(1);

                }
                Element element= (Element) nodeList.item(i);
               String title = domparser.getValue(element,"title");
                String linhk=domparser.getValue(element,"link");
               list.add(new NewsModel(title,linhk,hinhanh));

            }
            adapter=new NewsAdapter(MainActivity.this,list);
            lv.setAdapter(adapter);

            super.onPostExecute(s);


        }
    }

}
