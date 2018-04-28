package com.huan.baserecycleviewadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HeaderAndFooterActivity extends AppCompatActivity {

    private RecyclerView rv;
    private View header;
    private HeaderAndFooterAdapter headerAndFooterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_and_footer);
        rv = findViewById(R.id.rv);
        header = LayoutInflater.from(this).inflate(R.layout.item_string, (ViewGroup) rv.getParent(), false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add(new String("title==" + i));
        }
        headerAndFooterAdapter = new HeaderAndFooterAdapter(list);
        headerAndFooterAdapter.addHeaderView(header);
        rv.setAdapter(headerAndFooterAdapter);

        View footer = LayoutInflater.from(this).inflate(R.layout.item_string, (ViewGroup) rv.getParent(), false);
        TextView tvString = footer.findViewById(R.id.tv_string);
        tvString.setText("Footer View");
        headerAndFooterAdapter.addFooterView(footer);

    }
}
