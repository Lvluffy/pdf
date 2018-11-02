package com.luffy.pdflib.callback.download;

import android.support.annotation.Nullable;

import com.lzy.okhttputils.callback.FileCallback;
import com.lzy.okhttputils.request.BaseRequest;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lvlufei on 2018/6/20
 *
 * @desc 文件下载回调
 */
public class DownloadFileCallBack extends FileCallback {

    private DownloadFinishInterface mDownloadFinishInterface;

    public DownloadFileCallBack(String destFileDir, String destFileName, DownloadFinishInterface mDownloadFinishInterface) {
        super(destFileDir, destFileName);
        this.mDownloadFinishInterface = mDownloadFinishInterface;
    }

    /*开始下载*/
    @Override
    public void onBefore(BaseRequest request) {
        mDownloadFinishInterface.onBefore(request);
    }

    /*下载完成*/
    @Override
    public void onResponse(boolean isFromCache, File file, Request request, Response response) {
        mDownloadFinishInterface.onResponse(isFromCache, file, request, response);
    }

    /*下载中*/
    @Override
    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
        mDownloadFinishInterface.downloadProgress(currentSize, totalSize, progress, networkSpeed);
    }

    /*下载异常*/
    @Override
    public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
        super.onError(isFromCache, call, response, e);
        mDownloadFinishInterface.onError(isFromCache, call, response, e);
    }

    public interface DownloadFinishInterface {

        void onBefore(BaseRequest request);

        void onResponse(boolean isFromCache, File file, Request request, Response response);

        void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed);

        void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e);
    }
}
