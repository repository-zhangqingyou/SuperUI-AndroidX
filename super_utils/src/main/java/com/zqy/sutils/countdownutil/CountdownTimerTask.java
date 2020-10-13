package com.zqy.sutils.countdownutil;

import android.os.Handler;
import android.os.Looper;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Author: zhangqingyou
 * Date: 2020/4/7
 * Des:
 */
public class CountdownTimerTask {

    /**
     * 倒计时执行
     *
     * @param runnable
     * @param delayTime 倒计时
     */
    private TimerTask stateCountdownExecutionTimerTask;

    public void stateCountdownExecution(final Runnable runnable, float delayTime) {
        closeTimerTask();
        stateCountdownExecutionTimerTask = new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(runnable);
            }
        };
        new Timer().schedule(stateCountdownExecutionTimerTask, (int) (delayTime * 1000));
    }

    /**
     * 关闭倒计时
     */
    private void closeTimerTask() {
        if (stateCountdownExecutionTimerTask != null) {
            stateCountdownExecutionTimerTask.cancel();
        }
    }
}
