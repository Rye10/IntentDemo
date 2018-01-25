package com.example.intentdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by rye on 2018/1/25.
 */

public class ItemAdapter extends BaseAdapter {
    private List<JsonBean> list;
    private LayoutInflater inflater;
    public ItemAdapter(Context context, List<JsonBean> list){                         //重载也是构造方法（函数），与类名ItemAdapter相同，参数不一样
        this.inflater=LayoutInflater.from(context);                                  //对类的实例（对象）初始化
        this.list=list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.list_item,null);
            viewHolder.tvId=convertView.findViewById(R.id.id);
            viewHolder.tvName=convertView.findViewById(R.id.name);
            viewHolder.tvUser=convertView.findViewById(R.id.username);
            convertView.setTag(viewHolder);                                           //setTag表示给convertView添加一个额外的数据viewHolder，与convertView绑定
        } else{
            viewHolder= (ViewHolder) convertView.getTag();                            //如果convertView不为null，直接可以获取之前绑定的数据getTag到viewHolder中
        }
        viewHolder.tvId.setText(list.get(position).id+"");
        viewHolder.tvName.setText("姓名:"+list.get(position).name);
        viewHolder.tvUser.setText("用户名:"+list.get(position).user);
        return convertView;
    }
    class ViewHolder{
        TextView tvId;
        TextView tvName;
        TextView tvUser;
    }
}
