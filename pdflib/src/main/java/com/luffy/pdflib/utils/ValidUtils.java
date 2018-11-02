package com.luffy.pdflib.utils;

import java.util.Collection;

/**
 * Created by lvlufei on 2018/1/1
 *
 * @desc 验证-辅助工具
 */
public class ValidUtils {

    private ValidUtils() {
    }

    public static ValidUtils getInstance() {
        return ValidUtilsHelper.mValidUtils;
    }

    private static class ValidUtilsHelper {
        private static ValidUtils mValidUtils;

        static {
            mValidUtils = new ValidUtils();
        }
    }

    /**
     * 判断string的有效性
     *
     * @param string
     * @return
     */
    public boolean isValid(String string) {
        if (string == null || "".equals(string.trim()) || "null".equals(string) || string.length() == 0) {
            return false;
        }
        return true;
    }

    /**
     * 判断对象的有效性
     *
     * @param obj
     * @return
     */
    public boolean isValid(Object obj) {
        if (obj == null) {
            return false;
        }
        return true;
    }

    /**
     * 判断集合的有效性
     *
     * @param collection
     * @return
     */
    public boolean isValid(Collection collection) {
        if (collection == null || collection.isEmpty()) {
            return false;
        }
        return true;
    }
}