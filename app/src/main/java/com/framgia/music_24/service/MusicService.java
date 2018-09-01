package com.framgia.music_24.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.framgia.music_24.data.model.Track;
import com.framgia.music_24.screens.play.MusicPlayer;
import java.util.List;

public class MusicService extends Service implements OnMusicListener {

    private final IBinder mBinder = new LocalBinder();
    private MusicPlayer mMusicPlayer;

    public MusicService() {
    }

    public void registerService(MusicPlayer musicPlayer) {
        mMusicPlayer = musicPlayer;
    }

    public OnMusicListener getListener() {
        return this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void setDataSource(String url) {
        mMusicPlayer.setDataSource(url);
    }

    @Override
    public void play() {
        mMusicPlayer.play();
    }

    @Override
    public void next(List<Track> tracks, int position) {

    }

    @Override
    public void previous(List<Track> tracks, int position) {

    }

    public class LocalBinder extends Binder {
        public MusicService getService() {
            return MusicService.this;
        }
    }
}
