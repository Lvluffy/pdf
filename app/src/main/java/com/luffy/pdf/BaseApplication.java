package com.luffy.pdf;

import android.app.Application;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.cookie.store.PersistentCookieStore;

/**
 * Created by lvlufei on 2017/11/24
 *
 * @desc 公共-Application
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /**初始化下载框架*/
        OkHttpUtils.init(this);
        OkHttpUtils.getInstance()
                .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)        //全局的连接超时时间
                .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)           //全局的读取超时时间
                .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)          //全局的写入超时时间
                .setCookieStore(new PersistentCookieStore());               //cookie持久化存储，如果cookie不过期，则一直有效
    }
}
