package es.npatarino.android.gotchallenge.repository;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.mapper.GoTEntityMapper;
import es.npatarino.android.gotchallenge.repository.data.GoTDataSource;
import es.npatarino.android.gotchallenge.repository.data.LocalDataSource;
import es.npatarino.android.gotchallenge.repository.data.RetrofitDataSource;
import rx.Observable;

public class GoTBestRepository implements GoTRepository {

    GoTEntityMapper goTEntityMapper;

    RetrofitDataSource retrofitDataSource;
    LocalDataSource localDataSource;

    public GoTBestRepository(GoTEntityMapper goTEntityMapper,
                             RetrofitDataSource retrofitDataSource,
                             LocalDataSource localDataSource) {
        this.goTEntityMapper = goTEntityMapper;
        this.retrofitDataSource = retrofitDataSource;
        this.localDataSource = localDataSource;
    }

    @Override
    public Observable<List<GoTCharacter>> getCharacters() {

        return getBestDataSource().getCharacters()
                .map(goTEntityMapper::transform);
    }

    private GoTDataSource getBestDataSource() {
        return retrofitDataSource;
    }
}
