package com.aqinga.greendao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by
 * 张庆龄
 * 1506A
 * Administrator
 * 2017/10/1311:23
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    List<User> list;

    public MyAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null){
            holder = new ViewHolder();
            convertView = convertView.inflate(context,R.layout.item,null);
            holder.id = (TextView) convertView.findViewById(R.id.id);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        User user = list.get(position);
        holder.id.setText(String.valueOf(user.getId()));
        holder.content.setText(user.getName());
        return convertView;
    }

    private class ViewHolder {
        TextView id;
        TextView content;
    }
}
