package es.npatarino.android.gotchallenge.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.npatarino.android.gotchallenge.GoTApplication;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.injection.module.GoTListFragmentModule;
import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.repository.GoTRepository;
import es.npatarino.android.gotchallenge.view.activity.DetailActivity;
import es.npatarino.android.gotchallenge.view.adapter.GoTAdapter;
import es.npatarino.android.gotchallenge.view.listener.ItemClickListener;

public class GoTListFragment extends FragmentBase implements ItemClickListener {

    private static final String TAG = "GoTListFragment";

    @Inject GoTAdapter adapter;
    @Inject LayoutManager layoutManager;
    @Inject GoTRepository goTRepository;

    @BindView(R.id.progressBar) ContentLoadingProgressBar progressBar;
    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    public static Fragment newInstance() {
        return new GoTListFragment();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, rootView);

        configRecyclerView();

        displayCharacters(goTRepository.getCharacters());

        return rootView;
    }

    private void displayCharacters(List<GoTCharacter> characters) {
        adapter.addAll(characters);
        adapter.notifyDataSetChanged();
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
}