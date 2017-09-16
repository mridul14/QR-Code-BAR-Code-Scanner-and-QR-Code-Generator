package com.example.apple.miniproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by apple on 14/07/17.
 */

public class HistoryAdapter  extends RecyclerView.Adapter<Holder>

{
    Context context;//holds the values of activity
    ArrayList<Model>historylist;

    public HistoryAdapter(Context context, ArrayList<Model> historylist) {
        this.context = context;
        this.historylist = historylist;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.simple_card_item,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Model history= historylist.get(position);
        holder.tvresult.setText(history.getResult());
    }

    @Override
    public int getItemCount() {
        return historylist.size();
    }
}
