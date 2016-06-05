package es.npatarino.android.gotchallenge.model.mapper;


import java.util.ArrayList;
import java.util.List;

import es.npatarino.android.gotchallenge.model.GoTCharacter;
import es.npatarino.android.gotchallenge.model.entity.GoTCharacterEntity;

public class GoTEntityMapper {

    public List<GoTCharacter> transform(List<GoTCharacterEntity> goTCharacterEntities){
        List<GoTCharacter> goTCharacters = new ArrayList<>();

        if(goTCharacterEntities == null)
            return goTCharacters;

        for (GoTCharacterEntity goTCharacterEntity: goTCharacterEntities) {
            goTCharacters.add(transform(goTCharacterEntity));
        }

        return goTCharacters;
    }

    private GoTCharacter transform(GoTCharacterEntity goTCharacterEntity) {
        GoTCharacter goTCharacter = new GoTCharacter();
        goTCharacter.setName(goTCharacterEntity.getName());
        goTCharacter.setImageUrl(goTCharacterEntity.getImageUrl());
        goTCharacter.setDescription(goTCharacterEntity.getDescription());
        goTCharacter.setHouseId(goTCharacterEntity.getHouseId());
        goTCharacter.setHouseName(goTCharacterEntity.getHouseName());
        goTCharacter.setHouseImageUrl(goTCharacterEntity.getHouseImageUrl());
        return goTCharacter;
    }
}
