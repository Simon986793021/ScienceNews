package com.wind.okhttp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wind.okhttp.model.NewsBean;

import java.util.List;

/**
 * Created by zhangcong on 2017/4/25.
 */

//adapter 适配数据
public class NewsListBaseAdapter extends BaseAdapter {
    public static List<NewsBean.Second.Third> list;
    private Context context;
    public NewsListBaseAdapter(List<NewsBean.Second.Third> list,Context context)
    {
        this.context=context;
        this.list=list;
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
        ViewHolder viewHolder=null;
        if (convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_new_list,null,false);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.iv_news_list_image);
            viewHolder.title= (TextView) convertView.findViewById(R.id.tv_news_list_title);
            viewHolder.author= (TextView) convertView.findViewById(R.id.tv_news_list_realtype);
            viewHolder.time= (TextView) convertView.findViewById(R.id.tv_news_list_time);
            convertView.setTag(viewHolder);
        }
        viewHolder= (ViewHolder) convertView.getTag();
        Glide.with(context).load(list.get(position).thumbnail_pic_s).into(viewHolder.imageView);//Glide 加载图片
        viewHolder.title.setText(list.get(position).title);
        viewHolder.time.setText(list.get(position).date);
        viewHolder.author.setText(list.get(position).author_name);
        return convertView;
    }
    class ViewHolder
    {
        private ImageView imageView;
        private TextView title;
        private TextView author;
        private TextView time;
    }
}
