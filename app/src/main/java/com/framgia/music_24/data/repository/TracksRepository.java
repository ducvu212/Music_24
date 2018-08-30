package com.framgia.music_24.data.repository;

import com.framgia.music_24.data.source.CallBack;
import com.framgia.music_24.data.source.TracksDataSource;
import com.framgia.music_24.data.source.remote.TracksRemoteDataSource;
import java.util.List;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by CuD HniM on 18/08/25.
 */
public class TracksRepository implements TracksDataSource {

    private static TracksRepository sInstance;
    private TracksRemoteDataSource mRemoteDataSource;

    private TracksRepository(TracksRemoteDataSource tracksRemoteDataSource) {
        mRemoteDataSource = checkNotNull(tracksRemoteDataSource);
    }

    public static synchronized TracksRepository getInstance(
            TracksRemoteDataSource tracksRemoteDataSource) {
        if (sInstance == null) {
            synchronized (TracksRemoteDataSource.class) {
                if (sInstance == null) {
                    sInstance = new TracksRepository(checkNotNull(tracksRemoteDataSource));
                }
            }
        }
        return sInstance;
    }

    @Override
    public void getTrack(String genre, String genreTitle, CallBack callBack) {
        mRemoteDataSource.getTrack(genre, genreTitle, callBack);
    }

    @Override
    public void getTrack(String genre, int limit, CallBack callBack) {
        mRemoteDataSource.getTrack(genre, limit, callBack);
    }
}
