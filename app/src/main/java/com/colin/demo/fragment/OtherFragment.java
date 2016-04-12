package com.colin.demo.fragment;

import android.os.Bundle;

import com.colin.demo.R;
import com.colin.library.base.BaseFragment;

public class OtherFragment extends BaseFragment {
    private static final String FRAGMENT_ID = "fragment_id";
    private static final String TITLE = "title";

    private int fragment_id;
    private String title;


    public OtherFragment() {
    }

    public static OtherFragment newInstance(int fragment_id, String title) {
        OtherFragment fragment = new OtherFragment();
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
        return R.layout.fragment_other;
    }

    @Override
    public void initView() {

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
