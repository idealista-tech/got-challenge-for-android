package es.npatarino.android.gotchallenge.repository.service;

import java.util.List;

import es.npatarino.android.gotchallenge.model.entity.GoTCharacterEntity;
import retrofit2.http.GET;
import rx.Observable;

public interface GoTService {

    @GET("characters")
    Observable<List<GoTCharacterEntity>> listCharacters();

}