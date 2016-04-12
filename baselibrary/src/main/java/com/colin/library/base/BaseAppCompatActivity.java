package com.colin.library.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.colin.library.help.AnimHelp;
import com.colin.library.help.ImageLoaderHelp;
import com.colin.library.help.LogHelp;
import com.colin.library.help.SystemHelp;
import com.nostra13.universalimageloader.core.ImageLoader;


public abstract class BaseAppCompatActivity extends AppCompatActivity implements IBaseActivity {
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    /**
     * TitleBar控件
     */
    private View view = null;
    protected Toolbar toolbar;
    protected CollapsingToolbarLayout collapsingToolbar;
    protected ImageView image_toolbar_background;
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
        view = LayoutInflater.from(this).inflate(bindLayout(), null);
        setContentView(view);
        if (view != null) {
            initToolbar();
            initView();
            initData();
            initListener();
        }
        new BaseApplication().addActivity(this);
    }

    private void initToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != toolbar) {
            this.collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            this.image_toolbar_background = (ImageView) this.findViewById(R.id.image_toolbar_background);
            setSupportActionBar(toolbar);
            //设置返回主界面
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            if (null != image_toolbar_background) {
                imageLoader.displayImage("http://h.hiphotos.baidu.com/image/h%3D360/sign=d5eb92930ef431ada3d2453f7b37ac0f/d058ccbf6c81800a7e84fe0fb33533fa828b4749.jpg", this.image_toolbar_background, ImageLoaderHelp.getDisplayImageOptions(0));
            }

        }


    }

    @Override
    protected void onStart() {
        LogHelp.d(this.getClass().getSimpleName() + ":onStart()");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        LogHelp.d(this.getClass().getSimpleName() + ":onRestart()");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        LogHelp.d(this.getClass().getSimpleName() + ":onResume()");
        super.onResume();
        getAsynData();
    }

    @Override
    protected void onPause() {
        LogHelp.d(this.getClass().getSimpleName() + ":onStart()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        LogHelp.d(this.getClass().getSimpleName() + ":onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        LogHelp.d(this.getClass().getSimpleName() + ":onDestroy()");
        page = 0;
        imageLoader = null;
        setLoading(false);
        view = null;
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

    @Override
    public void onBackPressed() {
        LogHelp.d(this.getClass().getSimpleName() + ":onBackPressed()");
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
