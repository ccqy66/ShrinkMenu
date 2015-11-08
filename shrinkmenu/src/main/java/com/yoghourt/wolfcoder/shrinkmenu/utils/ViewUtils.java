package com.yoghourt.wolfcoder.shrinkmenu.utils;

import android.view.View;

/**
 * Description:视图工具类
 * User: Dream_Coder(chenchen_839@126.com)
 * Date: 2015-11-05
 * Time: 12:46
 */
public class ViewUtils {
    public static final String VIEW_TAG_PREFIX = "imageMenu_";

    /**
     * 生成view的tag
     * @param view
     * @param index
     */
    public static void generateViewTag(View view,int index) {
        view.setTag(VIEW_TAG_PREFIX+index);
    }
    public static String getViewTag(int index) {
        String tag = VIEW_TAG_PREFIX+index;
        return tag;
    }
}
