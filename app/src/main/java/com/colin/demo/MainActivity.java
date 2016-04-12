package com.colin.demo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.colin.demo.adapter.MyFragmentPagerAdapter;
import com.colin.demo.bean.ItemBean;
import com.colin.demo.custom.view.activity.CustomViewActivity;
import com.colin.demo.data.Constant;
import com.colin.demo.fragment.MineFragment;
import com.colin.demo.fragment.ViewFragment;
import com.colin.library.base.BaseAppCompatActivity;
import com.colin.library.base.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseAppCompatActivity implements View.OnClickListener, BaseFragment.OnFragmentListener {
    private FloatingActionButton floatingActionButton;
    private ViewPager pager_main_content;
    private RadioGroup radio_group_items;
    private int current_position = -1;
    private List<Fragment> fragmentList = null;
    private MyFragmentPagerAdapter adapter = null;
    private SearchView action_search;

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        this.pager_main_content = (ViewPager) this.findViewById(R.id.pager_main_content);
        this.radio_group_items = (RadioGroup) this.findViewById(R.id.radio_group_items);
        initViewPager();
    }

    private void initViewPager() {
        if (fragmentList == null) {
            fragmentList = new ArrayList<>();
        }
        fragmentList.clear();

        Fragment viewFragment = ViewFragment.newInstance(Constant.FRAGMENT_MAIN_ID_VIEW, Constant.TAB_MAIN_FRAGMENT_TITLE[0]);
        Fragment methodFragment = MineFragment.newInstance(Constant.FRAGMENT_MAIN_ID_METHOD, Constant.TAB_MAIN_FRAGMENT_TITLE[1]);
        Fragment otherFragment = MineFragment.newInstance(Constant.FRAGMENT_MAIN_ID_OTHER, Constant.TAB_MAIN_FRAGMENT_TITLE[2]);
        Fragment mineFragment = MineFragment.newInstance(Constant.FRAGMENT_MAIN_ID_MINE, Constant.TAB_MAIN_FRAGMENT_TITLE[3]);

        fragmentList.add(viewFragment);
        fragmentList.add(methodFragment);
        fragmentList.add(otherFragment);
        fragmentList.add(mineFragment);

        if (adapter == null) {
            adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, Arrays.asList(Constant.TAB_MAIN_FRAGMENT_TITLE));
        }
        pager_main_content.setAdapter(adapter);

        select(0);
    }

    private void select(int i) {
        if (current_position != i) {
            current_position = i;
            pager_main_content.setCurrentItem(current_position, false);
            //中间加了下划线
            ((RadioButton) radio_group_items.getChildAt(current_position * 2)).setChecked(true);
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        floatingActionButton.setOnClickListener(this);
        pager_main_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                select(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        this.radio_group_items.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int page = -1;
                if (checkedId == R.id.radio_bottm_title_view) {
                    page = 0;
                } else if (checkedId == R.id.radio_bottm_title_method) {
                    page = 1;
                } else if (checkedId == R.id.radio_bottm_title_other) {
                    page = 2;
                } else if (checkedId == R.id.radio_bottm_title_mine) {
                    page = 3;
                }
                select(page);
            }
        });
    }

    @Override
    public void getAsynData() {

    }

    @Override
    public void destroy() {
        floatingActionButton = null;
        pager_main_content = null;
        fragmentList = null;
        current_position = -1;
        adapter = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(CustomViewActivity.class, null, false);
                            }
                        }).show();
                break;
        }
    }

    @Override
    public void onFragmentClick(int fragment_id, int position, boolean b, Object object) {
        Bundle bundle = null;
        if (b) {
            switch (fragment_id) {
                case Constant.FRAGMENT_LIST:
                    ItemBean itemBean = (ItemBean) object;
                    bundle = new Bundle();
                    bundle.putParcelable("class", ((ItemBean) object));
//                    startActivity(itemBean.getTarget(), bundle, false);
                    break;
            }
        }
    }
}
