package com.framgia.music_24.data.source;

import com.framgia.music_24.data.model.Discover;
import com.framgia.music_24.data.model.Track;
import java.util.List;

/**
 * Created by CuD HniM on 18/08/24.
 */
public interface TracksDataSource {
    void getTrack(String genre, String genreTitle, CallBack<List<Discover>> callBack);

    void getTrack(String genre, int limit, CallBack<List<Track>> callBack);
}
