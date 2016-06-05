package es.npatarino.android.gotchallenge.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.view.activity.DetailActivity;
import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;

public class GoTAdapter extends AdapterListBase<GoTCharacter> {

    private Activity activity;

    public GoTAdapter(Activity activity, ItemClickListener itemClickListener) {
        super(itemClickListener);
        this.activity = activity;
    }

    @Override
    public GoTCharacter getItem(int position) {
        return data.get(position);
    }

    public void addAll(Collection<GoTCharacter> collection) {
        for (int i = 0; i < collection.size(); i++) {
            data.add((GoTCharacter) collection.toArray()[i]);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GotCharacterViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.got_character_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        GotCharacterViewHolder gotCharacterViewHolder = (GotCharacterViewHolder) holder;
        GoTCharacter character = data.get(position);
        gotCharacterViewHolder.render(character);
        ((GotCharacterViewHolder) holder).ivBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                itemClickListener.onItemClick(v, position);
//                GoTAdapter.this.onClick((GotCharacterViewHolder) holder, character);
            }
        });
    }

    private void onClick(GotCharacterViewHolder holder, GoTCharacter character) {
        Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
        intent.putExtra("description", character.getDescription());
        intent.putExtra("name", character.getName());
        intent.putExtra("imageUrl", character.getImageUrl());

        holder.itemView.getContext().startActivity(intent);
    }

    class GotCharacterViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "GotCharacterViewHolder";

        ImageView ivBackground;
        TextView tvName;

        public GotCharacterViewHolder(View itemView) {
            super(itemView);
            ivBackground = (ImageView) itemView.findViewById(R.id.ivBackground);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
        }

        public void render(GoTCharacter goTCharacter) {
            Picasso.with(activity).load(goTCharacter.getImageUrl()).into(ivBackground);
            tvName.setText(goTCharacter.getName());
        }
    }

}
