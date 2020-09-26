package com.zqy.sui.other.adapter;

import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/11 11:39
 * 描述: Fragment Adapter
 */
public class FragmentStatePageAdapter extends FragmentStatePagerAdapter {
    private List<String> mTitles;
    private List<Fragment> mFragments;
    private boolean isDestroyItem;

    public FragmentStatePageAdapter(FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;

    }

    public FragmentStatePageAdapter(FragmentManager fm, List<String> mTitles, List<Fragment> mFragments) {
        super(fm);
        this.mTitles = mTitles;
        this.mFragments = mFragments;
    }


    public void setDestroyItem(boolean destroyItem) {
        isDestroyItem = destroyItem;
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles != null)
            return mTitles.get(position);
        return "";
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (isDestroyItem) {
            super.destroyItem(container, position, object);
        }
    }

    public List<String> getTitles() {
        return mTitles;
    }

    public List<Fragment> getFragments() {
        return mFragments;
    }
}
