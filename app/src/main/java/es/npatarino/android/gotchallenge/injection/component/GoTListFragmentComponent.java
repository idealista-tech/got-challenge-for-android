package es.npatarino.android.gotchallenge.injection.component;

import dagger.Subcomponent;
import es.npatarino.android.gotchallenge.injection.module.GoTListFragmentModule;
import es.npatarino.android.gotchallenge.injection.scope.FragmentScope;
import es.npatarino.android.gotchallenge.view.fragment.GoTListFragment;

@FragmentScope
@Subcomponent(modules = {
        GoTListFragmentModule.class
})
public interface GoTListFragmentComponent {
    GoTListFragment inject (GoTListFragment goTListFragment);
}
