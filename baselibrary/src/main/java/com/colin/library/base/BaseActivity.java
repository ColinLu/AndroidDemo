package com.colin.library.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import com.colin.library.help.AnimHelp;
import com.colin.library.help.SystemHelp;
import com.nostra13.universalimageloader.core.ImageLoader;


public abstract class BaseActivity extends Activity implements IBaseActivity{

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    /**
     * TitleBar控件
     */
    private View foot_view = null;
    private boolean isLoading = false;
    protected int page = 0;
    /**
     * 对外公开;是否正在异步任务
     *
     * @return
     */
    public boolean isLoading() {
        return isLoading;
    }

    /**
     * 对外公开;是否正在异步任务
     */
    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        foot_view = LayoutInflater.from(this).inflate(bindLayout(), null);
        setContentView(foot_view);
        if (foot_view !=null){
            initView();
            initData();
            initListener();
        }
        new BaseApplication().addActivity(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAsynData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        page = 0 ;
        imageLoader = null;
        setLoading(false);
        foot_view = null;
        destroy();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 拦截MENU按钮点击事件，让他无任何操作
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出
     */
    @Override
    public void onBackPressed() {
        closeActivity();
        super.onBackPressed();
    }

    /**
     * 关闭当前界面
     */
    protected final void closeActivity() {
        SystemHelp.getInstance().hideKeyword(this);
        this.finish();
        AnimHelp.getInstance().showCloseAnim(this);
    }

    /**
     * 界面跳转 是否保存当前界面 false 保存；true 不保存
     *
     * @param target
     * @param bundle
     * @param closeSelf
     */
    protected final void startActivity(Class<? extends Activity> target, Bundle bundle, boolean closeSelf) {
        SystemHelp.getInstance().hideKeyword(this);
        if (closeSelf) {
            closeActivity();
        }
        Intent intent = new Intent(this, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        this.startActivity(intent);
        AnimHelp.getInstance().showOpenAnim(this);
    }

    /**
     * 界面跳转获取值
     *
     * @param target
     * @param requestCode
     * @param bundle
     */
    protected final void startActivityForResult(Class<? extends Activity> target, int requestCode, Bundle bundle) {
        SystemHelp.getInstance().hideKeyword(this);
        Intent intent = new Intent(this, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        this.startActivityForResult(intent, requestCode);
        AnimHelp.getInstance().showOpenAnim(this);
    }

}
