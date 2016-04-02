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
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.domain.GoTCharacter;
import es.npatarino.android.gotchallenge.view.activities.DetailActivity;

/**
 * @author Antonio López.
 */
public class GoTAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<GoTCharacter> gcs;
    private Activity activity;

    public GoTAdapter(Activity activity) {
        this.gcs = new ArrayList<>();
        this.activity = activity;
    }

    public void addAll(Collection<GoTCharacter> collection) {
        for (int i = 0; i < collection.size(); i++) {
            gcs.add((GoTCharacter) collection.toArray()[i]);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GotCharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.got_character_row, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final GotCharacterViewHolder gotCharacterViewHolder = (GotCharacterViewHolder) holder;
        final GoTCharacter character = gcs.get(position);
        gotCharacterViewHolder.render(character);
        final GotCharacterViewHolder viewHolder =((GotCharacterViewHolder) holder);
        viewHolder.imp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                moveToDetailActivity(gotCharacterViewHolder, character);
            }
        });
    }

    private void moveToDetailActivity(GotCharacterViewHolder viewHolder, GoTCharacter character){
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity, viewHolder.itemView, DetailActivity.CHARACTER_IMAGE);

        Intent intent = new Intent(viewHolder.itemView.getContext(), DetailActivity.class);
        intent.putExtra(DetailActivity.DESCRIPTION, character.getDescription());
        intent.putExtra(DetailActivity.NAME, character.getName());
        intent.putExtra(DetailActivity.IMAGE_URL, character.getImageUrl());
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }

    @Override
    public int getItemCount() {
        return gcs.size();
    }

    class GotCharacterViewHolder extends RecyclerView.ViewHolder {

        private static final String TAG = "GotCharacterViewHolder";
        ImageView imp;
        TextView tvn;

        public GotCharacterViewHolder(View itemView) {
            super(itemView);
            imp = (ImageView) itemView.findViewById(R.id.ivBackground);
            tvn = (TextView) itemView.findViewById(R.id.tv_name);
        }

        public void render(final GoTCharacter goTCharacter) {
            Picasso.with(imp.getContext()).load(goTCharacter.getImageUrl()).into(imp);
            tvn.setText(goTCharacter.getName());
        }
    }

}