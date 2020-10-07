package com.yohanome.starwarsapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterModel implements Parcelable {
    private String name;

    private String height;

    private String mass;

    @SerializedName("hair_color")
    private String hairColor;

    @SerializedName("skin_color")
    private String skinColor;

    @SerializedName("eye_color")
    private String eyeColor;

    @SerializedName("birth_year")
    private String birthYear;

    private String gender;

    private List<String> films;

    protected CharacterModel(Parcel in) {
        name = in.readString();
        height = in.readString();
        mass = in.readString();
        hairColor = in.readString();
        skinColor = in.readString();
        eyeColor = in.readString();
        birthYear = in.readString();
        gender = in.readString();
        films = in.createStringArrayList();
    }

    public static final Creator<CharacterModel> CREATOR = new Creator<CharacterModel>() {
        @Override
        public CharacterModel createFromParcel(Parcel in) {
            return new CharacterModel(in);
        }

        @Override
        public CharacterModel[] newArray(int size) {
            return new CharacterModel[size];
        }
    };

    public String format(){
        return "Name: " + this.name + "\n" +
                "Height: " + this.height + "\n" +
                "Mass: " + this.mass + "\n" +
                "hairColor: " + this.hairColor + "\n" +
                "skinColor: " + this.skinColor + "\n" +
                "eyeColor: " + this.eyeColor + "\n" +
                "birthYear: " + this.birthYear + "\n" +
                "gender: " + this.gender + "\n";
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getGender() {
        return gender;
    }

    public List<String> getFilms() {
        return films;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(height);
        dest.writeString(mass);
        dest.writeString(hairColor);
        dest.writeString(skinColor);
        dest.writeString(eyeColor);
        dest.writeString(birthYear);
        dest.writeString(gender);
        dest.writeStringList(films);
    }
}
