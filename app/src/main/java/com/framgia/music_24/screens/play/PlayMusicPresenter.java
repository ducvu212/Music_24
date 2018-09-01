package com.framgia.music_24.screens.play;

import com.framgia.music_24.data.repository.TracksRepository;

/**
 * Created by CuD HniM on 18/08/29.
 */
public class PlayMusicPresenter implements PlayMusicContract.Presenter {

    private PlayMusicContract.View mView;
    private TracksRepository mRepository;

    public PlayMusicPresenter(TracksRepository repository) {
        mRepository = repository;
    }

    @Override
    public void setView(PlayMusicContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
