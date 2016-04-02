package es.npatarino.android.gotchallenge.view.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.domain.GoTHouse;
import es.npatarino.android.gotchallenge.view.activities.DetailActivity;

/**
 * @author Antonio López.
 */
public class GoTHouseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<GoTHouse> gcs;
    private Activity activity;

    public GoTHouseAdapter(Activity activity) {
        this.gcs = new ArrayList<>();
        this.activity = activity;
    }


    public void addAll(Collection<GoTHouse> collection) {
        for (int i = 0; i < collection.size(); i++) {
            gcs.add((GoTHouse) collection.toArray()[i]);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GotHouseViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.got_house_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final GotHouseViewHolder gotCharacterViewHolder = (GotHouseViewHolder) holder;
        GotHouseViewHolder gotHouseViewHolder = (GotHouseViewHolder) holder;
        final GoTHouse house = gcs.get(position);
        gotCharacterViewHolder.render(house);
        gotHouseViewHolder.imp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                moveToDetailActivity(gotCharacterViewHolder, house);
            }
        });
    }

    private void moveToDetailActivity(GotHouseViewHolder viewHolder, GoTHouse house){
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity, viewHolder.itemView, DetailActivity.HOUSE_IMAGE);

        Intent intent = new Intent(viewHolder.itemView.getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.HOUSE_ID, house.getHouseId());
        intent.putExtra(DetailActivity.NAME, house.getHouseName());
        intent.putExtra(DetailActivity.IMAGE_URL, house.getHouseImageUrl());
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    public int getItemCount() {
        return gcs.size();
    }

    class GotHouseViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "GotCharacterViewHolder";
        ImageView imp;

        public GotHouseViewHolder(View itemView) {
            super(itemView);
            imp = (ImageView) itemView.findViewById(R.id.ivBackground);
        }

        public void render(final GoTHouse goTHouse) {
            Picasso.with(imp.getContext()).load(goTHouse.getHouseImageUrl()).into(imp);
        }
    }

}