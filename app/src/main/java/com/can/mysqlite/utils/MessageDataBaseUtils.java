package com.can.mysqlite.utils;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.can.mysqlite.bean.MessageBean;
import com.can.mysqlite.database.DataBaseManager;
import com.can.mysqlite.database.MessageDataOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by can on 2018/2/26.
 *  消息数据库的操作方法
 */


public class MessageDataBaseUtils {

    private static SQLiteDatabase db;
    private static Context context;

    public MessageDataBaseUtils(Context mContext){
        context = mContext;
        MessageDataOpenHelper dd = new MessageDataOpenHelper(context);
        DataBaseManager.initializeInstance(dd);
        db = DataBaseManager.getInstance().openDatabase();
    }

    /**
     * 关闭数据库
     */
    public  void closeDataBase(){
        if(db!=null&&db.isOpen())
            DataBaseManager.getInstance().closeDatabase();
    }

    /**
     * 获取数据库所有数据
     */
    public  List<MessageBean> getMessages(){
        List<MessageBean> list = new ArrayList<>();
        Cursor cursor = db.query("message", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String noeTitle = cursor.getString(cursor.getColumnIndex("noeTitle"));
            String noeTime = cursor.getString(cursor.getColumnIndex("noeTime"));
            String isRead = cursor.getString(cursor.getColumnIndex("isRead"));
            list.add(new MessageBean(noeTitle,noeTime,isRead));
        }
        cursor.close();
        return list;
    }

    /**
     * 添加数据
     */
    public  void addMessage(String noeTitle,String noeTime, String isRead){
        if(db!=null&&noeTitle!=null)
            db.execSQL("insert into message(noeTitle,noeTime,isRead) values(?,?,?)",new Object[]{noeTitle,noeTime,isRead});
    }

    /**
     * 查询数据
     */
    public  boolean searchMessage(String noeTitle){
        if(db!=null&&noeTitle!=null){
            Cursor cursor = db.rawQuery("select * from message where noeTitle=? ",new String[]{noeTitle});
            while (cursor.moveToNext()){
                cursor.close();
                return true;
            }
        }
        return false;
    }

    /**
     * 查询消息是否已读
     */
    public  boolean searchMessageIsRead(String noeTitle){
        if(db!=null&&noeTitle!=null){
            Cursor cursor = db.rawQuery("select * from message where noeTitle=? ",new String[]{noeTitle});
            while (cursor.moveToNext()){
                String isRead = cursor.getString(cursor.getColumnIndex("isRead"));
                cursor.close();
                if(isRead.equals("1")){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 设置消息已读
     */
    public void setMessageIsRead(String noeTitle) {
        if(db!=null&&noeTitle!=null){
            String sql = "update message set isRead=? where noeTitle=?";
            Object bindArgs[] = new Object[] { 1, noeTitle };
            db.execSQL(sql, bindArgs);
        }
    }

}
