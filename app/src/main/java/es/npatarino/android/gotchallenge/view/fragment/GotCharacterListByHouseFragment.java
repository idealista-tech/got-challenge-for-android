package es.npatarino.android.gotchallenge.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import es.npatarino.android.gotchallenge.GotChallengeApplication;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.di.components.DaggerCharactersComponent;
import es.npatarino.android.gotchallenge.di.modules.ActivityModule;
import es.npatarino.android.gotchallenge.di.modules.CharactersModule;
import es.npatarino.android.gotchallenge.domain.GoTCharacter;
import es.npatarino.android.gotchallenge.domain.GoTHouse;
import es.npatarino.android.gotchallenge.presenter.GotCharacterListByHousePresenter;
import es.npatarino.android.gotchallenge.view.DetailView;
import es.npatarino.android.gotchallenge.view.adapters.GoTAdapter;

/**
 * @author Antonio López.
 */

public class GotCharacterListByHouseFragment extends Fragment implements DetailView<List<GoTCharacter>> {

    private static final String TAG = "GoTListFragment";
    private RecyclerView rv;
    private ContentLoadingProgressBar pb;
    private GoTAdapter adp;
    private GoTHouse house;

    @Inject
    GotCharacterListByHousePresenter gotCharacterListByHousePresenter;


    public GotCharacterListByHouseFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        initDagger();

        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        pb = (ContentLoadingProgressBar) rootView.findViewById(R.id.content_loading_progress_bar);
        initUi();

        //dagger everywhere
        //Executor executor = new ThreadExecutor();
        //MainThread mainThread = new MainThreadImp();
        //String endPoint = "http://ec2-52-18-202-124.eu-west-1.compute.amazonaws.com:3000";
        //GotCharacterRepositoryImp repository = new GotCharacterRepositoryImp(new OkHttpClient(), endPoint);
        //GetCharactersByHouseUseCase charactersByHouse = new GetCharactersByHouseUseCase(executor, mainThread, repository);
        //gotCharacterListByHousePresenter = new GotCharacterListByHousePresenterImp(charactersByHouse);
        gotCharacterListByHousePresenter.setView(this);
        gotCharacterListByHousePresenter.init(house);
        return rootView;
    }

    private void initDagger() {
        GotChallengeApplication app = (GotChallengeApplication) getActivity().getApplication();
        DaggerCharactersComponent.builder()
                .appComponent(app.getAppComponent())
                .activityModule(new ActivityModule(getActivity()))
                .charactersModule(new CharactersModule())
                .build().inject(this);
    }

    public void setHouse(GoTHouse house) {
        this.house = house;
    }


    @Override
    public void initUi() {
        adp = new GoTAdapter(getActivity());
        rv.setAdapter(adp);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
    }

    @Override
    public void error() {
        Toast.makeText(getActivity().getApplicationContext(), "ERRRRRORRR ONEEE", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void show(List<GoTCharacter> list) {
        adp.addAll(list);
        adp.notifyDataSetChanged();
        pb.hide();
    }
}