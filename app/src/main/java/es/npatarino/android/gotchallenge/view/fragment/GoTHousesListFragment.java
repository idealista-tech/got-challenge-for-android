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
import es.npatarino.android.gotchallenge.di.components.DaggerHousesComponent;
import es.npatarino.android.gotchallenge.di.modules.ActivityModule;
import es.npatarino.android.gotchallenge.di.modules.HousesModule;
import es.npatarino.android.gotchallenge.domain.GoTHouse;
import es.npatarino.android.gotchallenge.presenter.HouseListPresenter;
import es.npatarino.android.gotchallenge.view.ViewList;
import es.npatarino.android.gotchallenge.view.adapters.GoTHouseAdapter;

/**
 * @author Antonio López.
 */
public class GoTHousesListFragment extends Fragment implements ViewList<GoTHouse> {

    private static final String TAG = "GoTHousesListFragment";
    private RecyclerView rv;
    private ContentLoadingProgressBar pb;
    private GoTHouseAdapter adp;

    @Inject
    HouseListPresenter gotHouseListPresenter;

    public GoTHousesListFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        initDagger();
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        pb = (ContentLoadingProgressBar) rootView.findViewById(R.id.content_loading_progress_bar);
        initUi();

        //dagger everywhere
//        Executor executor = new ThreadExecutor();
//        MainThread mainThread = new MainThreadImp();
//        String endPoint = "http://ec2-52-18-202-124.eu-west-1.compute.amazonaws.com:3000";
//        GotCharacterRepositoryImp characterRepository = new GotCharacterRepositoryImp(new OkHttpClient(), endPoint);
//        GotHouseRepositoryImp repository = new GotHouseRepositoryImp(characterRepository);
//        GetListUseCase<GoTHouse> goTHouseGetListUseCase = new GetListUseCaseImp<>(executor, mainThread, repository);
//        gotCharacterListPresenter = new GotListPresenterImp<>(goTHouseGetListUseCase);
        gotHouseListPresenter.setView(this);
        gotHouseListPresenter.init();
        return rootView;
    }

    private void initDagger() {
        GotChallengeApplication app = (GotChallengeApplication) getActivity().getApplication();
        DaggerHousesComponent.builder()
                .appComponent(app.getAppComponent())
                .activityModule(new ActivityModule(getActivity()))
                .housesModule(new HousesModule())
                .build().inject(this);
    }

    @Override
    public void showList(List<GoTHouse> list) {
        adp.addAll(list);
        adp.notifyDataSetChanged();
        pb.hide();
    }

    @Override
    public void initUi() {
        adp = new GoTHouseAdapter(getActivity());
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        rv.setAdapter(adp);
    }

    @Override
    public void error() {
        Toast.makeText(getActivity().getApplicationContext(), "ERRRRRORRR ONEEE", Toast.LENGTH_SHORT).show();
    }
}