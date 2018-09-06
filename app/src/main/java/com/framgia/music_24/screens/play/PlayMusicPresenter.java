package com.framgia.music_24.screens.play;

import com.framgia.music_24.data.model.Setting;
import com.framgia.music_24.data.model.Track;
import com.framgia.music_24.data.repository.PlaySettingRepository;
import com.framgia.music_24.data.repository.TracksRepository;

/**
 * Created by CuD HniM on 18/08/29.
 */
public class PlayMusicPresenter implements PlayMusicContract.Presenter {

    private PlayMusicContract.View mView;
    private PlaySettingRepository mRepository;
    private TracksRepository mTracksRepository;

    public PlayMusicPresenter(PlaySettingRepository repository, TracksRepository tracksRepository) {
        mRepository = repository;
        mTracksRepository = tracksRepository;
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

    @Override
    public void editFavorite(Track track, int fav) {
        mTracksRepository.updateFavorite(track, fav);
    }

    @Override
    public void addTracks(Track track) {
        mTracksRepository.addTrack(track);
    }

    @Override
    public boolean isExistRow(Track track) {
        return mTracksRepository.isExistRow(track);
    }
}
