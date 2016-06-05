package es.npatarino.android.gotchallenge.repository.data;

import java.util.List;

import es.npatarino.android.gotchallenge.model.entity.GoTCharacterEntity;
import rx.Observable;

public interface GoTDataSource {
    Observable<List<GoTCharacterEntity>> getCharacters();
}
