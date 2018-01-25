package com.example.intentdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    public static Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.listview);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                List list= (List) msg.obj;
                ItemAdapter itemAdapter=new ItemAdapter(MainActivity.this,list);                   //参数类型根据ItemAdapter构造时定义的
                listView.setAdapter(itemAdapter);
            }
        };
        GetData getData=new GetData();
        getData.start();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent=new Intent(MainActivity.this,JsonContent.class);
                        intent.putExtra("position",0);                                              //传递数据到JSON获取的线程中，position的取值和JSONArray中的index相关联
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1=new Intent(MainActivity.this,JsonContent.class);
                        intent1.putExtra("position",1);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2=new Intent(MainActivity.this,JsonContent.class);
                        intent2.putExtra("position",2);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3=new Intent(MainActivity.this,JsonContent.class);
                        intent3.putExtra("position",3);
                        startActivity(intent3);
                        break;
                    case 4:
                        Intent intent4=new Intent(MainActivity.this,JsonContent.class);
                        intent4.putExtra("position",4);
                        startActivity(intent4);
                        break;
                    case 5:
                        Intent intent5=new Intent(MainActivity.this,JsonContent.class);
                        intent5.putExtra("position",5);
                        startActivity(intent5);
                        break;
                    case 6:
                        Intent intent6=new Intent(MainActivity.this,JsonContent.class);
                        intent6.putExtra("position",6);
                        startActivity(intent6);
                        break;
                    case 7:
                        Intent intent7=new Intent(MainActivity.this,JsonContent.class);
                        intent7.putExtra("position",7);
                        startActivity(intent7);
                        break;
                    case 8:
                        Intent intent8=new Intent(MainActivity.this,JsonContent.class);
                        intent8.putExtra("position",8);
                        startActivity(intent8);
                        break;
                    case 9:
                        Intent intent9=new Intent(MainActivity.this,JsonContent.class);
                        intent9.putExtra("position",9);
                        startActivity(intent9);
                        break;
                }
            }
        });
    }
}
