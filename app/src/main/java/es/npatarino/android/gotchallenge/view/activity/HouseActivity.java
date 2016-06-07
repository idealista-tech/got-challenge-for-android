package es.npatarino.android.gotchallenge.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.GoTApplication;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.injection.module.HouseActivityModule;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.view.fragment.GoTListFragment;

public class HouseActivity extends BaseActivity{

    public static final String TAG = HouseActivity.class.getSimpleName();

    public static final String EXTRA_HOUSE = "HOUSE";

    @BindView(R.id.toolbar) Toolbar toolbar;

    private GoTHouse goTHouse;

    public static void launch(Activity activity, GoTHouse house) {
        activity.startActivity(newInstance(activity, house));
    }

    private static Intent newInstance(Context context, GoTHouse house) {
        Intent intent = new Intent(context, HouseActivity.class);
        intent.putExtra(EXTRA_HOUSE, house);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getHouse();
        configToolbar();
        setFragment();
    }

    private void setFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, GoTListFragment.newInstanceHouse(goTHouse), GoTListFragment.TAG).commit();
    }

    private void configToolbar() {
        toolbar.setTitle(goTHouse.getHouseName());
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getHouse() {
        goTHouse = getIntent().getParcelableExtra(EXTRA_HOUSE);
    }

    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_house);
        ButterKnife.bind(this);
    }

    @Override
    protected void setupActivityComponent() {
        GoTApplication.get(this).getComponent()
                .plus(new HouseActivityModule(this)).inject(this);
    }
}
