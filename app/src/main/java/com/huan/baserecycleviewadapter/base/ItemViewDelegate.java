package com.huan.baserecycleviewadapter.base;

/**
 * Created by H_S on 2018/4/21.
 */

public interface ItemViewDelegate<D> {
    int getItemViewLayoutId();

    boolean isForViewType(D item, int position);

    void convert(BaseViewHolder holder, D d, int position);
}
