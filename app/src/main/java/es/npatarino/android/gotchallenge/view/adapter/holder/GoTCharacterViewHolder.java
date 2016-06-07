package es.npatarino.android.gotchallenge.view.adapter.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;

public class GoTCharacterViewHolder extends GoTViewHolder {

    private static final String TAG = GoTCharacterViewHolder.class.getSimpleName();

    @BindView(R.id.iv_background) ImageView ivBackground;
    @BindView(R.id.tv_name) TextView tvName;

    public GoTCharacterViewHolder(Context context, View itemView, ItemClickListener itemClickListener) {
        super(context, itemView, itemClickListener);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    public void render(GoTCharacter goTCharacter) {
        Picasso.with(context).load(goTCharacter.getImageUrl()).fit().centerInside().into(ivBackground);
        tvName.setText(goTCharacter.getName());
    }
}