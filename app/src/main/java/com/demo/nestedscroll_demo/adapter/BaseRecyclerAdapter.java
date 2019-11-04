package com.demo.nestedscroll_demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * RecyclerView.Adapter基类
 * Created by lishilin on 2016/10/11.
 */
public abstract class BaseRecyclerAdapter<VH extends BaseRecyclerAdapter.ViewHolder> extends RecyclerView.Adapter<VH> {

    public final Context context;

    private OnRecyclerViewItemClickListener onItemClickListener;
    private OnRecyclerViewItemLongClickListener onItemLongClickListener;

    public BaseRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnRecyclerViewItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = View.inflate(context, R.layout.item_horizontal, parent);
        View view = LayoutInflater.from(context).inflate(getViewResourceId(viewType), parent, false);
        return getViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        setView(holder, position);
    }

    public abstract Object getItem(int position);

    public abstract void setView(VH holder, int position);

    public abstract int getViewResourceId(int viewType);

    public abstract VH getViewHolder(View view, int viewType);

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(ViewHolder.this);
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return onItemLongClickListener == null || onItemLongClickListener.onItemLongClick(ViewHolder.this);
                }
            });
        }
    }

}