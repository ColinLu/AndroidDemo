package com.colin.library.base;

import android.app.Activity;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public abstract class MyBaseAdapter<T> extends BaseAdapter {
    private SparseArray<Object> typeSArr = new SparseArray<>();

    protected Activity activity;
    protected LayoutInflater layoutInflater;
    protected List<T> itemList = new ArrayList<T>();
    protected int viewTypeCount = 1;//Item布局种类有几个

    public MyBaseAdapter(Activity activity, List<T> itemList) {
        this.activity = activity;
        this.itemList = itemList;
        this.layoutInflater = LayoutInflater.from(activity);
    }


    public MyBaseAdapter(Activity activity, List<T> itemList, int viewTypeCount) {
        this.activity = activity;
        this.itemList = itemList;
        this.layoutInflater = LayoutInflater.from(activity);
        this.viewTypeCount = viewTypeCount;
    }

    /**
     * 判断数据是否为空
     *
     * @return 为空返回true，不为空返回false
     */
    public boolean isEmpty() {
        return getCount() > 0 ? false : true;
    }

    /**
     * 在原有的数据上添加新数据
     *
     * @param itemList
     */
    public void addItems(List<T> itemList) {
        if (itemList != null && itemList.size() > 0) {
            for (T t : itemList) {
                if (!this.itemList.contains(t)) {
                    this.itemList.add(t);
                }
            }
            notifyDataSetChanged();
        }
    }

    /**
     * 设置为新的数据，旧数据会被清空
     * @param itemList
     */
    public void refreshItems(List<T> itemList) {
        if (itemList != null && itemList.size() > 0) {
            this.itemList.clear();
        }
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clearItems() {
        if (itemList != null && itemList.size() > 0) {
            this.itemList.clear();
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return itemList == null ? 0 : itemList.size();
    }

    @Override
    public T getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * instead by{@link #getItemViewType(Object)}
     */
    @Override
    public int getItemViewType(int position) {
        Object mType = getItemViewType(itemList.get(position));
        // 如果不写这个方法，会让listView更换dataList后无法刷新数据
        return getIntType(mType);
    }

    public Object getItemViewType(T t) {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return viewTypeCount;
    }

    @Override
    abstract public View getView(int position, View convertView, ViewGroup viewGroup);


    /**
     * @param type item的类型
     * @return 通过object类型的type来得到int类型的type
     */
    public int getIntType(Object type) {
        int index = typeSArr.indexOfValue(type);
        if (index == -1) {
            index = typeSArr.size();
            // 如果没用这个type，就存入这个type
            typeSArr.put(index, type);
        }
        return index;
    }
}
