package com.demo.nestedscroll_demo.utils;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.nestedscroll_demo.adapter.BaseRecyclerAdapter;
import com.demo.nestedscroll_demo.adapter.OnRecyclerViewItemClickListener;
import com.demo.nestedscroll_demo.adapter.OnRecyclerViewItemLongClickListener;

/**
 * RecyclerView帮助类
 * Created by lishilin on 2016/11/4.
 */
public class RecyclerViewHelper {

    private static class HelperHolder {
        private static final RecyclerViewHelper instance = new RecyclerViewHelper();
    }

    public static RecyclerViewHelper getInstance() {
        return HelperHolder.instance;
    }

    private RecyclerViewHelper() {
        super();
    }

    /**
     * 滚动RecyclerView到顶部
     *
     * @param recyclerView recyclerView
     */
    public void scrollToTop(RecyclerView recyclerView) {
        scrollToPositionByLayoutManager(recyclerView, 0);
    }

    /**
     * 滚动RecyclerView到底部
     *
     * @param recyclerView recyclerView
     */
    public void scrollToBottom(RecyclerView recyclerView) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        int position = 0;
        if (adapter != null) {
            int count = adapter.getItemCount();
            position = count - 1;
        }
        scrollToPositionByLayoutManager(recyclerView, position);
    }

    /**
     * 滚动RecyclerView到指定位置(通过RecyclerView自身，如果position在屏幕可见范围内，则不会滚动，即不会置顶position)
     *
     * @param recyclerView recyclerView
     * @param position     position
     */
    public void scrollToPosition(RecyclerView recyclerView, int position) {
        recyclerView.scrollToPosition(position);
    }

    /**
     * 滚动RecyclerView到指定位置(通过LayoutManager，会把position置顶)
     * RecyclerView 自身scrollToPosition方法定位不会置顶，所以使用LinearLayoutManager中的定位方法
     *
     * @param recyclerView recyclerView
     * @param position     position
     */
    public void scrollToPositionByLayoutManager(RecyclerView recyclerView, int position) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            linearLayoutManager.scrollToPositionWithOffset(position, 0);
        } else {
            scrollToPosition(recyclerView, position);
        }
    }

    /**
     * 设置点击监听
     *
     * @param adapter             adapter
     * @param onItemClickListener 单击监听回调
     */
    public void setItemClickListener(BaseRecyclerAdapter adapter, OnRecyclerViewItemClickListener onItemClickListener) {
        adapter.setOnItemClickListener(onItemClickListener);
    }

    /**
     * 设置点击监听
     *
     * @param adapter                 adapter
     * @param onItemLongClickListener 长按监听回调
     */
    public void setItemLongClickListener(BaseRecyclerAdapter adapter, final OnRecyclerViewItemLongClickListener onItemLongClickListener) {
        OnRecyclerViewItemLongClickListener onItemLongClickListenerTemp = null;
        if (onItemLongClickListener != null) {
            onItemLongClickListenerTemp = new OnRecyclerViewItemLongClickListener() {
                @Override
                public boolean onItemLongClick(RecyclerView.ViewHolder holder) {
                    return onItemLongClickListener.onItemLongClick(holder);
                }
            };
        }
        adapter.setOnItemLongClickListener(onItemLongClickListenerTemp);
    }

    /**
     * 设置点击监听
     *
     * @param recyclerView        RecyclerView
     * @param onItemClickListener 单击监听回调
     */
    public void setItemClickListener(RecyclerView recyclerView, OnRecyclerViewItemClickListener onItemClickListener) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BaseRecyclerAdapter) {
            setItemClickListener((BaseRecyclerAdapter) adapter, onItemClickListener);
        }

//        OnRecyclerViewItemTouchListener onRecyclerItemTouchListener = new OnRecyclerViewItemTouchListener(recyclerView);
//        onRecyclerItemTouchListener.setOnItemClickListener(onItemClickListener);
//        recyclerView.addOnItemTouchListener(onRecyclerItemTouchListener);
    }

    /**
     * 设置点击监听
     *
     * @param recyclerView            RecyclerView
     * @param onItemLongClickListener 长按监听回调
     */
    public void setItemLongClickListener(RecyclerView recyclerView, final OnRecyclerViewItemLongClickListener onItemLongClickListener) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter instanceof BaseRecyclerAdapter) {
            setItemLongClickListener((BaseRecyclerAdapter) adapter, onItemLongClickListener);
        }

//        OnRecyclerViewItemLongClickListener onItemLongClickListenerTemp = null;
//        if (onItemLongClickListener != null) {
//            onItemLongClickListenerTemp = new OnRecyclerViewItemLongClickListener() {
//                @Override
//                public boolean onItemLongClick(RecyclerView.ViewHolder holder) {
//                    VibratorHelper.getInstance().vibrate();
//                    return onItemLongClickListener.onItemLongClick(holder);
//                }
//            };
//        }
//
//        OnRecyclerViewItemTouchListener onRecyclerItemTouchListener = new OnRecyclerViewItemTouchListener(recyclerView);
//        onRecyclerItemTouchListener.setOnItemLongClickListener(onItemLongClickListenerTemp);
//        recyclerView.addOnItemTouchListener(onRecyclerItemTouchListener);
    }

}
