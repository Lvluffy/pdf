package com.luffy.pdflib.helper;

import android.content.Context;
import android.content.Intent;

import com.luffy.pdflib.activity.PdfShowActivity;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 界面跳转-帮助类
 */
public class IntentHelper {

    /**
     * PDF
     */
    public static class PDF {
        /**
         * 跳转至——PDF显示界面
         *
         * @param context   上下文对象
         * @param parameter 跳转界面所需参数
         */
        public static void gotoPdfShowActivity(Context context, Map<String, Object> parameter) {
            Intent intent = new Intent(context, PdfShowActivity.class);
            intent.putExtra("parameter", (Serializable) parameter);
            context.startActivity(intent);
        }
    }

}
