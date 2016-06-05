package es.npatarino.android.gotchallenge.repository.data;

import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.model.entity.GoTCharacterEntity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LocalDataSource implements GoTDataSource {

    @Override
    public Observable<List<GoTCharacterEntity>> getCharacters() {
            List<GoTCharacterEntity> characterEntities = new ArrayList<>();

            return Observable.from(characterEntities).toList()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io());
    }
}
