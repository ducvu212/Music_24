package com.framgia.music_24.screens.play;

import com.framgia.music_24.data.model.Setting;
import com.framgia.music_24.data.model.Track;
import com.framgia.music_24.screens.base.BasePresenter;

/**
 * Created by CuD HniM on 18/08/29.
 */
public interface PlayMusicContract {

    /**
     * View
     */

    interface View {
        void initData(Track track);

        void downloadSuccess(String url);

        void downloadError(String e);
    }

    /**
     * Presenter
     */

    interface Presenter extends BasePresenter<View> {
        void saveSetting(Setting setting);

        Setting getSetting();

        void editFavorite(Track track, int fav);

        void editDownload(Track track, int download, String uri);

        void addTracks(Track track);

        boolean isExistRow(Track track);

        void downloadTrack(String title);

        void findTrackById(String id);
    }
}
