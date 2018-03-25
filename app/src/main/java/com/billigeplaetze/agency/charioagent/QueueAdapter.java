package com.billigeplaetze.agency.charioagent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class QueueAdapter extends RecyclerView.Adapter<QueueAdapter.ViewHolder> {
    private final Context context;
    private List<Transaction> dataSet;

     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public View view;
        private final TextView textView;

        public ViewHolder(View v){
            super(v);
            view = v;
            textView = (TextView) v.findViewById(R.id.text_view);
            v.setOnClickListener(this);
        }
        public TextView getTextView() {
            return textView;
        }

        @Override
        public void onClick(View view) {

        }
    }

    public QueueAdapter(List<Transaction> dataSet, Context c) {
        this.dataSet = dataSet;
        this.context = c;
    }

    @Override
    public QueueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.code_view, parent, false);
       ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QueueAdapter.ViewHolder holder, final int position) {
        holder.getTextView().setText(dataSet.get(position).getTransactionId().donationCode);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                bundle.putString("description",dataSet.get(position).getTaskDescription());
                bundle.putString("Type", dataSet.get(position).getType());
                bundle.putDouble("amount", dataSet.get(position).getDonationAmount());
                Intent intent = new Intent(context, TodoActivity.class);
                intent.putExtra("extra", bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }
}
