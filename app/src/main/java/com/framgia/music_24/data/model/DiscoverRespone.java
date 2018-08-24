package com.framgia.music_24.data.model;

import java.util.Collection;
import java.util.List;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class DiscoverRespone {

    private String mGenre;
    private String mKind;
    private String mLastUpdated;
    private List<Collection> mCollections;

    private DiscoverRespone(DiscoverResponeBuilder discoverResponeBuilder) {
        mGenre = discoverResponeBuilder.mGenre;
        mKind = discoverResponeBuilder.mKind;
        mLastUpdated = discoverResponeBuilder.mLastUpdated;
        mCollections = discoverResponeBuilder.mCollections;
    }

    public String getGenre() {
        return mGenre;
    }

    public String getKind() {
        return mKind;
    }

    public String getLastUpdated() {
        return mLastUpdated;
    }

    public List<Collection> getCollections() {
        return mCollections;
    }

    public static final class DiscoverResponeBuilder {
        private String mGenre;
        private String mKind;
        private String mLastUpdated;
        private List<Collection> mCollections;

        public DiscoverResponeBuilder() {
        }

        public DiscoverResponeBuilder Genre(String genre) {
            mGenre = genre;
            return this;
        }

        public DiscoverResponeBuilder Kind(String kind) {
            mKind = kind;
            return this;
        }

        public DiscoverResponeBuilder LastUpdated(String lastUpdated) {
            mLastUpdated = lastUpdated;
            return this;
        }

        public DiscoverResponeBuilder Collections(List<Collection> collections) {
            mCollections = collections;
            return this;
        }

        public DiscoverRespone build() {
            return new DiscoverRespone(this);
        }
    }
}
