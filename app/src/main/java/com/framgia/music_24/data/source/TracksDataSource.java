package com.framgia.music_24.data.source;

import com.framgia.music_24.data.model.Discover;
import com.framgia.music_24.data.model.Track;
import java.util.List;

/**
 * Created by CuD HniM on 18/08/24.
 */

public interface TracksDataSource {

    interface TrackRemoteDataSource {
        void getTrack(String genre, String genreTitle, CallBack<List<Discover>> callBack);

        void getTrack(String genre, int limit, CallBack<List<Track>> callBack);

        interface OnDownloadListener {
            void OnSuccess(String url);

            void OnError(String e);
        }

        void downloadTrack(String title, OnDownloadListener onDownloadListener);
    }

    interface TrackLocalDataSource {
        void addTrack(Track track);

        List<Track> getAllTracks();

        void updateFavorite(Track track, int fav);

        void updateDownload(Track track, int download, String uri);

        boolean isExistRow(Track track);

        Track findTrackById(String id);
    }
}
