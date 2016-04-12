package com.colin.demo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.List;


public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    private List<String> titleList;
    private FragmentManager fragmentManager;

    public MyFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.fragmentList = fragmentList;
    }

    public MyFragmentPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragmentList, List<String> titleList) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        this.fragmentList = fragmentList;
        this.titleList = titleList;
    }

    @Override
    public int getCount() {
        return fragmentList == null ? 0 : fragmentList.size();
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position % fragmentList.size());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList == null ? "无题" : titleList.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
