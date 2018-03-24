package com.billigeplaetze.agency.charioagent;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class QueueAdapter extends RecyclerView.Adapter<QueueAdapter.ViewHolder> {
    private String[] dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ViewHolder(TextView v){
            super(v);
            textView = v;
        }
    }

    public QueueAdapter(String[] dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public QueueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.code_view, parent, false);
       ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QueueAdapter.ViewHolder holder, int position) {
        holder.textView.setText(dataSet[position]);
    }

    @Override
    public int getItemCount() {
        return dataSet.length;
    }
}
