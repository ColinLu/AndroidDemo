package com.colin.demo.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.colin.demo.R;
import com.colin.library.base.BaseFragment;

public class MineFragment extends BaseFragment {
    private static final String FRAGMENT_ID = "fragment_id";
    private static final String TITLE = "title";

    private int fragment_id;
    private String title;

    private TextView text_fragment_mine_title;

    public MineFragment() {
    }

    public static MineFragment newInstance(int fragment_id, String title) {
        MineFragment fragment = new MineFragment();
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
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
        this.text_fragment_mine_title = (TextView) view.findViewById(R.id.text_fragment_mine_title);
        this.text_fragment_mine_title.setText(title);
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
