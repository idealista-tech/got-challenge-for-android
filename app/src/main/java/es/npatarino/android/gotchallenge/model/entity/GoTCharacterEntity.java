package es.npatarino.android.gotchallenge.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class GoTCharacterEntity implements Parcelable {

    @SerializedName("name")
    String name;
    @SerializedName("imageUrl")
    String imageUrl;
    @SerializedName("description")
    String description;
    @SerializedName("houseImageUrl")
    String houseImageUrl;
    @SerializedName("houseName")
    String houseName;
    @SerializedName("houseId")
    String houseId;

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

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageUrl);
        dest.writeString(description);
        dest.writeString(houseImageUrl);
        dest.writeString(houseName);
        dest.writeString(houseId);
    }

    protected GoTCharacterEntity(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        description = in.readString();
        houseImageUrl = in.readString();
        houseName = in.readString();
        houseId = in.readString();
    }

    public static final Creator<GoTCharacterEntity> CREATOR = new Creator<GoTCharacterEntity>() {
        @Override
        public GoTCharacterEntity createFromParcel(Parcel in) {
            return new GoTCharacterEntity(in);
        }

        @Override
        public GoTCharacterEntity[] newArray(int size) {
            return new GoTCharacterEntity[size];
        }
    };
}
