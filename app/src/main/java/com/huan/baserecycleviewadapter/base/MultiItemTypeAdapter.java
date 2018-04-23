package com.huan.baserecycleviewadapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H_S on 2018/4/21.
 */

public class MultiItemTypeAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context mContext;
    protected List<T> mList;
    protected ItemViewDelegateManager delegateManager;

    public MultiItemTypeAdapter(Context context, List<T> list) {
        mContext = context;
        if (list == null || list.size() <= 0) {
            mList = new ArrayList<>();
        } else {
            mList = list;
        }
        delegateManager = new ItemViewDelegateManager();
    }

    @Override
    public int getItemViewType(int position) {
        boolean useManager = useItemViewDelegateManager();
        if (!useManager) {
            return super.getItemViewType(position);
        }
        return delegateManager.getItemViewType(mList.get(position), position);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemViewDelegate delegate = delegateManager.getItemViewDelegate(viewType);
        int layoutId = delegate.getItemViewLayoutId();
        BaseViewHolder holder = BaseViewHolder.createBaseViewHolder(mContext, parent, layoutId);
        onViewHolderCreated(holder, holder.getConvertView());
        return holder;
    }

    public void onViewHolderCreated(BaseViewHolder holder, View itemView) {
    }

    public void convert(BaseViewHolder holder, T t) {
        delegateManager.convert(holder, t, holder.getAdapterPosition());
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size() > 0 ? mList.size() : 0;
    }

    public void addItemViewDelegate(ItemViewDelegate delegate) {
        delegateManager.addDelegate(delegate);
    }

    public void addItemViewDelegate(int viewType, ItemViewDelegate delegate) {
        delegateManager.addDelegate(viewType, delegate);
    }

    protected boolean useItemViewDelegateManager() {
        return delegateManager.getItemViewDelegateCount() > 0;
    }
}
