package com.framgia.music_24.data.source;

import com.framgia.music_24.data.model.Collection;
import java.util.ArrayList;

/**
 * Created by CuD HniM on 18/08/24.
 */
public interface GenreTracksDataSource {
    void getGenderTracks(String genreTracks, CallBack<ArrayList<Collection>> callBack);
}
