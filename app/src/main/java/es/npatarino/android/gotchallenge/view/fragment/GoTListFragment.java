package es.npatarino.android.gotchallenge.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.Constants;
import es.npatarino.android.gotchallenge.GoTApplication;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.injection.module.GoTListFragmentModule;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.repository.GoTRepository;
import es.npatarino.android.gotchallenge.view.activity.DetailActivity;
import es.npatarino.android.gotchallenge.view.adapter.GoTAdapter;
import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;
import rx.Observable;
import rx.Subscription;

public class GoTListFragment extends FragmentBase implements ItemClickListener {

    public static final String TAG = GoTListFragment.class.getSimpleName();

    @Inject GoTAdapter adapter;
    @Inject LayoutManager layoutManager;
    @Inject GoTRepository goTRepository;

    @BindView(R.id.progressBar) ContentLoadingProgressBar progressBar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private SearchView searchView;

    private GoTHouse goTHouse;
    private String query;

    private Subscription subscription;

    public static Fragment newInstance() {
        return new GoTListFragment();
    }

    public static Fragment newInstanceHouse(GoTHouse house) {
        Fragment fragment = new GoTListFragment();
        Bundle args = new Bundle();
        args.putParcelable(Constants.ViewFlow.EXTRA_HOUSE, house);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getHouse();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, rootView);

        configRecyclerView();
        displayLoading(true);
        getCharacters();

        return rootView;
    }

    private void getHouse(){
        if(getArguments() != null)
            goTHouse = getArguments().getParcelable(Constants.ViewFlow.EXTRA_HOUSE);
    }

    private void getCharacters(){
        Observable<GoTCharacter> character = goTRepository.getCharacters()
                .concatMap(Observable::from);
        character = checkHouse(character);
        character = checkQuery(character);
        subscription = character.toList().subscribe(characters -> {
                displayLoading(false);
                displayCharacters(characters);
            }, error -> {
                displayLoading(false);
            });
    }

    private Observable<GoTCharacter> checkQuery(Observable<GoTCharacter> character){
        if(query != null && !query.isEmpty())
            character = character.filter(goTCharacter ->
                    goTCharacter.getName().contains(query));
        return character;
    }

    private Observable<GoTCharacter> checkHouse(Observable<GoTCharacter> character){
        if(goTHouse != null)
            character = character.filter(goTCharacter ->
                    goTCharacter.getHouseId().equals(goTHouse.getHouseId()));
        return character;
    }

    private void displayCharacters(List<GoTCharacter> characters) {
        adapter.addAll(characters);
        adapter.notifyDataSetChanged();
    }

    private void displayLoading(boolean show) {
        if (show)
            progressBar.show();
        else
            progressBar.hide();
    }

    private void configRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void setupActivityComponent() {
        GoTApplication.get(getActivity()).getComponent()
                .plus(new GoTListFragmentModule(this)).inject(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        DetailActivity.launch(getActivity(), adapter.getItem(position));
    }

    @Override
    public void onActivityCreated(Bundle savedState) {
        super.onActivityCreated(savedState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.characters, menu);
        configSearchView(menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void configSearchView(Menu menu) {
        MenuItem searchItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnCloseListener(() -> {
            query = "";
            displayLoading(true);
            getCharacters();
            return false;
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query2) {
                query = query2;
                displayLoading(true);
                getCharacters();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}