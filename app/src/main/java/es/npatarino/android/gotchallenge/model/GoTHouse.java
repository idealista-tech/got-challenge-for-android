package es.npatarino.android.gotchallenge.model;

public class GoTHouse {

    String houseImageUrl;
    String houseName;
    String houseId;

    public GoTHouse(GoTCharacter character) {
        this.houseImageUrl = character.getHouseImageUrl();
        this.houseName = character.getHouseName();
        this.houseId = character.getHouseId();
    }

    public String getHouseImageUrl() {
        return houseImageUrl;
    }

    public void setHouseImageUrl(final String houseImageUrl) {
        this.houseImageUrl = houseImageUrl;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(final String houseName) {
        this.houseName = houseName;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(final String houseId) {
        this.houseId = houseId;
    }

}
