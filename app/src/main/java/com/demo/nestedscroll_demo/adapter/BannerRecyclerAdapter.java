package com.demo.nestedscroll_demo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.demo.nestedscroll_demo.R;
import com.demo.nestedscroll_demo.utils.BaseUtils;

import java.util.List;

/**
 * Banner RecyclerAdapter
 * Created by lishilin
 */
public class BannerRecyclerAdapter extends BaseRecyclerAdapter<BannerRecyclerAdapter.ViewHolder> {

    private final List<String> data;

    public BannerRecyclerAdapter(Context context, List<String> data) {
        super(context);
        this.data = data;
    }

    public int getItemCountReal() {
        return BaseUtils.getSize(data);
    }

    @Override
    public int getItemCount() {
        // 如果设置为 Integer.MAX_VALUE 则会和 ViewPager 产生左右滑动冲突，具体原因未知
        // 1. 设置为 3000000(这个数值可自行测试得出) ，不用自定义 RecyclerView ；
        // 2. 自定义 RecyclerView 重写 dispatchTouchEvent 方法， 调用 getParent().requestDisallowInterceptTouchEvent(true) ，这样就可以设置为 Integer.MAX_VALUE 。
        // 详情访问：https://juejin.im/post/5d0c7693f265da1b6a349c5f
        int count = getItemCountReal();
        return count <= 1 ? count : Integer.MAX_VALUE;
    }

    @Override
    public String getItem(int position) {
        return data.get(position % getItemCountReal());
    }

    @Override
    public int getViewResourceId(int viewType) {
        return R.layout.item_banner;
    }

    @Override
    public ViewHolder getViewHolder(View view, int viewType) {
        return new ViewHolder(view);
    }

    @Override
    public void setView(final ViewHolder holder, final int position) {
        String temp = getItem(position % getItemCountReal());

        holder.tv_content.setText(temp);
    }

    class ViewHolder extends BaseRecyclerAdapter.ViewHolder {

        TextView tv_content;

        ViewHolder(View itemView) {
            super(itemView);

            tv_content = itemView.findViewById(R.id.tv_content);
        }

    }

}
