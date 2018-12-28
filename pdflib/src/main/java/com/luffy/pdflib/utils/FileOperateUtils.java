package com.luffy.pdflib.utils;

import java.io.File;

/**
 * Created by lvlufei on 2018/12/28
 *
 * @desc 文件操作-工具类
 */
public class FileOperateUtils {

    private FileOperateUtils() {
    }

    public static FileOperateUtils getInstance() {
        return FileOperateUtils.FileOperateUtilsHelper.mFileOperateUtils;
    }

    private static class FileOperateUtilsHelper {
        private static FileOperateUtils mFileOperateUtils;

        static {
            mFileOperateUtils = new FileOperateUtils();
        }
    }

    /**
     * 删除文件夹和文件夹里面的文件
     *
     * @param pPath
     */
    public void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }

    public void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
        dir.delete();// 删除目录本身
    }
}
