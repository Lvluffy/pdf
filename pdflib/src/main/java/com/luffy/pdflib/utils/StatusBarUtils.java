package com.luffy.pdflib.utils;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by lvlufei on 2018/7/29
 *
 * @desc 状态栏-辅助工具
 */
public class StatusBarUtils {

    private StatusBarUtils() {
    }

    public static StatusBarUtils getInstance() {
        return StatusBarUtils.StatusBarUtilsHelper.mStatusBarUtils;
    }

    private static class StatusBarUtilsHelper {
        private static StatusBarUtils mStatusBarUtils;

        static {
            mStatusBarUtils = new StatusBarUtils();
        }
    }

    /**
     * 设置状态栏
     *
     * @param mActivity
     * @param colorId
     */
    public void setStatusBar(Activity mActivity, int colorId, boolean isDarkColor) {
        /*5.0及以上*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = mActivity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //设置状态栏颜色
            setStatusBarColor(mActivity, colorId);
        }
        /*4.4到5.0*/
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutParams = mActivity.getWindow().getAttributes();
            layoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutParams.flags);
        }
        /*6.0以后可以对状态栏文字颜色和图标进行修改*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarIconColor(mActivity, isDarkColor);
        }
    }

    /**
     * 设置状态栏颜色
     *
     * @param mActivity
     * @param colorId
     */
    private void setStatusBarColor(Activity mActivity, int colorId) {
        /*5.0及以上*/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mActivity.getWindow().setStatusBarColor(mActivity.getResources().getColor(colorId));
        }
    }

    /**
     * 设置状态栏图标和文字颜色
     *
     * @param mActivity
     * @param isDarkColor ture:实现状态栏图标和文字颜色为暗色;false:实现状态栏图标和文字颜色为浅色
     */
    private void setStatusBarIconColor(Activity mActivity, boolean isDarkColor) {
        /*实现状态栏图标和文字颜色为暗色*/
        if (isDarkColor) {
            /*6.0以后可以对状态栏文字颜色和图标进行修改*/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
        }
        /*实现状态栏图标和文字颜色为浅色*/
        else {
            /*6.0以后可以对状态栏文字颜色和图标进行修改*/
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                mActivity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }
        /*解决：背景图片填充到状态栏，故不能使用android:fitsSystemWindows属性,带有底部导航栏手机底部导航按钮会和navigationbar重叠*/
//        if (ScreenUtils.getInstance().hasNavigationBar(mActivity)) {
//            mActivity.getWindow().getDecorView().findViewById(android.R.id.content).setPadding(0, 0, 0, ScreenUtils.getInstance().getNavigationBarHeight(mActivity));
//        }
    }
}
