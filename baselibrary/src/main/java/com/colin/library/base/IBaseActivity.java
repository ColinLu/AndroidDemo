package com.colin.library.base;

/**
 * Created by Administrator on 2016/3/1.
 */
public interface IBaseActivity {


    /**
     * 绑定渲染视图的布局文件 （onCreate方法中调用）
     *
     * @return 布局文件资源id
     */
    int bindLayout();

    /**
     * （onCreate方法中调用）
     * <p/>
     * 初始化控件
     */
    void initView();

    /**
     * （onCreate方法中调用）
     * <p/>
     * 业务处理操作,数据加载到控件上
     */
    void initData();

    /**
     * （onCreate方法中调用）
     * <p/>
     * 控件监听
     */
    void initListener();

    /**
     * （onResume方法中调用）
     * <p/>
     * 异步任务加载数据
     */
    void getAsynData();

    /**
     * （onDestroy方法中调用）
     * <p/>
     * 销毁、释放资源相关操作
     */
    void destroy();
}
