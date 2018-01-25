package com.example.intentdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rye on 2018/1/25.
 */

public class JsonContent extends AppCompatActivity {
    private static final String PATH="http://jsonplaceholder.typicode.com/users";
    private ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        getData();
    }

    private void getData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(PATH);
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    if (connection.getResponseCode()==200){
                        List list=new ArrayList();
                        Intent intent=getIntent();
                        int position=intent.getIntExtra("position",0);                   //获取MainActivity传递过来的数据，改变position值
                        InputStream in=connection.getInputStream();
                        InputStreamReader isr=new InputStreamReader(in,"utf-8");
                        String line="";
                        String result="";
                        BufferedReader br=new BufferedReader(isr);
                        while ((line=br.readLine())!=null){
                            result+=line;
                        }
                        JSONArray jsonArray=new JSONArray(result);
//                        JSONObject jsonObject=jsonArray.getJSONObject(0);
                        JSONObject jsonObject=jsonArray.getJSONObject(position);                                //listview中的item position与JSONArray中的index相关联，获取不同index中的数据
                        String nam=jsonObject.getString("name");
                        String email=jsonObject.getString("email");
                        String phone=jsonObject.getString("phone");
                        String website=jsonObject.getString("website");
                        JSONObject address=jsonObject.getJSONObject("address");
                        String street=address.getString("street");
                        String suite=address.getString("suite");
                        String city=address.getString("city");
                        String zipcode=address.getString("zipcode");
                        JSONObject company=jsonObject.getJSONObject("company");
                        String name=company.getString("name");
                        String catchPhrase=company.getString("catchPhrase");
                        String bs=company.getString("bs");
                        list.add("姓名:"+nam);
                        list.add("邮箱:"+email);
                        list.add("电话:"+phone);
                        list.add("网站:"+website);
                        list.add("地址:"+street+" street "+suite+" "+city+" city");
                        list.add("邮编:"+zipcode);
                        list.add("公司名:"+name);
                        list.add("广告语:"+catchPhrase);
                        list.add("简介:"+bs);
                        Message msg=new Message();
                        msg.obj=list;
                        handler.sendMessage(msg);

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            List list= (List) msg.obj;
            ArrayAdapter adapter=new ArrayAdapter(JsonContent.this,android.R.layout.simple_list_item_1,list);
            listView.setAdapter(adapter);
        }
    };
}

