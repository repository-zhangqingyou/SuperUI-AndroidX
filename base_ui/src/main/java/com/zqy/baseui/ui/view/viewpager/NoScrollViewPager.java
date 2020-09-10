package com.zqy.baseui.ui.view.viewpager;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * Android 禁止 viewpager 左右滑动
 */
public class NoScrollViewPager extends ViewPager {
    // 是否禁止 viewpager 左右滑动
    private boolean noScroll = true;
    private boolean requestDisallowInterceptTouchEvent = true;//默认 父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
    /**
     * 触摸时按下的点
     **/
    private PointF downP = new PointF();
    /**
     * 触摸时当前的点
     **/
    private PointF curP = new PointF();

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setNoScroll(boolean noScroll) {
        this.noScroll = noScroll;
    }

    public void setRequestDisallowInterceptTouchEvent(boolean requestDisallowInterceptTouchEvent) {
        this.requestDisallowInterceptTouchEvent = requestDisallowInterceptTouchEvent;
    }


    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
//        if (noScroll){
//            return false;
//        }else{
//            return super.onTouchEvent(arg0);
//        }
        // TODO Auto-generated method stub
        //每次进行onTouch事件都记录当前的按下的坐标
        curP.x = arg0.getX();
        curP.y = arg0.getY();

        if (arg0.getAction() == MotionEvent.ACTION_DOWN) {
            //记录按下时候的坐标
            //切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
            downP.x = arg0.getX();
            downP.y = arg0.getY();
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(requestDisallowInterceptTouchEvent);
        }

        if (arg0.getAction() == MotionEvent.ACTION_MOVE) {
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(requestDisallowInterceptTouchEvent);
        }

        if (arg0.getAction() == MotionEvent.ACTION_UP) {
            //在up时判断是否按下和松手的坐标为一个点
            //如果是一个点，将执行点击事件，这是我自己写的点击事件，而不是onclick
            if (downP.x == curP.x && downP.y == curP.y) {
                // onSingleTouch();
                return true;
            }
        }

        return super.onTouchEvent(arg0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if (noScroll) {
            return false;
        } else {
            return super.onInterceptTouchEvent(arg0);
        }
    }

}
