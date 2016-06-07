package es.npatarino.android.gotchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

public class GoTHouse implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(houseImageUrl);
        dest.writeString(houseName);
        dest.writeString(houseId);
    }

    protected GoTHouse(Parcel in) {
        houseImageUrl = in.readString();
        houseName = in.readString();
        houseId = in.readString();
    }

    public static final Creator<GoTHouse> CREATOR = new Creator<GoTHouse>() {
        @Override
        public GoTHouse createFromParcel(Parcel in) {
            return new GoTHouse(in);
        }

        @Override
        public GoTHouse[] newArray(int size) {
            return new GoTHouse[size];
        }
    };
}
