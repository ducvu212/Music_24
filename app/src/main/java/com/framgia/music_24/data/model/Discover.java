package com.framgia.music_24.data.model;

import java.util.List;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class Discover {

    private String mGender;
    private List<Music> mMusics;

    private Discover(DiscoverBuilder discoverBuilder) {
        mGender = discoverBuilder.mGender;
        mMusics = discoverBuilder.mMusics;
    }

    public String getGender() {
        return mGender;
    }

    public List<Music> getMusics() {
        return mMusics;
    }

    public static final class DiscoverBuilder {
        private String mGender;
        private List<Music> mMusics;

        public DiscoverBuilder() {
        }

        public DiscoverBuilder Gender(String gender) {
            mGender = gender;
            return this;
        }

        public DiscoverBuilder Musics(List<Music> musics) {
            mMusics = musics;
            return this;
        }

        public Discover build() {
            return new Discover(this);
        }
    }
}
