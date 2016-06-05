package es.npatarino.android.gotchallenge.injection.module;

import dagger.Module;
import dagger.Provides;
import es.npatarino.android.gotchallenge.injection.scope.ActivityScope;
import es.npatarino.android.gotchallenge.view.activity.DetailActivity;

@Module
public class DetailActivityModule {

    DetailActivity detailActivity;

    public DetailActivityModule(DetailActivity detailActivity) {
        this.detailActivity = detailActivity;
    }

    @Provides @ActivityScope
    DetailActivity provideDetailActivity(){
        return detailActivity;
    }
}
