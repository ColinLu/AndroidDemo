package com.colin.library.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.colin.library.help.AnimHelp;
import com.colin.library.help.LogHelp;
import com.colin.library.help.SystemHelp;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2016/3/1.
 */
public abstract class BaseFragment extends Fragment implements IBaseFragment {
    public interface OnFragmentListener {

        //fragment编号；item位置，判断,回调数据
        void onFragmentClick(int fragment_id, int position, boolean b, Object object);
    }

    protected Activity activity;
    protected ImageLoader imageLoader = ImageLoader.getInstance();
    protected OnFragmentListener onFragmentListener;
    protected View view;

    /**
     * Fragment当前是否正在加载数据
     */
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogHelp.d(this.getClass().getSimpleName() + ":onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //渲染视图View(防止切换时重绘View)
        if (null != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(bindLayout(), container, false);
            // 控件初始化
            initView();
        }
        //业务处理
        initData();
        initListener();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        this.activity = (Activity) context;
        super.onAttach(context);
        if (context instanceof OnFragmentListener) {
            onFragmentListener = (OnFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() + " \n must implement OnFragmentListener");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        LogHelp.d(this.getClass().getSimpleName() + ":onStart()");
    }


    @Override
    public void onResume() {
        LogHelp.d(this.getClass().getSimpleName() + ":onResume()");
        super.onResume();
        if (null != view && getUserVisibleHint() && !isLoading()) {
            getAsynData();
            LogHelp.d(this.getClass().getSimpleName() + ":onResume()：getAsynData");
        } else {

        }
    }

    /**
     * 参考：http://stackoverflow.com/questions/10024739/how-to-determine-when-
     * fragment-becomes-visible-in-viewpager
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (null != view && isVisibleToUser && !isLoading()) {
            getAsynData();
            LogHelp.d(this.getClass().getSimpleName() + ":setUserVisibleHint()：getAsynData");
        } else {

        }
    }

    @Override
    public void onPause() {
        LogHelp.d(this.getClass().getSimpleName() + ":onPause()");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogHelp.d(this.getClass().getSimpleName() + ":onStop()");
        super.onStop();
    }

    @Override
    public void onDetach() {
        LogHelp.d(this.getClass().getSimpleName() + ":onStop()");
        super.onDetach();
        onFragmentListener = null;
    }

    //Fragment 与 Activity分离
    @Override
    public void onDestroyView() {
        LogHelp.d(this.getClass().getSimpleName() + ":onDestroyView()");
        destroyView();
        imageLoader = null;
        view = null;
        page = 0;
        isLoading = false;
        activity = null;
        onFragmentListener = null;
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        LogHelp.d(this.getClass().getSimpleName() + ":onDestroy()");
        super.onDestroy();
    }


    /**
     * 界面跳转 是否保存当前界面 false 保存；true 不保存
     *
     * @param target
     * @param bundle
     * @param closeSelf
     */
    protected final void startActivity(Class<? extends Activity> target, Bundle bundle, boolean closeSelf) {
        SystemHelp.getInstance().hideKeyword(activity);
        if (closeSelf) {
            getActivity().finish();
        }
        Intent intent = new Intent(activity, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        this.startActivity(intent);
        AnimHelp.getInstance().showOpenAnim(activity);
    }

    /**
     * 界面跳转获取值
     *
     * @param target
     * @param requestCode
     * @param bundle
     */
    protected final void startActivityForResult(Class<? extends Activity> target, int requestCode, Bundle bundle) {
        SystemHelp.getInstance().hideKeyword(activity);
        Intent intent = new Intent(activity, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        this.startActivityForResult(intent, requestCode);
        AnimHelp.getInstance().showOpenAnim(activity);

    }

    /**
     * 关闭当前界面
     */
    protected final void closeActivity() {
        SystemHelp.getInstance().hideKeyword(activity);
        this.getActivity().onBackPressed();
        AnimHelp.getInstance().showCloseAnim(activity);
    }


}
