package com.demo.nestedscroll_demo;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.demo.nestedscroll_demo.adapter.ListRecyclerAdapter;
import com.demo.nestedscroll_demo.utils.RecyclerViewHelper;
import com.demo.nestedscroll_demo.utils.ToastHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 列表 Fragment
 * Created by lishilin on 2019/11/04
 */
public class ListFragment extends Fragment {

    private String prefix;

    private SwipeRefreshLayout srl_list;
    private RecyclerView rv_list;

    private List<String> dataList;
    private ListRecyclerAdapter adapter;

    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup layout_root = (ViewGroup) View.inflate(getActivity(), R.layout.fragment_list, null);
        srl_list = layout_root.findViewById(R.id.srl_list);
        rv_list = layout_root.findViewById(R.id.rv_list);

        initView();
        initData();
        setListener();

        return layout_root;
    }

    private void initView() {
        // 使RecyclerView保持固定的大小,这样会提高RecyclerView的性能
        rv_list.setHasFixedSize(true);

        // 布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_list.setLayoutManager(layoutManager);
    }

    private void initData() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            prefix = bundle.getString("prefix");
        }

        dataList = new ArrayList<>();
        adapter = new ListRecyclerAdapter(getActivity(), dataList);
        RecyclerViewHelper.getInstance().setItemClickListener(adapter, holder -> {
            String temp = adapter.getItem(holder.getAdapterPosition());
            ToastHelper.getInstance().showLong(temp);
        });
        rv_list.setAdapter(adapter);
    }

    private void setListener() {
        srl_list.setOnRefreshListener(() -> handler.postDelayed(() -> {
            for (int i = 0; i < 100; i++) {
                dataList.add(prefix + "_" + (i + 1));
            }
            adapter.notifyDataSetChanged();
            srl_list.setRefreshing(false);
        }, 2000));
    }
}
