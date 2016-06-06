package es.npatarino.android.gotchallenge.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.GoTApplication;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.injection.module.GoTHousesListFragmentModule;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.GoTHouse;
import es.npatarino.android.gotchallenge.repository.GoTRepository;
import es.npatarino.android.gotchallenge.view.adapter.GoTHouseAdapter;
import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class GoTHousesListFragment extends FragmentBase implements ItemClickListener{

    private static final String TAG = "GoTHousesListFragment";

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

        goTRepository.getCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(characters -> {
                    displayLoading(false);
                    displayHouses(characters);
                }, error -> {
                    displayLoading(false);
                });

        return rootView;
    }

    private void configRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void displayHouses(List<GoTCharacter> characters) {
        ArrayList<GoTHouse> houses = extractHouses(characters);
        adapter.addAll(houses);
        adapter.notifyDataSetChanged();
    }

    private void displayLoading(boolean show) {
        if (show)
            progressBar.show();
        else
            progressBar.hide();
    }

    @NonNull
    private ArrayList<GoTHouse> extractHouses(List<GoTCharacter> characters) {
        ArrayList<GoTHouse> houses = new ArrayList<>();
        for (int i = 0; i < characters.size(); i++) {
            boolean b = false;
            for (int j = 0; j < houses.size(); j++) {
                if (houses.get(j).getHouseName().equalsIgnoreCase(characters.get(i).getHouseName())) {
                    b = true;
                }
            }
            if (!b) {
                if (characters.get(i).getHouseId() != null && !characters.get(i).getHouseId().isEmpty()) {
                    houses.add(new GoTHouse(characters.get(i)));
                    b = false;
                }
            }
        }
        return houses;
    }

    @Override
    protected void setupActivityComponent() {
        GoTApplication.get(getActivity()).getComponent()
                .plus(new GoTHousesListFragmentModule(this))
                .inject(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(), "Implementar Charecters By house", Toast.LENGTH_SHORT).show();
    }
}
