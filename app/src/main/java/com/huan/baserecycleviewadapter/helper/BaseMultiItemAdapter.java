package com.huan.baserecycleviewadapter.helper;

import android.support.annotation.LayoutRes;
import android.util.SparseIntArray;
import android.view.ViewGroup;

import com.huan.baserecycleviewadapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by H_S on 2018/5/2.
 */

public abstract class BaseMultiItemAdapter<T> extends BaseRvAdapter<T> {
    private SparseIntArray layouts;
    private static final int DEFAULT_VIEW_TYPE = -0xff;
    public static final int TYPE_NOT_FOUND = -404;
    public BaseMultiItemAdapter(List<T> list) {
        super(list);
    }

    @Override
    public BaseViewHolder createDefViewHolder(ViewGroup parent, int viewType) {
        return createBaseViewHolder(parent, getLayoutId(viewType));
    }

    public void setDefaultViewTypeLayout(@LayoutRes int layoutId) {
        addItemType(DEFAULT_VIEW_TYPE, layoutId);
    }

    private int getLayoutId(int itemType) {
        return layouts.get(itemType, TYPE_NOT_FOUND);
    }


    protected void addItemType(int itemType, @LayoutRes int layoutId) {
        if (layouts == null) {
            layouts = new SparseIntArray();
        }
        layouts.put(itemType, layoutId);
    }
}
