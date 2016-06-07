package es.npatarino.android.gotchallenge.view.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;

public class GoTHouseViewHolder extends GoTViewHolder {

    private static final String TAG = GoTHouseViewHolder.class.getSimpleName();

    @BindView(R.id.iv_background) ImageView ivBackground;

    public GoTHouseViewHolder(Context context, View itemView, ItemClickListener itemClickListener) {
        super(context, itemView, itemClickListener);
        ButterKnife.bind(this, itemView);
    }

    public void render(GoTHouse goTHouse) {
        if(goTHouse.getHouseImageUrl() != null && goTHouse.getHouseImageUrl().length() > 0)
            Picasso.with(context).load(goTHouse.getHouseImageUrl()).fit().centerInside().into(ivBackground);
    }
}
