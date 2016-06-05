package es.npatarino.android.gotchallenge.injection.module;

import android.support.v4.app.FragmentManager;

import dagger.Module;
import dagger.Provides;
import es.npatarino.android.gotchallenge.injection.scope.ActivityScope;
import es.npatarino.android.gotchallenge.view.activity.HomeActivity;
import es.npatarino.android.gotchallenge.view.adapter.PagerAdapter;

@Module
public class HomeActivityModule {

    HomeActivity homeActivity;

    public HomeActivityModule(HomeActivity homeActivity) {
        this.homeActivity = homeActivity;
    }

    @Provides @ActivityScope
    HomeActivity provideHomeActivity(){
        return homeActivity;
    }

    @Provides @ActivityScope
    FragmentManager provideFragmentManager(){
        return homeActivity.getSupportFragmentManager();
    }

    @Provides @ActivityScope
    PagerAdapter providePagerAdapter(FragmentManager fragmentManager){
        return new PagerAdapter(fragmentManager);
    }
}
