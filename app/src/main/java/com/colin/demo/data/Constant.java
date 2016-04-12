package com.colin.demo.data;

/**
 * Created by Administrator on 2016/3/9.
 */
public abstract class Constant {
    //主界面的标签
    // 列表Fragment：控件
    public static final int FRAGMENT_MAIN_ID_VIEW = 0X00000000;
    // 列表Fragment：方法
    public static final int FRAGMENT_MAIN_ID_METHOD = 0X00000001;
    // 列表Fragment：其他
    public static final int FRAGMENT_MAIN_ID_OTHER = 0X00000002;
    // 列表Fragment：我的
    public static final int FRAGMENT_MAIN_ID_MINE = 0X00000003;

    // 基本
    public static final int FRAGMENT_VIEW_ID_BASE = 0X00000004;
    // 高级
    public static final int FRAGMENT_VIEW_ID_HIGH = 0X00000005;
    // 最新
    public static final int FRAGMENT_VIEW_ID_NEW = 0X00000006;
    // 自定义
    public static final int FRAGMENT_VIEW_ID_CUSTOM = 0X00000007;

    //    List
    public static final int FRAGMENT_LIST = 0X00000008;

    public static final String[] TAB_MAIN_FRAGMENT_TITLE = new String[]{
            "控件", "方法", "其他", "我的"
    };
    public static final int[] TAB_MAIN_FRAGMENT_TITLE_ID = new int[]{
            FRAGMENT_MAIN_ID_VIEW,
            FRAGMENT_MAIN_ID_METHOD,
            FRAGMENT_MAIN_ID_OTHER,
            FRAGMENT_MAIN_ID_MINE
    };


    //职位管理的标签
    public static final String[] FRAGMENT_VIEW_TITLES = new String[]{
            "基础", "高级", "最新", "自定义"
    };

    public static final int[] FRAGMENT_VIEW_TITLE_IDS = new int[]{
            FRAGMENT_VIEW_ID_BASE,
            FRAGMENT_VIEW_ID_HIGH,
            FRAGMENT_VIEW_ID_NEW,
            FRAGMENT_VIEW_ID_CUSTOM
    };
}
