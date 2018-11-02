package com.luffy.pdflib.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.luffy.pdflib.R;
import com.luffy.pdflib.callback.download.DownloadFileCallBack;
import com.luffy.pdflib.callback.loading.BaseLayerLoadingInterface;
import com.luffy.pdflib.loadingDialog.LoadingDialog;
import com.luffy.pdflib.utils.StatusBarUtils;
import com.luffy.pdflib.utils.ValidUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.request.BaseRequest;

import java.io.File;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class PdfShowActivity extends AppCompatActivity implements View.OnClickListener,
        BaseLayerLoadingInterface {

    /**
     * 上下文对象
     */
    public AppCompatActivity mContext;

    /**
     * 标题栏布局
     */
    LinearLayout navBack;
    ImageView navBackImg;
    TextView navTitle;
    View navDivider;

    /**
     * PDF控件
     */
    PDFView pdfView;

    /**
     * 网络加载loading
     */
    LoadingDialog mLoadingDialog;

    /*存储路径*/
    String destFileDir;

    /*上个界面传过来的数据*/
    Map<String, Object> parameter;
    /*上个界面传过来的详细内容*/
    String title;
    String pdfUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_show);
        mContext = this;
        destFileDir = mContext.getCacheDir().getPath() + File.separator + "pdf";
        /*沉浸式状态栏-白底黑字*/
        StatusBarUtils.getInstance().setStatusBar(mContext, R.color.white, true);
        /*获取控件*/
        navBack = findViewById(R.id.nav_back);
        navBackImg = findViewById(R.id.nav_back_img);
        navTitle = findViewById(R.id.nav_title);
        navDivider = findViewById(R.id.nav_divider);
        navBack.setOnClickListener(this);
        pdfView = findViewById(R.id.pdfView);
        /*获取上个界面传过来的数据*/
        parameter = (Map<String, Object>) getIntent().getSerializableExtra("parameter");
        if (parameter != null && parameter.size() > 0) {
            for (Map.Entry<String, Object> entry : parameter.entrySet()) {
                if ("title".equals(entry.getKey())) {
                    title = (String) entry.getValue();
                    navTitle.setText(title);
                } else if ("pdfUrl".equals(entry.getKey())) {
                    pdfUrl = (String) entry.getValue();
                }
            }
        }
        /*处理合同*/
        handleContract(pdfUrl, String.valueOf(System.currentTimeMillis()));
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.nav_back) {
            onBackPressed();
        }
    }

    @Override
    public void showLoading() {
        try {
            if (mContext == null || mContext.isFinishing()) {
                return;
            }
            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog(mContext);
            }
            mLoadingDialog.showLoading();
        } catch (Exception e) {

        }
    }

    @Override
    public void showLoading(String content) {
        try {
            if (mContext == null || mContext.isFinishing()) {
                return;
            }
            if (mLoadingDialog == null) {
                mLoadingDialog = new LoadingDialog(mContext);
            }
            mLoadingDialog.setText(content).showLoading();
        } catch (Exception e) {

        }
    }

    @Override
    public void dismissLoading() {
        try {
            if (mContext == null || mContext.isFinishing()) {
                return;
            }
            if (mLoadingDialog != null) {
                mLoadingDialog.dismissLoading();
            }
        } catch (Exception e) {

        }
    }

    private void handleContract(final String fileUrl, final String fileName) {
        /*下载PDF文件*/
        if (ValidUtils.getInstance().isValid(fileUrl) && fileUrl.endsWith(".pdf")) {
            OkHttpUtils.get(fileUrl)
                    .tag(this)
                    .execute(new DownloadFileCallBack(destFileDir, fileName, new DownloadFileCallBack.DownloadFinishInterface() {
                        @Override
                        public void onBefore(BaseRequest request) {
                            showLoading("0%");
                        }

                        @Override
                        public void onResponse(boolean isFromCache, File file, Request request, Response response) {
                            dismissLoading();
                            showPdf(fileName);
                        }

                        @Override
                        public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                            showLoading((Math.round(progress * 10000) / 100) + "%");
                        }

                        @Override
                        public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
                            dismissLoading();
                        }
                    }));
        }
    }

    private void showPdf(String fileName) {
        File file = new File(destFileDir, fileName);
        pdfView.fromFile(file)
                .defaultPage(0)//默认展示第一页
                .spacing(0)//间距
                .load();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
