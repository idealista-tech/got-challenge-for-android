package es.npatarino.android.gotchallenge.di.modules;

import com.tonilopezmr.interactorexecutor.Executor;
import com.tonilopezmr.interactorexecutor.MainThread;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import es.npatarino.android.gotchallenge.di.Activity;
import es.npatarino.android.gotchallenge.domain.GoTCharacter;
import es.npatarino.android.gotchallenge.domain.interactor.GetCharactersByHouseUseCase;
import es.npatarino.android.gotchallenge.domain.interactor.common.GetListUseCase;
import es.npatarino.android.gotchallenge.domain.interactor.common.GetListUseCaseImp;
import es.npatarino.android.gotchallenge.domain.repository.GotCharacterRepository;
import es.npatarino.android.gotchallenge.presenter.CharacterListPresenter;
import es.npatarino.android.gotchallenge.presenter.CharacterListPresenterImp;
import es.npatarino.android.gotchallenge.presenter.GotCharacterListByHousePresenter;
import es.npatarino.android.gotchallenge.presenter.GotCharacterListByHousePresenterImp;

/**
 * @author Antonio López.
 */

@Module
public class CharactersModule {

    @Provides
    @Activity
    public GetCharactersByHouseUseCase provideCharactersByHouseUseCase(Executor executor, MainThread mainThread, GotCharacterRepository repository){
        return new GetCharactersByHouseUseCase(executor, mainThread, repository);
    }

    @Provides
    @Activity
    @Named("character")
    public GetListUseCase<GoTCharacter> provideGotCharacterListUseCase(Executor executor, MainThread mainThread, GotCharacterRepository repository){
        return new GetListUseCaseImp<>(executor, mainThread, repository);
    }

    @Provides
    @Activity
    public CharacterListPresenter provideGotCharacterListPresenter(@Named("character")GetListUseCase<GoTCharacter> characterGetListUseCase){
        return new CharacterListPresenterImp(characterGetListUseCase);
    }

    @Provides
    @Activity
    public GotCharacterListByHousePresenter provideGotCharacterListByHousePresenter(GetCharactersByHouseUseCase useCase){
        return new GotCharacterListByHousePresenterImp(useCase);
    }
}
