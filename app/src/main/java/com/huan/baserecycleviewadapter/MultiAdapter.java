package com.huan.baserecycleviewadapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.huan.baserecycleviewadapter.base.BaseViewHolder;
import com.huan.baserecycleviewadapter.base.ItemViewDelegate;
import com.huan.baserecycleviewadapter.base.MultiItemTypeAdapter;

import java.util.List;

/**
 * Created by H_S on 2018/4/21.
 */

public class MultiAdapter extends MultiItemTypeAdapter<Object> {
    public MultiAdapter(Context context, List<Object> list) {
        super(context, list);
        addItemViewDelegate(new StringItemType());
        addItemViewDelegate(new TitleItemType());
    }


    class TitleItemType implements ItemViewDelegate<Object> {

        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_title;
        }

        @Override
        public boolean isForViewType(Object item, int position) {
            return item instanceof Title ? true : false;
        }

        @Override
        public void convert(BaseViewHolder holder, Object o, int position) {
            if (o instanceof Title) {
                Title title = (Title) o;
                TextView tvTitle = holder.getView(R.id.tv_title);
                TextView tvTime = holder.getView(R.id.tv_time);
                tvTitle.setText(title.getTitle());
                tvTime.setText(title.getTime());
            }
        }

    }

    class StringItemType implements ItemViewDelegate<Object> {
        @Override
        public int getItemViewLayoutId() {
            return R.layout.item_string;
        }

        @Override
        public boolean isForViewType(Object item, int position) {
            return item instanceof String ? true : false;
        }

        @Override
        public void convert(BaseViewHolder holder, Object s, int position) {
            TextView tv = holder.getView(R.id.tv_string);
            tv.setText((String) s);
        }
    }
}
