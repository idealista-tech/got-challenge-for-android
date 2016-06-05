package es.npatarino.android.gotchallenge.injection.component;

import dagger.Subcomponent;
import es.npatarino.android.gotchallenge.injection.scope.ActivityScope;
import es.npatarino.android.gotchallenge.injection.module.DetailActivityModule;
import es.npatarino.android.gotchallenge.view.activity.DetailActivity;

@ActivityScope
@Subcomponent(modules = {
        DetailActivityModule.class
})
public interface DetailActivityComponent {

    DetailActivity inject(DetailActivity detailActivity);

}
