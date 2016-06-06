package es.npatarino.android.gotchallenge.repository.data;

import java.util.List;

import es.npatarino.android.gotchallenge.model.entity.GoTCharacterEntity;
import es.npatarino.android.gotchallenge.repository.data.GoTDataSource;
import es.npatarino.android.gotchallenge.repository.service.GoTService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitDataSource implements GoTDataSource {

    private GoTService goTService;

    public RetrofitDataSource(GoTService goTService) {
        this.goTService = goTService;
    }

    @Override
    public Observable<List<GoTCharacterEntity>> getCharacters() {

            return goTService.listCharacters();

    }
}
