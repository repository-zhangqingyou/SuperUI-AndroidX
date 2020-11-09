package com.zqy.superutils.database.model;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * 作者: zhangqingyou
 * 时间: 2020/7/20 10:50
 * 描述:消息-自定义消息
 * 如果您不想将模型中的字段保存到其领域，请使用注释@Ignore
 */
public class CustomNews extends RealmObject implements Serializable {
    @PrimaryKey // 设置为主键
    private String messageId;//自定义消息ID
    private int msgType;//消息类型
    private long msgTime;//消息时间
    private String contentType;//类容类型
    private String title;//消息标题
    private String message;//消息
    private String extra;//扩展消息
    private boolean isRead;//是否已读

    public long getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(long msgTime) {
        this.msgTime = msgTime;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public int getMsgType() {
        return msgType;
    }

    public void setMsgType(int msgType) {
        this.msgType = msgType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
