package com.framgia.music_24.screens.play;

/**
 * Created by CuD HniM on 18/08/29.
 */
public class PlayScreenPresenter implements PlayScreenContract.Presenter {

    private PlayScreenContract.View  mView;

    @Override
    public void setView(PlayScreenContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
