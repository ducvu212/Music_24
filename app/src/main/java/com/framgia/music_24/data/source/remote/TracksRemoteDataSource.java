package com.framgia.music_24.data.source.remote;

import com.framgia.music_24.BuildConfig;
import com.framgia.music_24.data.source.CallBack;
import com.framgia.music_24.data.source.TracksDataSource;
import com.framgia.music_24.data.source.remote.asynctask.TracksAsyncTask;
import com.framgia.music_24.data.source.remote.download.DownloadReceiver;

import static com.framgia.music_24.BuildConfig.API_KEY;
import static com.framgia.music_24.utils.Constants.BASE_URL;
import static com.framgia.music_24.utils.Constants.CLIENT_ID;
import static com.framgia.music_24.utils.Constants.QUERY_GENRE;
import static com.framgia.music_24.utils.Constants.QUERY_KIND;
import static com.framgia.music_24.utils.Constants.QUERY_LIMIT;
import static com.framgia.music_24.utils.Constants.QUERY_TYPE;
import static com.framgia.music_24.utils.Constants.QUERY_TYPE_KEY;
import static com.framgia.music_24.utils.Constants.STREAM;
import static com.framgia.music_24.utils.Constants.STREAM_CLIENT_ID;
import static com.framgia.music_24.utils.Constants.STREAM_TRACK_ID;
import static com.framgia.music_24.utils.Constants.STREAM_URL;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class TracksRemoteDataSource implements TracksDataSource.TrackRemoteDataSource {

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

    public static String buildStreamUrl(int id) {
        StringBuilder builder = new StringBuilder();
        String url = builder.append(STREAM_URL)
                .append(STREAM_TRACK_ID)
                .append(STREAM)
                .append(STREAM_CLIENT_ID)
                .append(API_KEY)
                .toString();
        return url.replace(STREAM_TRACK_ID, String.valueOf(id));
    }

    private void getDataTrackFromUrl(String genre, String genreTitle, String limit,
            CallBack callBack) {
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
        new TracksAsyncTask(genreTitle, callBack).execute(url);
    }

    @Override
    public void getTrack(String genre, String genreTitle, CallBack callBack) {
        getDataTrackFromUrl(genre, genreTitle, "", callBack);
    }

    @Override
    public void getTrack(String genre, int limit, CallBack callBack) {
        getDataTrackFromUrl(genre, "", String.valueOf(limit), callBack);
    }

    @Override
    public void downloadTrack(String title, OnDownloadListener onDownloadListener) {
        DownloadReceiver receiver = new DownloadReceiver(new android.os.Handler());
        receiver.setData(title, onDownloadListener);
    }
}
