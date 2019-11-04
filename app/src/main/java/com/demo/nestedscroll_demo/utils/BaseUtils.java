package com.demo.nestedscroll_demo.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.LeadingMarginSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.demo.nestedscroll_demo.R;

import java.util.List;

public class BaseUtils {

    private static long lastClickTime = 0;// 最后一次点击时间
    private static final long FAST_DOUBLE_CLICK_TIME_SPACE = 300;// 快速点击时间间隔判定值(小于则判断为快速点击两次)

    /**
     * Activity是否正在运行
     *
     * @param activity activity
     * @return 是否正在运行
     */
    public static boolean isActivityRunning(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return !(activity == null || activity.isDestroyed() || activity.isFinishing());
        } else {
            return !(activity == null || activity.isFinishing());
        }
    }

    /**
     * 是否快速点击两次
     *
     * @return 是否快速点击两次
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        lastClickTime = time;
        return timeD <= FAST_DOUBLE_CLICK_TIME_SPACE;
    }

    /**
     * 比较两个Object是否相同
     *
     * @param obj_1 obj_1
     * @param obj_2 obj_2
     * @return 是否相同
     */
    public static boolean equals(Object obj_1, Object obj_2) {
        return equals(obj_1, obj_2, true);
    }

    /**
     * 比较两个Object是否相同
     *
     * @param obj_1           obj_1
     * @param obj_2           obj_2
     * @param isEqualsAllNull 全部为Null是否相同
     * @return 是否相同
     */
    public static boolean equals(Object obj_1, Object obj_2, boolean isEqualsAllNull) {
        if (obj_1 == null && obj_2 == null) {
            return isEqualsAllNull;
        }
        if (obj_1 != null) {
            return obj_1.equals(obj_2);
        } else {
            return false;
        }
    }

    /**
     * 是否为空 CharSequence
     *
     * @param str str
     * @return 是否为空
     */
    public static boolean isEmpty(CharSequence str) {
        boolean isEmpty = TextUtils.isEmpty(str) || "null".contentEquals(str);
        if (isEmpty) {
            return true;
        } else {
            String strTrim = str.toString().trim();
            return TextUtils.isEmpty(strTrim) || "null".contentEquals(strTrim);
        }
    }

    /**
     * 获取Length Array
     *
     * @param str str
     * @return Length
     */
    public static int getLength(CharSequence str) {
        return str == null ? 0 : str.length();
    }

    /**
     * 是否为空 List
     *
     * @param list list
     * @return 是否为空
     */
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    /**
     * 获取Size List
     *
     * @param list list
     * @return Size
     */
    public static int getSize(List list) {
        return list == null ? 0 : list.size();
    }

    /**
     * 是否为空 Array
     *
     * @param array array
     * @return 是否为空
     */
    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 获取Length Array
     *
     * @param array array
     * @return Length
     */
    public static <T> int getLength(T[] array) {
        return array == null ? 0 : array.length;
    }

    /**
     * 是否是网址
     *
     * @param url url
     * @return 是否是网址
     */
    public static boolean isWebUrl(String url) {
        final String PREFIX_HTTP = "http://";
        final String PREFIX_HTTPS = "https://";
        final int LENGTH_HTTP = PREFIX_HTTP.length();
        final int LENGTH_HTTPS = PREFIX_HTTPS.length();

        int length = url.length();

        if (length < LENGTH_HTTP) {
            return false;
        }
        String tempUrl = url.substring(0, LENGTH_HTTP);
        if (PREFIX_HTTP.equalsIgnoreCase(tempUrl)) {
            return true;
        }

        if (length < LENGTH_HTTPS) {
            return false;
        }
        tempUrl = url.substring(0, LENGTH_HTTPS);
        return PREFIX_HTTPS.equalsIgnoreCase(tempUrl);
    }

    /**
     * 是否是阿里云网址
     *
     * @param url url
     * @return boolean
     */
    public static boolean isAliyunUrl(String url) {
        return url.contains("shanbao-medical.oss-") && url.contains(".aliyuncs.com");
    }

    /**
     * 格式化图片Url
     *
     * @param url    url
     * @param width  width
     * @param height height
     * @return String
     */
    public static String formatPhotoUrl(String url, int width, int height) {
//        LogUtil.i("formatPhotoUrl Original：" + url);

        if (isEmpty(url)) {
            return url;
        }
        if (!isWebUrl(url)) {
            return url;
        }
        if (!isAliyunUrl(url)) {
            return url;
        }
        String tag = "?x-oss-process=image";
        int tagIndex = url.lastIndexOf(tag);
        if (tagIndex > 0) {
            url = url.substring(0, tagIndex + tag.length());
        } else {
            url += tag;
        }
        if (width > 0 && height > 0) {
            url += "/resize,m_fill,w_" + width + ",h_" + height;
        }
        url += "/format,webp";

//        LogUtil.i("formatPhotoUrl Result：" + url);

        return url;
    }

    /* ********************************************* 获取资源 *********************************************/

    /**
     * 获取颜色
     *
     * @param context context
     * @param resId   颜色id(R.color.black)
     * @return Color
     */
    @ColorInt
    public static int getResColor(Context context, @ColorRes int resId) {
        return ContextCompat.getColor(context, resId);
    }

    /**
     * 获取ColorStateList
     *
     * @param context context
     * @param resId   颜色id(R.color.black)
     * @return ColorStateList
     */
    public static ColorStateList getResColorStateList(Context context, int resId) {
        return ContextCompat.getColorStateList(context, resId);
    }

    /**
     * 获取Drawable
     *
     * @param context context
     * @param resId   Drawable id
     * @return Drawable
     */
    public static Drawable getResDrawable(Context context, @DrawableRes int resId) {
        Drawable drawable = ContextCompat.getDrawable(context, resId);

        if (drawable == null) {
            drawable = new ColorDrawable(getResColor(context, R.color.transparent));
        } else {
            drawable = DrawableCompat.wrap(drawable);
        }

        return drawable.mutate();
    }

    /**
     * 获取数值
     *
     * @param context context
     * @param resId   Dimens id
     * @return 数值
     */
    public static int getResDimension(Context context, @DimenRes int resId) {
        return context.getResources().getDimensionPixelOffset(resId);
    }

    /* ********************************************* 获取资源 *********************************************/

    /* ********************************************* TextView操作相关 *********************************************/

    /**
     * 解决EditText滑动冲突问题
     *
     * @param editText editText
     */
    @SuppressLint("ClickableViewAccessibility")
    public static void setEditTextSlidingConflict(final EditText editText) {
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 触摸的是EditText并且当前EditText可以滚动，则将事件交给EditText处理；否则将事件交由其父类处理
                if (editText.canScrollVertically(-1) || editText.canScrollVertically(1)) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
                return false;
            }
        });
    }

    /**
     * 获取缩进CharSequence(第二行开始缩进，不缩进第一行)
     *
     * @param content    内容
     * @param tv_content tv_content
     * @return CharSequence
     */
    public static CharSequence getIndentLineContent(CharSequence content, TextView tv_content) {
        String contentStr = content.toString();
        int index = contentStr.indexOf("：");
        if (index < 0) {
            index = contentStr.indexOf(":");
        }
        if (index < 0) {
            index = contentStr.indexOf("*");
        }
        if (index < 0) {
            index = contentStr.indexOf(" ");
        }
        if (index < 0) {
            return content;
        }

        SpannableStringBuilder span = new SpannableStringBuilder(content);
        int width = (int) tv_content.getPaint().measureText(contentStr.substring(0, index + 1));
        int start = 0;
        int end = span.length();
        span.setSpan(new LeadingMarginSpan.Standard(0, width), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }
    /* ********************************************* TextView操作相关 *********************************************/

}
