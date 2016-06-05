package es.npatarino.android.gotchallenge.injection.component;

import dagger.Subcomponent;
import es.npatarino.android.gotchallenge.injection.module.GoTHousesListFragmentModule;
import es.npatarino.android.gotchallenge.injection.scope.FragmentScope;
import es.npatarino.android.gotchallenge.view.fragment.GoTHousesListFragment;

@FragmentScope
@Subcomponent(modules = {
        GoTHousesListFragmentModule.class
})
public interface GoTHousesListFragmentComponent {
    GoTHousesListFragment inject(GoTHousesListFragment goTHousesListFragment);
}
