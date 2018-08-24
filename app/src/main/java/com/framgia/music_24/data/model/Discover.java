package com.framgia.music_24.data.model;

import java.util.List;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class Discover {

    private String mGender;
    private List<Music> mMusics;

    public Discover(String gender, List<Music> musics) {
        mGender = gender;
        mMusics = musics;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public List<Music> getMusics() {
        return mMusics;
    }

    public void setMusics(List<Music> musics) {
        mMusics = musics;
    }
}
