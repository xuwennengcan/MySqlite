package com.can.mysqlite.bean;

/**
 * Created by can on 2018/2/26.
 * 消息实体类
 */

public class MessageBean {

    private String noeTitle;
    private String noeTime;
    private String isRead;


    public MessageBean(String noeTitle, String noeTime, String isRead) {
        this.noeTitle = noeTitle;
        this.noeTime = noeTime;
        this.isRead = isRead;
    }

    public String getNoeTitle() {
        return noeTitle;
    }

    public void setNoeTitle(String noeTitle) {
        this.noeTitle = noeTitle;
    }

    public String getNoeTime() {
        return noeTime;
    }

    public void setNoeTime(String noeTime) {
        this.noeTime = noeTime;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}
