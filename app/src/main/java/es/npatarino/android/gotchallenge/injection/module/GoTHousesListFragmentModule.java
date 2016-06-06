package es.npatarino.android.gotchallenge.injection.module;

import dagger.Module;
import dagger.Provides;
import es.npatarino.android.gotchallenge.injection.scope.FragmentScope;
import es.npatarino.android.gotchallenge.view.adapter.GoTHouseAdapter;
import es.npatarino.android.gotchallenge.view.fragment.GoTHousesListFragment;

@Module
public class GoTHousesListFragmentModule {

    GoTHousesListFragment goTHousesListFragment;

    public GoTHousesListFragmentModule(GoTHousesListFragment goTHousesListFragment) {
        this.goTHousesListFragment = goTHousesListFragment;
    }

    @Provides @FragmentScope
    GoTHousesListFragment provideGoTHousesListFragment(){
        return goTHousesListFragment;
    }

    @Provides @FragmentScope
    GoTHouseAdapter provideGoTHouseAdapter(){
        return new GoTHouseAdapter(goTHousesListFragment.getActivity(), goTHousesListFragment);
    }
}
