package com.billigeplaetze.agency.charioagent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QueueActivity extends AppCompatActivity {
    @BindView(R.id.queue_view)
    RecyclerView queue;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] codes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);
        ButterKnife.bind(this);

        queue.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        queue.setLayoutManager(layoutManager);

        adapter = new QueueAdapter(codes);
        queue.setAdapter(adapter);
    }
}
