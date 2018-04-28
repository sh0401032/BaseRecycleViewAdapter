package com.huan.baserecycleviewadapter;

import android.widget.TextView;

import com.huan.baserecycleviewadapter.base.BaseViewHolder;
import com.huan.baserecycleviewadapter.helper.BaseRvAdapter;

import java.util.List;

/**
 * Created by H_S on 2018/4/28.
 */

public class HeaderAndFooterAdapter extends BaseRvAdapter<String> {

    public HeaderAndFooterAdapter(List<String> list) {

        super(R.layout.item_title, list);
    }

    @Override
    public void covert(BaseViewHolder holder, String item) {
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvTime = holder.getView(R.id.tv_time);
        tvTitle.setText(item.toString());
        tvTime.setText("==============================");
    }


}
