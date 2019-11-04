package com.demo.nestedscroll_demo.utils;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.demo.nestedscroll_demo.adapter.BannerRecyclerAdapter;
import com.demo.nestedscroll_demo.view.IndicatorView;

/**
 * 指示器 帮助类
 * Created by lishilin on 2017/7/31.
 */
public class IndicatorHelper {

    private static class InstanceHolder {
        private static final IndicatorHelper instance = new IndicatorHelper();
    }

    public static IndicatorHelper getInstance() {
        return InstanceHolder.instance;
    }

    private IndicatorHelper() {
        super();
    }

    /**
     * 绑定 RecyclerView
     *
     * @param indicator_banner indicator_banner
     * @param rv_content       rv_content
     * @param snapHelper       snapHelper
     */
    public void bindRecyclerView(IndicatorView indicator_banner, RecyclerView rv_content, PagerSnapHelper snapHelper) {
        rv_content.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = rv_content.getLayoutManager();
                if (layoutManager == null || snapHelper == null) {
                    return;
                }
                View view = snapHelper.findSnapView(layoutManager);
                if (view == null) {
                    return;
                }
                int position = layoutManager.getPosition(view);
                RecyclerView.Adapter adapter = rv_content.getAdapter();
                if (adapter instanceof BannerRecyclerAdapter) {
                    position = position % ((BannerRecyclerAdapter) adapter).getItemCountReal();
                }
                indicator_banner.setSelectedPosition(position);
            }
        });
    }

}
