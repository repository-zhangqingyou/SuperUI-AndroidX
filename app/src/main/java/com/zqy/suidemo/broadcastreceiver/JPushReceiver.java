package com.zqy.suidemo.broadcastreceiver;

import android.content.Context;
import android.util.Log;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;

//
public class JPushReceiver extends JPushMessageReceiver {
    private String TAG = "TalkReceiver";


    /**
     * 收到自定义消息回调  官方消息
     *
     * @param context
     * @param customMessage
     */
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);
        Log.d(TAG, customMessage.toString());
//
//        customMessage.extra;
//        customMessage.message;
//        customMessage.contentType;
//        customMessage.title;
//        customMessage.title;



    }

    /**
     * 收到通知回调
     *
     * @param context
     * @param notificationMessage
     */
    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);
    }

    /**
     * 点击通知回调
     *
     * @param context
     * @param notificationMessage
     */
    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageOpened(context, notificationMessage);

    }


}
