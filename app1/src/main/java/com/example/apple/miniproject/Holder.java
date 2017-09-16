package com.example.apple.miniproject;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by apple on 14/07/17.
 */

public class Holder extends RecyclerView.ViewHolder {

    private final CardView container;
    public final TextView tvresult;

    public Holder(View itemView) {
        super(itemView);
        tvresult = itemView.findViewById(R.id.tvresult);
        container = itemView.findViewById(R.id.container);
    }
}
