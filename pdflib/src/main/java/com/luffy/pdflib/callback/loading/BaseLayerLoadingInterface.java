package com.luffy.pdflib.callback.loading;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 加载Loading回调监听
 */
public interface BaseLayerLoadingInterface {
    /**
     * 显示-加载Loading
     */
    void showLoading();

    /**
     * 显示-加载Loading
     */
    void showLoading(String content);

    /**
     * 取消-加载Loading
     */
    void dismissLoading();
}
