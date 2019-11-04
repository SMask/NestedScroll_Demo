package com.demo.nestedscroll_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import com.demo.nestedscroll_demo.adapter.BannerRecyclerAdapter;
import com.demo.nestedscroll_demo.adapter.PageAdapter;
import com.demo.nestedscroll_demo.utils.BaseUtils;
import com.demo.nestedscroll_demo.utils.IndicatorHelper;
import com.demo.nestedscroll_demo.utils.LogUtil;
import com.demo.nestedscroll_demo.utils.RecyclerViewHelper;
import com.demo.nestedscroll_demo.utils.ToastHelper;
import com.demo.nestedscroll_demo.view.IndicatorView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv_banner;
    private IndicatorView indicator_banner;

    private TabLayout tab_type;
    private ViewPager vp_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startSwitchBanner();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopSwitchBanner();
    }

    private void initView() {
        rv_banner = findViewById(R.id.rv_banner);
        indicator_banner = findViewById(R.id.indicator_banner);
        tab_type = findViewById(R.id.tab_type);
        vp_content = findViewById(R.id.vp_content);

        // 使RecyclerView保持固定的大小,这样会提高RecyclerView的性能
        rv_banner.setHasFixedSize(true);

        // 布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_banner.setLayoutManager(layoutManager);

        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(rv_banner);
        IndicatorHelper.getInstance().bindRecyclerView(indicator_banner, rv_banner, snapHelper);
    }

    private void initData() {
        // 轮播图
        List<String> bannerList = new ArrayList<>();
        bannerList.add("Banner_1");
        bannerList.add("Banner_2");
        bannerList.add("Banner_3");
        bannerList.add("Banner_4");
        BannerRecyclerAdapter bannerAdapter = new BannerRecyclerAdapter(this, bannerList);
        RecyclerViewHelper.getInstance().setItemClickListener(bannerAdapter, holder -> {
            String temp = bannerAdapter.getItem(holder.getAdapterPosition());
            ToastHelper.getInstance().showLong(temp);
        });
        rv_banner.setAdapter(bannerAdapter);
        indicator_banner.setCount(BaseUtils.getSize(bannerList));

        // ViewPager
        vp_content.setAdapter(new PageAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        tab_type.setupWithViewPager(vp_content);
    }

    private void setListener() {

    }

    /* ********************************************* 轮播图 *********************************************/

    private static final int MESSAGE_BANNER_AUTO_SWITCH = 10000;// 轮播图自动切换消息码

    private final Handler handler = new Handler(msg -> {
        switch (msg.what) {
            case MESSAGE_BANNER_AUTO_SWITCH:
                switchBanner();
                break;
        }
        return false;
    });

    /**
     * 开始切换轮播图
     */
    private void startSwitchBanner() {
        handler.removeMessages(MESSAGE_BANNER_AUTO_SWITCH);
        if (!handler.hasMessages(MESSAGE_BANNER_AUTO_SWITCH)) {
            handler.sendEmptyMessageDelayed(MESSAGE_BANNER_AUTO_SWITCH, 3000);
        }
    }

    /**
     * 停止切换轮播图
     */
    private void stopSwitchBanner() {
        LogUtil.i("stopSwitchBanner");
        handler.removeMessages(MESSAGE_BANNER_AUTO_SWITCH);
    }

    /**
     * 切换轮播图
     */
    private void switchBanner() {
        int count = 0;
        RecyclerView.Adapter adapter = rv_banner.getAdapter();
        if (adapter != null) {
            count = adapter.getItemCount();
        }

        int currentPosition = 0;
        RecyclerView.LayoutManager layoutManager = rv_banner.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            currentPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        if (currentPosition >= count - 1) {
            currentPosition = 0;
        } else {
            currentPosition++;
        }
        rv_banner.smoothScrollToPosition(currentPosition);

        startSwitchBanner();
    }
    /* ********************************************* 轮播图 *********************************************/
}
