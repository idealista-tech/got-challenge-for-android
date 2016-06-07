package es.npatarino.android.gotchallenge.view.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Collection;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.view.adapter.holder.GoTHouseViewHolder;
import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;

public class GoTHouseAdapter extends AdapterListBase<GoTHouse> {

    private int layout = R.layout.got_house_row;

    public GoTHouseAdapter(Activity activity, ItemClickListener itemClickListener) {
        super(activity, itemClickListener);
    }

    @Override
    public GoTHouse getItem(int position) {
        return data.get(position);
    }

    public void addAll(Collection<GoTHouse> collection) {
        for (int i = 0; i < collection.size(); i++) {
            data.add((GoTHouse) collection.toArray()[i]);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoTHouseViewHolder(activity, inflate(parent, layout), itemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GoTHouseViewHolder gotCharacterViewHolder = (GoTHouseViewHolder) holder;
        gotCharacterViewHolder.render(data.get(position));
    }

}