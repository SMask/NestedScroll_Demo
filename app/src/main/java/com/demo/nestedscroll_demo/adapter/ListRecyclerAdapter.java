package com.demo.nestedscroll_demo.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.demo.nestedscroll_demo.R;
import com.demo.nestedscroll_demo.utils.BaseUtils;

import java.util.List;

/**
 * List RecyclerAdapter
 * Created by lishilin
 */
public class ListRecyclerAdapter extends BaseRecyclerAdapter<ListRecyclerAdapter.ViewHolder> {

    private final List<String> data;

    public ListRecyclerAdapter(Context context, List<String> data) {
        super(context);
        this.data = data;
    }

    @Override
    public int getItemCount() {
        return BaseUtils.getSize(data);
    }

    @Override
    public String getItem(int position) {
        return data.get(position);
    }

    @Override
    public int getViewResourceId(int viewType) {
        return R.layout.item_list;
    }

    @Override
    public ViewHolder getViewHolder(View view, int viewType) {
        return new ViewHolder(view);
    }

    @Override
    public void setView(final ViewHolder holder, final int position) {
        String temp = getItem(position);

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
