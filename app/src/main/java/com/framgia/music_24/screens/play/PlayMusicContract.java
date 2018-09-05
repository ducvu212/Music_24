package com.framgia.music_24.screens.play;

import com.framgia.music_24.data.model.Setting;
import com.framgia.music_24.screens.base.BasePresenter;

/**
 * Created by CuD HniM on 18/08/29.
 */
public interface PlayMusicContract {

    /**
     * View
     */

    interface View {

    }

    /**
     * Presenter
     */

    interface Presenter extends BasePresenter<View> {
        void saveSetting(Setting setting);

        Setting getSetting();
    }
}
