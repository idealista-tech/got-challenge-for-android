package es.npatarino.android.gotchallenge.view.adapter.holder;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;

public class GoTViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected Context context;
    private ItemClickListener itemClickListener;

    public GoTViewHolder(Context context, View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.context = context;
        this.itemClickListener = itemClickListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(itemClickListener != null)
            itemClickListener.onItemClick(view, getAdapterPosition());
    }
}
