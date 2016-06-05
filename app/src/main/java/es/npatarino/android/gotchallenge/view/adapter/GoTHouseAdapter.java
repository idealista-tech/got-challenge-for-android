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
import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;

public class GoTHouseAdapter extends AdapterListBase<GoTHouse> {

    private Activity activity;

    public GoTHouseAdapter(Activity activity, ItemClickListener itemClickListener) {
        super(itemClickListener);
        this.activity = activity;
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
        return new GotCharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.got_house_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        GotCharacterViewHolder gotCharacterViewHolder = (GotCharacterViewHolder) holder;
        gotCharacterViewHolder.render(data.get(position));
    }

    class GotCharacterViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "GotCharacterViewHolder";

        ImageView ivBackground;

        public GotCharacterViewHolder(View itemView) {
            super(itemView);
            ivBackground = (ImageView) itemView.findViewById(R.id.ivBackground);
        }

        public void render(GoTHouse goTHouse) {
            Picasso.with(activity).load(goTHouse.getHouseImageUrl()).into(ivBackground);
        }
    }

}