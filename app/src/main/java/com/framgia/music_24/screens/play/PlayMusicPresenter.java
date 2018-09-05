package com.framgia.music_24.screens.play;

import com.framgia.music_24.data.model.Setting;
import com.framgia.music_24.data.repository.PlaySettingRepository;

/**
 * Created by CuD HniM on 18/08/29.
 */
public class PlayMusicPresenter implements PlayMusicContract.Presenter {

    private PlayMusicContract.View mView;
    private PlaySettingRepository mRepository;

    public PlayMusicPresenter(PlaySettingRepository repository) {
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

    @Override
    public void saveSetting(Setting setting) {
        mRepository.saveSetting(setting);
    }

    @Override
    public Setting getSetting() {
        return mRepository.getSetting();
    }
}
