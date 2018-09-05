package com.framgia.music_24.data.repository;

import com.framgia.music_24.data.model.Setting;
import com.framgia.music_24.data.source.local.PlaySettingLocalDataSource;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by CuD HniM on 18/09/02.
 */
public class PlaySettingRepository {

    private static PlaySettingRepository sInstance;
    private PlaySettingLocalDataSource mDataSource;

    private PlaySettingRepository(PlaySettingLocalDataSource dataSource) {
        mDataSource = dataSource;
    }

    public static PlaySettingRepository getInstance(PlaySettingLocalDataSource dataSource) {
        if (sInstance == null) {
            synchronized (PlaySettingRepository.class) {
                if (sInstance == null) {
                    sInstance = new PlaySettingRepository(checkNotNull(dataSource));
                }
            }
        }
        return sInstance;
    }

    public void saveSetting(Setting setting) {
        mDataSource.saveSetting(setting);
    }

    public Setting getSetting() {
        return mDataSource.getSetting();
    }
}
