package es.npatarino.android.gotchallenge.view.fragment;


import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.GoTApplication;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.injection.module.GoTHousesListFragmentModule;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.repository.GoTRepository;
import es.npatarino.android.gotchallenge.view.activity.HouseActivity;
import es.npatarino.android.gotchallenge.view.adapter.GoTHouseAdapter;
import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GoTHousesListFragment extends FragmentBase implements ItemClickListener{

    private static final String TAG = GoTHousesListFragment.class.getSimpleName();

    @BindView(R.id.progressBar) ContentLoadingProgressBar progressBar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    @Inject GoTHouseAdapter adapter;
    @Inject GoTRepository goTRepository;

    public static GoTHousesListFragment newInstance() {
        return new GoTHousesListFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, rootView);

        configRecyclerView();
        displayLoading(true);

        getHouses();

        return rootView;
    }

    private void getHouses() {
        goTRepository.getCharacters()
                .map(goTCharacters -> Observable.from(goTCharacters)
                        .concatMap(goTCharacter -> Observable.just(new GoTHouse(goTCharacter)))
                        .filter(goTHouse -> goTHouse.getHouseId() != null && goTHouse.getHouseId().length() > 0)
                        .distinct(GoTHouse::getHouseId).toList().toBlocking().single())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(houses -> {
                    displayLoading(false);
                    displayHouses(houses);
                }, error -> {
                    displayLoading(false);
                });
    }

    private void configRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void displayHouses(List<GoTHouse> houses) {
        adapter.addAll(houses);
        adapter.notifyDataSetChanged();
    }

    private void displayLoading(boolean show) {
        if (show)
            progressBar.show();
        else
            progressBar.hide();
    }

    @Override
    protected void setupActivityComponent() {
        GoTApplication.get(getActivity()).getComponent()
                .plus(new GoTHousesListFragmentModule(this))
                .inject(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        HouseActivity.launch(getActivity(), adapter.getItem(position));
    }
}
