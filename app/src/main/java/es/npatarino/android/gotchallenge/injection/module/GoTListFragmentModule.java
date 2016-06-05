package es.npatarino.android.gotchallenge.injection.module;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutManager;

import dagger.Module;
import dagger.Provides;
import es.npatarino.android.gotchallenge.injection.scope.FragmentScope;
import es.npatarino.android.gotchallenge.view.adapter.GoTAdapter;
import es.npatarino.android.gotchallenge.view.fragment.GoTListFragment;
import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;

@Module
public class GoTListFragmentModule {
    GoTListFragment goTListFragment;

    public GoTListFragmentModule(GoTListFragment goTListFragment) {
        this.goTListFragment = goTListFragment;
    }

    @Provides @FragmentScope
    GoTListFragment provideGoTListFragment(){
        return goTListFragment;
    }

    @Provides @FragmentScope
    GoTAdapter provideGoTAdapter(){
        return new GoTAdapter(goTListFragment.getActivity(), goTListFragment);
    }

    @Provides @FragmentScope
    LayoutManager provideLayoutManager(Context context){
        return new LinearLayoutManager(context);
    }
}
