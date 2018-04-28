package com.huan.baserecycleviewadapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by H_S on 2018/4/13.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private final SparseArray<View> mViews;
    private Context mContext;
    private View mConvertView;

    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    public static BaseViewHolder createBaseViewHolder(Context context, View itemView) {

        return new BaseViewHolder(context, itemView);
    }

    public static BaseViewHolder createBaseViewHolder(Context context, ViewGroup paraent, int layoutId) {
        View itemView = LayoutInflater.from(context).inflate(layoutId, paraent, false);
        return new BaseViewHolder(context, itemView);
    }

    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }


}
