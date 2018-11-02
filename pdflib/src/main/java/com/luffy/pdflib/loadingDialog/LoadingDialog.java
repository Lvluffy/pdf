package com.luffy.pdflib.loadingDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.luffy.pdflib.R;


/**
 * Created by lvlufei on 2018/7/26
 *
 * @desc 加载Dialog
 */
public class LoadingDialog extends Dialog {
    private View mView;

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_loading_layout, null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(mView);
        /*初始化配置*/
        initConfig();
    }

    /**
     * 显示-加载Loading
     *
     * @return
     */
    public LoadingDialog showLoading() {
        if (!this.isShowing()) {
            this.show();
        }
        return this;
    }

    /**
     * 取消-加载Loading
     *
     * @return
     */
    public LoadingDialog dismissLoading() {
        if (this.isShowing()) {
            this.dismiss();
        }
        return this;
    }

    /**
     * 初始化配置
     */
    private void initConfig() {
        this.setCanceledOnTouchOutside(false);
    }

    /**
     * 设置文字
     *
     * @param mString
     * @return
     */
    public LoadingDialog setText(String mString) {
        TextView mTextView = mView.findViewById(R.id.base_loading_txt);
        mTextView.setVisibility(View.VISIBLE);
        mTextView.setText(mString);
        return this;
    }
}