package es.npatarino.android.gotchallenge.injection.module;

import dagger.Module;
import dagger.Provides;
import es.npatarino.android.gotchallenge.injection.scope.ActivityScope;
import es.npatarino.android.gotchallenge.view.activity.DetailActivity;
import es.npatarino.android.gotchallenge.view.activity.HouseActivity;

@Module
public class HouseActivityModule {

    HouseActivity houseActivity;

    public HouseActivityModule(HouseActivity houseActivity) {
        this.houseActivity = houseActivity;
    }

    @Provides @ActivityScope
    HouseActivity provideHouseActivity(){
        return houseActivity;
    }
}
