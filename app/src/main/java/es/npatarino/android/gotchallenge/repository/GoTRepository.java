package es.npatarino.android.gotchallenge.repository;

import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import rx.Observable;

public interface GoTRepository {

    Observable<List<GoTCharacter>> getCharacters();
}
