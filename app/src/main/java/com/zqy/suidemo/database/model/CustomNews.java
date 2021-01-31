package com.zqy.suidemo.database.model;//package com.zqy.superutils.database.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;


/**
 * 作者: zhangqingyou
 * 时间: 2020/7/20 10:50
 * 描述:消息-自定义消息
 */
@Entity
public class CustomNews implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;
    @Id(autoincrement = true)//主键自动增长
    private Long id;//自定义消息ID 必须为Long 否则不能自增长
    private String messageId;//自定义消息ID
    private int msgType;//消息类型
    private long msgTime;//消息时间
    private String contentType;//类容类型
    private String title;//消息标题
    private String message;//消息
    private String extra;//扩展消息
    private boolean isRead;//是否已读

    @Generated(hash = 756270220)
    public CustomNews(Long id, String messageId, int msgType, long msgTime,
            String contentType, String title, String message, String extra, boolean isRead) {
        this.id = id;
        this.messageId = messageId;
        this.msgType = msgType;
        this.msgTime = msgTime;
        this.contentType = contentType;
        this.title = title;
        this.message = message;
        this.extra = extra;
        this.isRead = isRead;
    }

    @Generated(hash = 51335759)
    public CustomNews() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean getIsRead() {
        return this.isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }
}
