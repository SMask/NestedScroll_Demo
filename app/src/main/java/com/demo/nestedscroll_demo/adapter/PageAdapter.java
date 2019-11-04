package com.demo.nestedscroll_demo.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.demo.nestedscroll_demo.ListFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * ViewPager Adapter
 * Created by lishilin on 2019/11/04
 */
public class PageAdapter extends FragmentStatePagerAdapter {

    private final Map<String, ListFragment> fragmentMap = new HashMap<>();

    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            default:
            case 0:
                return "最新";
            case 1:
                return "热门";
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // 获取当前Fragment
        final String KEY = String.valueOf(position);
        ListFragment fragment = fragmentMap.get(KEY);
        if (fragment != null) {
            return fragment;
        }
        fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("prefix", String.valueOf(getPageTitle(position)));
        fragment.setArguments(bundle);
        fragmentMap.put(KEY, fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
