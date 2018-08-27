package com.framgia.music_24.data.source.remote;

import com.framgia.music_24.BuildConfig;
import com.framgia.music_24.data.source.CallBack;
import com.framgia.music_24.data.source.TracksDataSource;
import java.util.List;

import static com.framgia.music_24.utils.Constants.BASE_URL;
import static com.framgia.music_24.utils.Constants.CLIENT_ID;
import static com.framgia.music_24.utils.Constants.QUERY_GENRE;
import static com.framgia.music_24.utils.Constants.QUERY_KIND;
import static com.framgia.music_24.utils.Constants.QUERY_LIMIT;
import static com.framgia.music_24.utils.Constants.QUERY_TYPE;
import static com.framgia.music_24.utils.Constants.QUERY_TYPE_KEY;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class TracksRemoteDataSource implements TracksDataSource {

    private static TracksRemoteDataSource sInstance;

    public static synchronized TracksRemoteDataSource getInstance() {
        if (sInstance == null) {
            synchronized (TracksRemoteDataSource.class) {
                if (sInstance == null) {
                    sInstance = new TracksRemoteDataSource();
                }
            }
        }
        return sInstance;
    }

    private void getDataTrackFromUrl(String genre, String genreTitle, String limit,
            List<Object> datas, CallBack callBack) {
        StringBuilder builder = new StringBuilder();
        builder.append(BASE_URL)
                .append(QUERY_KIND)
                .append(QUERY_GENRE)
                .append(QUERY_TYPE)
                .append(CLIENT_ID)
                .append(BuildConfig.API_KEY)
                .append(QUERY_LIMIT)
                .append(limit);
        String url = builder.toString().replace(QUERY_TYPE_KEY, genre);
        new TracksAsyncTask(genreTitle, datas, callBack).execute(url);
    }

    @Override
    public void getTrack(String genre, String genreTitle, List datas, CallBack callBack) {
        getDataTrackFromUrl(genre, genreTitle, "", datas, callBack);
    }

    @Override
    public void getTrack(String genre, int limit, List datas, CallBack callBack) {
        getDataTrackFromUrl(genre, "", String.valueOf(limit), datas, callBack);
    }
}
