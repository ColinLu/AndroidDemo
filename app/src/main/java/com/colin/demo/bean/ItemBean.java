package com.colin.demo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/1/28.
 */
public class ItemBean extends Object implements Parcelable {
    private String title;
    private String name;
    private String target;//包名与类名
    private int position;

    public ItemBean() {
    }

    public ItemBean(String title, String name, String target, int position) {
        this.title = title;
        this.name = name;
        this.target = target;
        this.position = position;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    protected ItemBean(Parcel in) {
        title = in.readString();
        name = in.readString();
        target = in.readString();
        position = in.readInt();
    }

    public static final Creator<ItemBean> CREATOR = new Creator<ItemBean>() {
        @Override
        public ItemBean createFromParcel(Parcel in) {
            return new ItemBean(in);
        }

        @Override
        public ItemBean[] newArray(int size) {
            return new ItemBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(name);
        dest.writeString(target);
        dest.writeInt(position);
    }
}
