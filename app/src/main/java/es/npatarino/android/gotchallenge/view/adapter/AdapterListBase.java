package es.npatarino.android.gotchallenge.view.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;


abstract  public class AdapterListBase<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<T> data;

    protected ItemClickListener itemClickListener;

    public AdapterListBase(ItemClickListener itemClickListener) {
        this.data = new ArrayList<>();
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public abstract T getItem(int position);

    protected abstract void addAll(Collection<T> data);
}
