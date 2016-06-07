package es.npatarino.android.gotchallenge.injection.component;

import dagger.Subcomponent;
import es.npatarino.android.gotchallenge.injection.module.HouseActivityModule;
import es.npatarino.android.gotchallenge.injection.scope.ActivityScope;
import es.npatarino.android.gotchallenge.view.activity.DetailActivity;
import es.npatarino.android.gotchallenge.view.activity.HouseActivity;

@ActivityScope
@Subcomponent(modules = {
        HouseActivityModule.class
})
public interface HouseActivityComponent {

    HouseActivity inject(HouseActivity houseActivity);

}
