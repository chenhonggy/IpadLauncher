package com.launcher.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.launcher.ipadlauncher.R;

import java.util.ArrayList;

/**
 * Created by chen on 14-7-12.
 */
public class LauncherAdapter extends BaseAdapter {

    private ArrayList<Integer> list;
    //	内部类实现BaseAdapter  ，自定义适配器
    private Context context;

    public LauncherAdapter(Context context,ArrayList<Integer> list)
    {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        // TODO Auto-generated method stub
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_launcher_item, null);
            holder = new ViewHolder();
            holder.img = (ImageView) convertView.findViewById(R.id.launcher_item_img);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.img.setBackgroundResource(list.get(position));

        return convertView;
    }

    //此类为上面getview里面view的引用，方便快速滑动
    class ViewHolder{
        ImageView img;
    }
}
