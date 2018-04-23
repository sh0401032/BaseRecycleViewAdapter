package com.huan.baserecycleviewadapter.base;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by H_S on 2018/4/21.
 */

public abstract class BaseRecycleViewAdapter<T> extends MultiItemTypeAdapter<T> {

    private List<T> mList;
    private int mLayoutId;
    private Context mContext;

    public BaseRecycleViewAdapter(@NonNull Context context, List<T> list, int layoutId) {
        super(context, list);
        mLayoutId = layoutId;
        mList = list;
        mContext = context;

        addItemViewDelegate(new ItemViewDelegate<T>() {

            @Override
            public int getItemViewLayoutId() {
                return mLayoutId;
            }

            @Override
            public boolean isForViewType(T item, int position) {
                return true;
            }

            @Override
            public void convert(BaseViewHolder holder, T t, int position) {
                BaseRecycleViewAdapter.this.convert(holder, t, position);
            }
        });

    }

    protected abstract void convert(BaseViewHolder holder, T t, int position);
}
