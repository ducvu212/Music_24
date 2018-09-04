package com.framgia.music_24.screens.play;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import com.framgia.music_24.data.model.Track;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.music_24.data.source.remote.TracksRemoteDataSource.buildStreamUrl;

/**
 * Created by CuD HniM on 18/08/29.
 */
public class MusicPlayer
        implements MediaPlayer.OnPreparedListener, MediaPlayer.OnBufferingUpdateListener,
        MediaPlayer.OnCompletionListener {

    private static final float MAX_VOLUME_INDEX = 1.0f;
    private static MusicPlayer sInstance;
    private MediaPlayer mMediaPlayer;
    private Context mContext;
    private List<Track> mTracks;
    private List<Track> mUnShuffleTracks;
    private int mPosition;
    private boolean mIsLoopOne;
    private boolean mIsLoopAll;
    private OnUpdateUiListener mMediaState;

    private MusicPlayer(Context context, List<Track> tracks, int position,
            OnUpdateUiListener onListener) {
        mContext = context;
        mTracks = new ArrayList<>();
        mTracks.addAll(tracks);
        mPosition = position;
        mMediaState = onListener;
    }

    public static synchronized MusicPlayer getInstance(Context context, List<Track> tracks,
            int position, OnUpdateUiListener listener) {
        if (sInstance == null) {
            synchronized (MusicPlayer.class) {
                if (sInstance == null) {
                    sInstance = new MusicPlayer(context, tracks, position, listener);
                }
            }
        }
        return sInstance;
    }

    public void setDataSource(String url) {
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(mContext, Uri.parse(url));
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setVolume(MAX_VOLUME_INDEX, MAX_VOLUME_INDEX);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.setOnCompletionListener(this);
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

    public void next() {
        destroyMedia();
        if (mIsLoopOne) {
            return;
        }
        if (mIsLoopAll) {
            if (mPosition == mTracks.size() - 1) {
                mPosition = 0;
            } else {
                mPosition++;
            }
        } else {
            if (mPosition == mTracks.size() - 1) {
                return;
            } else {
                mPosition++;
            }
        }
        updateUiState(mTracks.get(mPosition));
    }

    private void updateUiState(Track track) {
        setDataSource(buildStreamUrl(track.getId()));
        mMediaState.OnUpdateUiPlay(track);
    }

    public void destroyMedia() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public void previous() {
        destroyMedia();
        if (mPosition > 0) {
            mPosition--;
        } else {
            mPosition = 0;
        }
        updateUiState(mTracks.get(mPosition));
    }

    public void seekTo(int position) {
        mMediaPlayer.seekTo(position);
    }

    public int getCurrentPosition() {
        return mMediaPlayer == null ? 0 : mMediaPlayer.getCurrentPosition();
    }

    public int getDuration() {
        return mMediaPlayer.getDuration();
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        mMediaState.OnBuffer(i);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mMediaState.OnPlayComplete();
        next();
    }
}
