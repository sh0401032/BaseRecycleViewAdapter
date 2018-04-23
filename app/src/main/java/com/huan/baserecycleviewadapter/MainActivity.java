package com.huan.baserecycleviewadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private MultiAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new String("字符串==" + i));
        }
        for (int i = 0; i < 20; i++) {
            Title title = new Title();
            title.setTitle("Title is " + i);
            title.setTime("Time is " + i);
            list.add(title);
        }
        adapter = new MultiAdapter(this, list);
        rv.setAdapter(adapter);
    }
}
