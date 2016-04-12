package com.colin.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.colin.demo.R;
import com.colin.demo.bean.ItemBean;
import com.colin.library.base.BaseFragment.OnFragmentListener;

import java.util.List;

public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<ItemBean> itemBeanList;
    private final int fragment_tag;
    private final OnFragmentListener mListener;

    public MyItemRecyclerViewAdapter(List<ItemBean> itemBeanList) {
        this.fragment_tag = -1;
        this.itemBeanList = itemBeanList;
        this.mListener = null;
    }

    public MyItemRecyclerViewAdapter(List<ItemBean> itemBeanList, OnFragmentListener listener) {
        this.fragment_tag = -1;
        this.itemBeanList = itemBeanList;
        this.mListener = listener;
    }

    public MyItemRecyclerViewAdapter(int fragment_tag, List<ItemBean> itemBeanList, OnFragmentListener listener) {
        this.fragment_tag = fragment_tag;
        this.itemBeanList = itemBeanList;
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fragment_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final ItemBean itemBean = this.itemBeanList.get(position);
        holder.text_fragment_list_content.setText(itemBean.getTitle() + ":" + itemBean.getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onFragmentClick(fragment_tag, position, true, itemBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.itemBeanList == null ? 0 : itemBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView text_fragment_list_content;
        public ItemBean itemBean;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            text_fragment_list_content = (TextView) view.findViewById(R.id.text_fragment_list_content);
        }
    }
}
