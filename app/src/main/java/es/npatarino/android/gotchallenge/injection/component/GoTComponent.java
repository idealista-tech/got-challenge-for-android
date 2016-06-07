package es.npatarino.android.gotchallenge.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import es.npatarino.android.gotchallenge.injection.module.DetailActivityModule;
import es.npatarino.android.gotchallenge.injection.module.GoTHousesListFragmentModule;
import es.npatarino.android.gotchallenge.injection.module.GoTListFragmentModule;
import es.npatarino.android.gotchallenge.injection.module.GoTModule;
import es.npatarino.android.gotchallenge.injection.module.GoTRepositoryModule;
import es.npatarino.android.gotchallenge.injection.module.HomeActivityModule;
import es.npatarino.android.gotchallenge.injection.module.HouseActivityModule;

@Singleton
@Component(modules = {
        GoTModule.class,
        GoTRepositoryModule.class
})
public interface GoTComponent {
    HomeActivityComponent plus(HomeActivityModule homeActivityModule);
    DetailActivityComponent plus(DetailActivityModule detailActivityModule);
    HouseActivityComponent plus(HouseActivityModule houseActivityModule);

    GoTListFragmentComponent plus(GoTListFragmentModule goTListFragmentModule);
    GoTHousesListFragmentComponent plus(GoTHousesListFragmentModule goTHousesListFragmentModule);
}
