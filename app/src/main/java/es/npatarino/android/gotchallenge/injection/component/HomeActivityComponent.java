package es.npatarino.android.gotchallenge.injection.component;

import dagger.Subcomponent;
import es.npatarino.android.gotchallenge.injection.scope.ActivityScope;
import es.npatarino.android.gotchallenge.injection.module.HomeActivityModule;
import es.npatarino.android.gotchallenge.view.activity.HomeActivity;

@ActivityScope
@Subcomponent(modules = {
        HomeActivityModule.class
})
public interface HomeActivityComponent {
    HomeActivity inject(HomeActivity homeActivity);
}
