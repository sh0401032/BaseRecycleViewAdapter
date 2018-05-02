package com.huan.baserecycleviewadapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.huan.baserecycleviewadapter.base.BaseViewHolder;
import com.huan.baserecycleviewadapter.helper.BaseMultiItemAdapter;

import java.util.List;

/**
 * Created by H_S on 2018/4/21.
 */

public class MultiAdapter extends BaseMultiItemAdapter<Object> {

    private Context mContext;

    public MultiAdapter(Context context, List<Object> list) {
        super(list);
        mContext = context;
    }

    @Override
    public void covert(BaseViewHolder holder, Object item) {
        switch (holder.getItemViewType()) {
            case 1:
                if (item instanceof Title) {
                    Title title = (Title) item;
                    TextView tvTitle = holder.getView(R.id.tv_title);
                    TextView tvTime = holder.getView(R.id.tv_time);
                    tvTitle.setText(title.getTitle());
                    tvTime.setText(title.getTime());
                }
                break;
            case 2:
                if (item instanceof String) {
                    TextView tv = holder.getView(R.id.tv_string);
                    tv.setText((String) item);
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(mContext, HeaderAndFooterActivity.class);
                            mContext.startActivity(intent);
                        }
                    });
                }
                break;
        }
    }

    @Override
    protected int getDefItemViewType(int position) {
        Object obj = mData.get(position);
        if (obj instanceof Title) {
            addItemType(1, R.layout.item_title);
            return 1;
        } else if (obj instanceof String) {
            addItemType(2, R.layout.item_string);
            return 2;
        }
        return super.getDefItemViewType(position);
    }
}
