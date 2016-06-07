package es.npatarino.android.gotchallenge.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.injection.module.DetailActivityModule;
import es.npatarino.android.gotchallenge.GoTApplication;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.GoTCharacter;

public class DetailActivity extends BaseActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private static final String EXTRA_CHARACTER = "CHARACTER";

    @BindView(R.id.iv_photo) ImageView ivPhoto;
    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.tv_description) TextView tvDescripcion;
    @BindView(R.id.toolbar) Toolbar toolbar;

    private GoTCharacter character;

    public static void launch(Activity activity, GoTCharacter character) {
        activity.startActivity(newInstance(activity, character));
    }

    private static Intent newInstance(Context context, GoTCharacter character) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_CHARACTER, character);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getExtraData();
        configToolbar();
        displayCharacter();
    }

    private void displayCharacter() {
        Picasso.with(this).load(character.getImageUrl()).into(ivPhoto);
        tvName.setText(character.getName());
        tvDescripcion.setText(character.getDescription());
    }

    private void configToolbar() {
        toolbar.setTitle(character.getName());
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
    }

    @Override
    protected void setupActivityComponent() {
        GoTApplication.get(this).getComponent()
                .plus(new DetailActivityModule(this))
                .inject(this);
    }

    public void getExtraData() {
        character = getIntent().getParcelableExtra(EXTRA_CHARACTER);
    }
}
