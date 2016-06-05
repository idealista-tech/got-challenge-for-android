package es.npatarino.android.gotchallenge.injection.module;

import android.content.Context;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.npatarino.android.gotchallenge.Constants;
import es.npatarino.android.gotchallenge.R;
import es.npatarino.android.gotchallenge.model.mapper.GoTEntityMapper;
import es.npatarino.android.gotchallenge.repository.GoTBestRepository;
import es.npatarino.android.gotchallenge.repository.GoTRepository;
import es.npatarino.android.gotchallenge.repository.data.LocalDataSource;
import es.npatarino.android.gotchallenge.repository.data.RetrofitDataSource;
import es.npatarino.android.gotchallenge.repository.service.GoTService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class GoTRepositoryModule {

    @Provides @Singleton
    GoTEntityMapper provideGoTEntityMapper(){
        return new GoTEntityMapper();
    }

    @Provides @Named(Constants.Injection.Named.GOT_API_KEY)
    public String provideGoTApiKey(Context context){
        return context.getString(R.string.url_service);
    }

    @Provides @Singleton
    public Retrofit provideRestAdapter(@Named(Constants.Injection.Named.GOT_API_KEY) String apiKey) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(apiKey)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }

    @Provides @Singleton
    public GoTService provideGoTService(Retrofit restAdapter) {
        return restAdapter.create(GoTService.class);
    }

    @Provides @Singleton
    public LocalDataSource provideLocalDataSource(){
        return new LocalDataSource();
    }

    @Provides @Singleton
    GoTRepository provideGoTRepository(GoTEntityMapper goTEntityMapper,
                                       RetrofitDataSource retrofitDataSource,
                                       LocalDataSource localDataSource){
        return new GoTBestRepository(goTEntityMapper, retrofitDataSource, localDataSource);
    }
}
