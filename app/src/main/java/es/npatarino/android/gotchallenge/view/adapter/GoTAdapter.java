package es.npatarino.android.gotchallenge.view.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collection;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.view.adapter.holder.GoTCharacterViewHolder;
import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;

public class GoTAdapter extends AdapterListBase<GoTCharacter> {

    private int layout = R.layout.got_character_row;

    public GoTAdapter(Activity activity, ItemClickListener itemClickListener) {
        super(activity, itemClickListener);
    }

    @Override
    public GoTCharacter getItem(int position) {
        return data.get(position);
    }

    public void addAll(Collection<GoTCharacter> collection) {
        data.clear();
        for (int i = 0; i < collection.size(); i++) {
            data.add((GoTCharacter) collection.toArray()[i]);
        }
    }

    public void add(GoTCharacter goTCharacter){
        data.add(goTCharacter);
        notifyItemInserted(data.size());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GoTCharacterViewHolder(activity, inflate(parent, layout), itemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GoTCharacterViewHolder gotCharacterViewHolder = (GoTCharacterViewHolder) holder;
        gotCharacterViewHolder.render(data.get(position));
    }
}
