package com.huan.baserecycleviewadapter.helper;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.huan.baserecycleviewadapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by H_S on 2018/4/28.
 */

public abstract class BaseRvAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private List<T> mData;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private int mLayoutId;
    private LinearLayout mHeaderLayout;
    private LinearLayout mFooterLayout;
    public static final int HEADER_VIEW = 0x00000111;
    public static final int FOOTER_VIEW = 0x00000222;
    public static final int EMPTY_VIEW = 0x00000555;

    public BaseRvAdapter(@LayoutRes int layoutId, List<T> list) {
        mData = list == null ? new ArrayList<T>() : list;
        if (layoutId != 0) {
            mLayoutId = layoutId;
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = null;
        mContext = parent.getContext();
        mLayoutInflater = LayoutInflater.from(mContext);
        switch (viewType) {
            case HEADER_VIEW:
                holder = BaseViewHolder.createBaseViewHolder(mHeaderLayout);
                break;
            case FOOTER_VIEW:
                holder = BaseViewHolder.createBaseViewHolder(mFooterLayout);
                break;
            default:
                holder = createViewHolder(parent);
                break;
        }
        return holder;
    }

    private BaseViewHolder createViewHolder(ViewGroup parent) {
        int layoutId = mLayoutId;

        return BaseViewHolder.createBaseViewHolder(getItemView(layoutId, parent));
    }

    private View getItemView(int layoutId, ViewGroup parent) {
        return mLayoutInflater.inflate(layoutId, parent, false);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int itemViewType = holder.getItemViewType();
        switch (itemViewType) {
            case 0: // 默认类型super.getItemViewType()
                covert(holder, getItem(position - getHeaderViewCount()));
                break;
            case HEADER_VIEW:
                break;
            case FOOTER_VIEW:
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        int headerViewCount = getHeaderViewCount();
        if (position < headerViewCount) { // 头
            return HEADER_VIEW;
        } else {
            int tmp = position - headerViewCount;
            int adapterCount = mData.size();
            if (tmp < adapterCount) { // 数据
                return getDefItemViewType(position);
            } else {
                tmp = tmp - adapterCount;
                if (tmp < getFooterViewCount()) {// 尾
                    return FOOTER_VIEW;
                } else {
                    return -1;
                }
            }
        }
    }

    // 返回adapter定义的数据类型
    private int getDefItemViewType(int position) {

        return super.getItemViewType(position);// 0
    }

    @Override
    public int getItemCount() {
        return mData.size() + getHeaderViewCount() + getFooterViewCount();
    }

    private T getItem(int position) {
        if (position < mData.size()) {
            return mData.get(position);
        }
        return null;
    }

    public abstract void covert(BaseViewHolder holder, T item);

    private int getHeaderViewCount() {
        if (mHeaderLayout == null || mHeaderLayout.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    private int getFooterViewCount() {
        if (mFooterLayout == null || mFooterLayout.getChildCount() == 0) {
            return 0;
        }
        return 1;
    }

    public int addHeaderView(View header) {
        return addHeaderView(header, -1);
    }

    public int addHeaderView(View header, int index) {
        if (mHeaderLayout == null) {
            mHeaderLayout = new LinearLayout(header.getContext());
            mHeaderLayout.setOrientation(LinearLayout.VERTICAL);
            mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        final int childCount = mHeaderLayout.getChildCount();
        if (index < 0 || index > childCount) {
            index = childCount;
        }
        mHeaderLayout.addView(header, index);
        if (mHeaderLayout.getChildCount() == 1) {
            notifyItemInserted(0);// 第一次添加
        }
        return index;
    }

    public int addFooterView(View footer) {
        return addFooterView(footer, -1);
    }

    public int addFooterView(View footer, int index) {
        if (mFooterLayout == null) {
            mFooterLayout = new LinearLayout(footer.getContext());
            mFooterLayout.setOrientation(LinearLayout.VERTICAL);
            mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        final int childCount = mFooterLayout.getChildCount();
        if (index < 0 || index > childCount) {
            index = childCount;
        }
        mFooterLayout.addView(footer, index);
        if (mFooterLayout.getChildCount() == 1) {// 第一次添加
            notifyItemInserted(mData.size() + getHeaderViewCount());
        }
        return index;
    }
}
