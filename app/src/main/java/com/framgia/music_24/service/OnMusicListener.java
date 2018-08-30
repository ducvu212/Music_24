package com.framgia.music_24.service;

import com.framgia.music_24.data.model.Track;
import java.util.List;

/**
 * Created by CuD HniM on 18/08/30.
 */
public interface OnMusicListener {

    void setDataSource(String url);

    void play();

    void next(List<Track> tracks, int position);

    void previous(List<Track> tracks, int position);
}
