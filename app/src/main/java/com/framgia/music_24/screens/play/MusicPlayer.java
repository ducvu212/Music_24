package com.framgia.music_24.screens.play;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import com.framgia.music_24.data.model.Track;
import java.io.IOException;
import java.util.List;

import static com.framgia.music_24.screens.play.PlayMusicFragment.buildStreamUrl;

/**
 * Created by CuD HniM on 18/08/29.
 */
public class MusicPlayer
        implements MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener {

    private static final float MAX_VOLUME_INDEX = 1.0f;
    private MediaPlayer mMediaPlayer;
    private Context mContext;
    private OnButtonStateListener mMediaState;

    public MusicPlayer(Context context, OnButtonStateListener onListener) {
        mContext = context;
        mMediaState = onListener;
    }

    public void setDataSource(String url) {
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(mContext, Uri.parse(url));
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setVolume(MAX_VOLUME_INDEX, MAX_VOLUME_INDEX);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setOnBufferingUpdateListener(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void play() {
        if (mMediaPlayer != null && !mMediaPlayer.isPlaying()) {
            mMediaPlayer.start();
            mMediaState.updateStateButton(true);
        } else {
            mMediaPlayer.pause();
            mMediaState.updateStateButton(false);
        }
    }

    public void next(List<Track> tracks, int position) {
        destroyMedia();
        if (position == tracks.size() - 1) {
            position = 0;
        } else {
            position++;
        }
        setDataSource(buildStreamUrl(tracks.get(position).getId()));
    }

    public void destroyMedia() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void previous(List<Track> tracks, int position) {
        destroyMedia();
        position--;
        setDataSource(buildStreamUrl(tracks.get(position).getId()));
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {

    }
}
