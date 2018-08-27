package com.framgia.music_24.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class Discover implements Parcelable {

    private String mGender;
    private List<Track> mTracks;

    public String getGender() {
        return mGender;
    }

    public Discover(Builder discoverBuilder) {
        mGender = discoverBuilder.mGender;
        mTracks = discoverBuilder.mTracks;
    }

    public static final Parcelable.Creator<Discover> CREATOR = new Parcelable.Creator<Discover>() {
        @Override
        public Discover createFromParcel(Parcel source) {
            return new Discover(source);
        }

        @Override
        public Discover[] newArray(int size) {
            return new Discover[size];
        }
    };

    protected Discover(Parcel in) {
        mGender = in.readString();
        mTracks = in.createTypedArrayList(Track.CREATOR);
    }

    public List<Track> getTracks() {
        return mTracks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mGender);
        dest.writeTypedList(mTracks);
    }

    public static final class Builder {

        private String mGender;
        private List<Track> mTracks;

        public Builder() {
        }

        public Builder mGender(String gender) {
            mGender = gender;
            return this;
        }

        public Builder mTracks(List<Track> tracks) {
            mTracks = tracks;
            return this;
        }

        public Discover build() {
            return new Discover(this);
        }
    }
}
