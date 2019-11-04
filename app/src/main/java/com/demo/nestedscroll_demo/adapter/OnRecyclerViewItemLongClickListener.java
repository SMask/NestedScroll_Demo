package com.demo.nestedscroll_demo.adapter;

import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView Item长按监听回调
 * Created by lishilin on 2016/11/22.
 */
public interface OnRecyclerViewItemLongClickListener {

    boolean onItemLongClick(RecyclerView.ViewHolder holder);

}
