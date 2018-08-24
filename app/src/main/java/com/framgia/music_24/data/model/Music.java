package com.framgia.music_24.data.model;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class Music {

    private String mName;
    private String mSinger;
    private String mUrl;

    private Music(MussicBuilder mussicBuilder) {
        mName = mussicBuilder.mName;
        mSinger = mussicBuilder.mSinger;
        mUrl = mussicBuilder.mUrl;
    }

    public String getName() {
        return mName;
    }

    public String getSinger() {
        return mSinger;
    }

    public String getUrl() {
        return mUrl;
    }

    public static final class MussicBuilder {
        private String mName;
        private String mSinger;
        private String mUrl;

        public MussicBuilder() {
        }

        public MussicBuilder Name(String name) {
            mName = name;
            return this;
        }

        public MussicBuilder Singer(String singer) {
            mSinger = singer;
            return this;
        }

        public MussicBuilder Url(String url) {
            mUrl = url;
            return this;
        }

        public Music build() {
            return new Music(this);
        }
    }
}
