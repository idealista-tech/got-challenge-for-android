package es.npatarino.android.gotchallenge.view.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;


abstract  public class AdapterListBase<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Activity activity;

    protected List<T> data;

    protected ItemClickListener itemClickListener;

    public AdapterListBase(Activity activity, ItemClickListener itemClickListener) {
        this.data = new ArrayList<>();
        this.activity = activity;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public abstract T getItem(int position);

    protected abstract void addAll(Collection<T> data);

    protected View inflate(ViewGroup parent, int layout) {
        return LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);
    }
}
