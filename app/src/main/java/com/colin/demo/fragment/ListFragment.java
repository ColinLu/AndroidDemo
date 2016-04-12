package com.colin.demo.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.colin.demo.R;
import com.colin.demo.adapter.MyItemRecyclerViewAdapter;
import com.colin.demo.bean.ItemBean;
import com.colin.demo.data.Constant;
import com.colin.library.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseFragment {

    private static final String FRAGMENT_ID = "fragment_id";
    private static final String TITLE = "title";

    private int fragment_id;
    private String title;

    private RecyclerView recycler_fragment_item_course;
    private List<ItemBean> itembeanList = null;
    private MyItemRecyclerViewAdapter myItemRecyclerViewAdapter = null;

    public static ListFragment newInstance(int fragment_id, String title) {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_ID, fragment_id);
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fragment_id = getArguments().getInt(FRAGMENT_ID);
            title = getArguments().getString(TITLE);
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_list;
    }

    @Override
    public void initView() {
        recycler_fragment_item_course = (RecyclerView) view.findViewById(R.id.recycler_fragment_item_course);
        initRecylerView();
    }

    private void initRecylerView() {
        initItemData();
        if (myItemRecyclerViewAdapter == null) {
            myItemRecyclerViewAdapter = new MyItemRecyclerViewAdapter(Constant.FRAGMENT_LIST, itembeanList, onFragmentListener);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_fragment_item_course.setLayoutManager(layoutManager);
        recycler_fragment_item_course.setAdapter(myItemRecyclerViewAdapter);
    }

    private void initItemData() {
        if (itembeanList == null) {
            itembeanList = new ArrayList<>();
        }
        itembeanList.clear();
        String[] array_item = null;
        if (fragment_id == Constant.FRAGMENT_VIEW_ID_BASE) {
            array_item = activity.getResources().getStringArray(R.array.ListFragment_View_Base);
        } else if (fragment_id == Constant.FRAGMENT_VIEW_ID_HIGH) {
            array_item = activity.getResources().getStringArray(R.array.ListFragment_View_High);
        } else if (fragment_id == Constant.FRAGMENT_VIEW_ID_NEW) {
            array_item = activity.getResources().getStringArray(R.array.ListFragment_View_New);
        } else if (fragment_id == Constant.FRAGMENT_VIEW_ID_CUSTOM) {
            array_item = activity.getResources().getStringArray(R.array.ListFragment_View_Custom);
        }
        ItemBean itemBean = null;
        if (!TextUtils.isEmpty(array_item.toString()) && array_item.length > 0) {
            for (String s : array_item) {
                String[] item_string = s.split(",");
                itemBean = new ItemBean(item_string[0], item_string[1], item_string[2], fragment_id);
                itembeanList.add(itemBean);
            }
        }


    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void getAsynData() {

    }

    @Override
    public void destroyView() {

    }
}
