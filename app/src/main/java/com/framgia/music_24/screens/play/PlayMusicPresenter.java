package com.framgia.music_24.screens.play;

/**
 * Created by CuD HniM on 18/08/29.
 */
public class PlayMusicPresenter implements PlayMusicContract.Presenter {

    private PlayMusicContract.View mView;

    public PlayMusicPresenter() {

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
