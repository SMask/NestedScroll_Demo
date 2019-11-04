package com.demo.nestedscroll_demo.utils;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.widget.Toast;

import com.demo.nestedscroll_demo.BaseApplication;

/**
 * Toast帮助类
 * Created by lishilin on 2016/10/11.
 */
public class ToastHelper {

    private static class ToastHelperHolder {
        private static final ToastHelper instance = new ToastHelper();
    }

    public static ToastHelper getInstance() {
        return ToastHelperHolder.instance;
    }

    private ToastHelper() {
        super();
    }

    private Toast toast;

    /**
     * 显示Toast
     *
     * @param message  message
     * @param duration duration
     */
    @SuppressLint("ShowToast")
    private void show(CharSequence message, int duration) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getInstance(), message, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
            toast.setDuration(duration);
        }
        toast.show();
    }

    /**
     * 显示Toast
     *
     * @param messageId messageId
     * @param duration  duration
     */
    private void show(int messageId, int duration) {
        show(BaseApplication.getInstance().getText(messageId), duration);
    }

    /**
     * 显示Toast_Short
     *
     * @param message message
     */
    public void showShort(CharSequence message) {
        show(message, Toast.LENGTH_SHORT);
    }

    /**
     * 显示Toast_Short
     *
     * @param messageId messageId
     */
    public void showShort(int messageId) {
        show(messageId, Toast.LENGTH_SHORT);
    }

    /**
     * 显示Toast_Long
     *
     * @param message message
     */
    public void showLong(CharSequence message) {
        show(message, Toast.LENGTH_LONG);
    }

    /**
     * 显示Toast_Long
     *
     * @param messageId messageId
     */
    public void showLong(int messageId) {
        show(messageId, Toast.LENGTH_LONG);
    }

    /**
     * 取消Toast
     */
    public void cancel() {
        if (toast != null) {
            toast.cancel();
        }
    }


}
