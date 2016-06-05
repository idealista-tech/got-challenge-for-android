package es.npatarino.android.gotchallenge.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.GoTApplication;
import es.npatarino.android.gotchallenge.injection.module.HomeActivityModule;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.view.adapter.PagerAdapter;
import es.npatarino.android.gotchallenge.view.fragment.GoTHousesListFragment;
import es.npatarino.android.gotchallenge.view.fragment.GoTListFragment;

public class HomeActivity extends BaseActivity {

    @Inject PagerAdapter pagerAdapter;

    @BindView(R.id.container) ViewPager viewPager;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabs) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        setViewPager();
    }

    @Override
    protected void bindViews() {
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @Override
    protected void setupActivityComponent() {
        GoTApplication.get(this).getComponent()
                .plus(new HomeActivityModule(this))
                .inject(this);
    }

    private void setViewPager(){
        pagerAdapter.addFragment(GoTListFragment.newInstance(), getString(R.string.characters));
        pagerAdapter.addFragment(GoTHousesListFragment.newInstance(), getString(R.string.houses));
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
