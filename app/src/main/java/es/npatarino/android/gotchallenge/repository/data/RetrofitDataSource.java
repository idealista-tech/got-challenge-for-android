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

    public static final String URL = "http://52.18.228.107:3000/";

    GoTService goTService;

    public RetrofitDataSource(GoTService goTService) {
        this.goTService = goTService;
    }

    @Override
    public Observable<List<GoTCharacterEntity>> getCharacters() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            GoTService service = retrofit.create(GoTService.class);

            return service.listCharacters()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io());
    }
}
