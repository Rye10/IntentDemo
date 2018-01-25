package com.example.intentdemo;

import android.os.Message;

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

public class GetData extends Thread{
    private static final String PATH="http://jsonplaceholder.typicode.com/users";
    @Override
    public void run() {
        List<JsonBean> jsonBeanList=new ArrayList<>();
        try {
            URL url=new URL(PATH);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            if (connection.getResponseCode()==200){
                InputStream in=connection.getInputStream();
                InputStreamReader isr=new InputStreamReader(in,"utf-8");
                String line="";
                String result="";
                BufferedReader br=new BufferedReader(isr);
                while ((line=br.readLine())!=null){
                    result+=line;
                }
                JsonBean jsonBean;
                JSONArray jsonArray=new JSONArray(result);
                for (int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    jsonBean=new JsonBean();
                    jsonBean.id=jsonObject.getInt("id");
                    jsonBean.name=jsonObject.getString("name");
                    jsonBean.user=jsonObject.getString("username");
                    jsonBeanList.add(jsonBean);
                }
                Message msg=new Message();
                msg.obj=jsonBeanList;
                MainActivity.handler.sendMessage(msg);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
