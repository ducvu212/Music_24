package com.framgia.music_24.data.model;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class Music {

    private String mName;
    private String mSinger;
    private String mUrl;

    public Music(String name, String singer, String url) {
        mName = name;
        mSinger = singer;
        mUrl = url;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getSinger() {
        return mSinger;
    }

    public void setSinger(String singer) {
        mSinger = singer;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
