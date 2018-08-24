package com.framgia.music_24.data.source.remote;

import com.framgia.music_24.data.model.Collection;
import com.framgia.music_24.data.source.CallBack;
import com.framgia.music_24.data.source.GenreTracksDataSource;
import java.util.ArrayList;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class GenreTracksRemoteDataSource implements GenreTracksDataSource {

    private static GenreTracksRemoteDataSource mInstance;

    public static synchronized GenreTracksRemoteDataSource getInstance() {
        if (mInstance == null) {
            synchronized (GenreTracksRemoteDataSource.class) {
                if (mInstance == null) {
                    mInstance = new GenreTracksRemoteDataSource();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void getGenderTracks(String genreTracks, CallBack<ArrayList<Collection>> callBack) {

    }
}
