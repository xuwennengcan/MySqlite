package com.can.mysqlite.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.can.mysqlite.R;
import com.can.mysqlite.bean.MessageBean;

import java.util.List;

/**
 * Created by can on 2018/2/26.
 * 消息适配器
 */

public class MessageAdapter extends BaseAdapter {

    private Context mContext;
    private List<MessageBean> mList;
    public MessageAdapter(Context context,List<MessageBean> list){
        mContext = context;
        mList = list;
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        VH vh;
        if(view==null){
            vh = new VH();
            view = LayoutInflater.from(mContext).inflate(R.layout.item_message,null);
            vh.tv_isRead = view.findViewById(R.id.tv_isRead);
            vh.tv_time = view.findViewById(R.id.tv_noeTime);
            vh.tv_title = view.findViewById(R.id.tv_noeTitle);
            view.setTag(vh);
        }else{
            vh = (VH) view.getTag();
        }
        MessageBean bean = mList.get(i);
        String isRead = bean.getIsRead();
        String title = bean.getNoeTitle()+"";
        String time = bean.getNoeTime()+"";
        if(isRead.equals("1")){
            vh.tv_isRead.setText("已读");
            vh.tv_isRead.setTextColor(Color.parseColor("#888888"));
        }else{
            vh.tv_isRead.setText("未读");
            vh.tv_isRead.setTextColor(Color.parseColor("#00aa00"));
        }
        vh.tv_title.setText(title);
        vh.tv_time.setText(time);
        return view;
    }

    static class VH{
        private TextView tv_title,tv_time,tv_isRead;
    }


}
