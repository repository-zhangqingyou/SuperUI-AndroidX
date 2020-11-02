package com.zqy.superui.core.other.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

/**
 * 作者: zhangqingyou
 * 时间: 2020/10/29 10:24
 * 描述:
 */
public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> viewList;

    public ViewPagerAdapter(ArrayList<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList.isEmpty() ? 0 : viewList.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        // TODO Auto-generated method stub
        // 刚开始用viewpager就直接写“return arg0 == arg1;”就好啦
        return arg0 == arg1;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));
        //每次滑动的时候把视图添加到viewpager
        return viewList.get(position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        // 将当前位置的View移除
        container.removeView(viewList.get(position));
    }
}
