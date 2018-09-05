package com.framgia.music_24.screens.play;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.music_24.R;
import com.framgia.music_24.data.model.Setting;
import com.framgia.music_24.data.model.Track;
import com.framgia.music_24.data.repository.PlaySettingRepository;
import com.framgia.music_24.data.source.local.PlaySettingLocalDataSource;
import com.framgia.music_24.service.MusicService;
import com.framgia.music_24.service.OnMusicListener;
import com.framgia.music_24.utils.DisplayUtils;
import com.framgia.music_24.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.music_24.data.source.remote.TracksRemoteDataSource.buildStreamUrl;
import static com.framgia.music_24.screens.discover.DiscoverFragment.ARGUMENT_POSITION_ITEM;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayMusicFragment extends Fragment
        implements PlayMusicContract.View, View.OnClickListener, OnUpdateUiListener,
        SeekBar.OnSeekBarChangeListener {

    public static final String TAG = "PlayMusicFragment";
    private static final String ARGUMENT_LIST_PLAY = "LIST_TRACKS_PLAYING";
    private static final int TIME_UPDATE_SEEKBAR = 1000;
    private static final int TIME_UPDATE_SEEKBAR_LOOP = 300;
    private FragmentActivity mContext;
    private PlayMusicContract.Presenter mPresenter;
    private ImageView mImageViewPlay;
    private ImageView mImageViewNext;
    private ImageView mImageViewPrevious;
    private ImageView mImageViewLoop;
    private ImageView mImageViewShuffle;
    private ImageView mImageViewFavorite;
    private ImageView mImageViewDownload;
    private ImageView mImageViewTrack;
    private TextView mTextViewName;
    private TextView mTextViewSinger;
    private TextView mTextViewTimeRunning;
    private TextView mTextViewTotalTime;
    private SeekBar mSeekBar;
    private MusicPlayer mPlayer;
    private Track mCurrentTrack;
    private int mId;
    private int mSeekbarPosition;
    private boolean mIsShuffle;
    private Handler mHandler;
    private OnMusicListener mMediaListener;
    private Runnable mRunnable;
    private MusicService mService;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicService.LocalBinder binder = (MusicService.LocalBinder) iBinder;
            mService = binder.getService();
            if (mService != null) {
                mService.registerService(mPlayer);
                mService.setDataSource(buildStreamUrl(mId));
                setupPlaySetting();
                mMediaListener = mService.getListener();
                mHandler.postDelayed(mRunnable, TIME_UPDATE_SEEKBAR_LOOP);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            connectService();
        }
    };

    public PlayMusicFragment() {
        // Required empty public constructor
    }

    public static PlayMusicFragment newInstance(List<Track> tracks, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_POSITION_ITEM, position);
        bundle.putParcelableArrayList(ARGUMENT_LIST_PLAY, (ArrayList<? extends Parcelable>) tracks);
        PlayMusicFragment fragment = new PlayMusicFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = (FragmentActivity) context;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
        connectService();
    }

    private void connectService() {
        final Intent intent = new Intent(getContext(), MusicService.class);
        mContext.getApplicationContext().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return inflater.inflate(R.layout.fragment_play, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {
        mImageViewDownload = view.findViewById(R.id.imageview_download);
        mImageViewFavorite = view.findViewById(R.id.imageview_favorite);
        mImageViewShuffle = view.findViewById(R.id.imageview_shuffle);
        mImageViewLoop = view.findViewById(R.id.imageview_loop);
        mImageViewPrevious = view.findViewById(R.id.imageview_previous);
        mImageViewNext = view.findViewById(R.id.imageview_next);
        mImageViewPlay = view.findViewById(R.id.imageview_play);
        mImageViewTrack = view.findViewById(R.id.imageview_track);
        mTextViewTimeRunning = view.findViewById(R.id.textview_time_running);
        mTextViewSinger = view.findViewById(R.id.textview_singer);
        mTextViewName = view.findViewById(R.id.textview_name_track);
        mTextViewTotalTime = view.findViewById(R.id.textview_time_total);
        mSeekBar = view.findViewById(R.id.seekBar);
        FrameLayout frameLayout = view.findViewById(R.id.frame_play);
        frameLayout.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initComponents();
        getDataFromActivity();
    }

    private void initComponents() {
        mPresenter = new PlayMusicPresenter(PlaySettingRepository.getInstance(
                PlaySettingLocalDataSource.getInstance(mContext)));
        setupListener();
        mHandler = new Handler();
        updateSeekBar();
    }

    private void setupListener() {
        mPresenter.setView(this);
        mImageViewDownload.setOnClickListener(this);
        mImageViewFavorite.setOnClickListener(this);
        mImageViewShuffle.setOnClickListener(this);
        mImageViewLoop.setOnClickListener(this);
        mImageViewPrevious.setOnClickListener(this);
        mImageViewNext.setOnClickListener(this);
        mImageViewPlay.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(this);
    }

    private void setupPlaySetting() {
        Setting setting = mPresenter.getSetting();
        mIsShuffle = setting.isShuffle();
        if (mIsShuffle) {
            mImageViewShuffle.setImageResource(R.drawable.ic_shuffle_on);
        } else {
            mImageViewShuffle.setImageResource(R.drawable.ic_shuffle_off);
        }
        switch (setting.getLoopMode()) {
            case LoopType.NO_LOOP:
                mImageViewLoop.setImageResource(R.drawable.ic_loop_off);
                break;

            case LoopType.LOOP_ONE:
                mImageViewLoop.setImageResource(R.drawable.ic_loop_one_on);
                break;

            case LoopType.LOOP_ALL:
                mImageViewLoop.setImageResource(R.drawable.ic_loop_on);
                break;
        }
    }

    private void checkShuffle(boolean isShuffle) {
        if (isShuffle) {
            mImageViewShuffle.setImageResource(R.drawable.ic_shuffle_off);
            DisplayUtils.makeToast(mContext, getString(R.string.play_shuffle_off));
        } else {
            mImageViewShuffle.setImageResource(R.drawable.ic_shuffle_on);
            DisplayUtils.makeToast(mContext, getString(R.string.play_shuffle_on));
        }
    }

    private void setShuffle() {
        Setting setting = mPresenter.getSetting();
        checkShuffle(mIsShuffle);
        mIsShuffle = !mIsShuffle;
        setting.setShuffle(mIsShuffle);
        mPresenter.saveSetting(setting);
    }

    private void setLoop() {
        Setting setting = mPresenter.getSetting();
        switch (setting.getLoopMode()) {
            case LoopType.NO_LOOP:
                mImageViewLoop.setImageResource(R.drawable.ic_loop_one_on);
                mMediaListener.setLoopOne();
                setting.setLoopMode(LoopType.LOOP_ONE);
                DisplayUtils.makeToast(mContext, getString(R.string.play_loop_one));
                break;

            case LoopType.LOOP_ONE:
                mImageViewLoop.setImageResource(R.drawable.ic_loop_on);
                mMediaListener.setLoopAll();
                setting.setLoopMode(LoopType.LOOP_ALL);
                DisplayUtils.makeToast(mContext, getString(R.string.play_loop_all));
                break;

            case LoopType.LOOP_ALL:
                mImageViewLoop.setImageResource(R.drawable.ic_loop_off);
                mMediaListener.setLoopOff();
                setting.setLoopMode(LoopType.NO_LOOP);
                DisplayUtils.makeToast(mContext, getString(R.string.play_loop_off));
                break;
        }
        mPresenter.saveSetting(setting);
    }

    private void getDataFromActivity() {
        if (getArguments() != null) {
            int position = getArguments().getInt(ARGUMENT_POSITION_ITEM);
            List<Track> tracks = getArguments().getParcelableArrayList(ARGUMENT_LIST_PLAY);
            mId = tracks.get(position).getId();
            if (mPlayer != null) {
                mPlayer.destroyMedia();
            }
            mPlayer = MusicPlayer.getInstance(getContext(), tracks, position, this);
            showTrackInfo(tracks.get(position));
        }
    }

    private void showTrackInfo(Track track) {
        setImageTrack(track.getArtworkUrl());
        mTextViewName.setText(track.getTitle());
        mTextViewSinger.setText(track.getUser().getUsername());
        mTextViewTotalTime.setText(StringUtils.convertMilisecToMinute(track.getDuration()));
        mSeekBar.setMax(track.getDuration());
    }

    private void setImageTrack(String url) {
        if (!mContext.isFinishing()) {
            Glide.with(mContext)
                    .load(url)
                    .apply(new RequestOptions().placeholder(R.drawable.ic_image_place_holder)
                            .error(R.drawable.ic_load_image_error))
                    .into(mImageViewTrack);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageview_play:
                mMediaListener.play();
                break;

            case R.id.imageview_next:
                mMediaListener.next();
                break;

            case R.id.imageview_previous:
                mMediaListener.previous();
                break;

            case R.id.imageview_loop:
                setLoop();
                break;

            case R.id.imageview_shuffle:
                setShuffle();
                mMediaListener.setShuffle(mIsShuffle);
                break;

            case R.id.imageview_favorite:

                break;

            case R.id.imageview_download:

                break;

            case R.id.frame_play:
                ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
                break;

            default:
        }
    }

    private void updateSeekBar() {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                long timeRunning = mMediaListener.getCurrentPosition();
                mSeekBar.setProgress((int) timeRunning);
                mTextViewTimeRunning.setText(StringUtils.convertMilisecToMinute(timeRunning));
                mHandler.postDelayed(mRunnable, TIME_UPDATE_SEEKBAR);
            }
        };
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    public void updateStateButton(boolean isPlaying) {
        if (isPlaying) {
            mImageViewPlay.setImageResource(R.drawable.ic_pause);
        } else {
            mImageViewPlay.setImageResource(R.drawable.ic_play);
        }
    }

    @Override
    public void OnUpdateUiPlay(Track track) {
        mCurrentTrack = track;
        showTrackInfo(track);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mSeekbarPosition = progress;
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mTextViewTimeRunning.setText(StringUtils.convertMilisecToMinute(mSeekbarPosition));
        mMediaListener.seekTo(mSeekbarPosition);
    }

    @Override
    public void OnPlayComplete() {
        if (mMediaListener != null) {
            Setting setting = mPresenter.getSetting();
            mService.checkStatus(setting);
        }
    }

    @Override
    public void OnBuffer(int position) {
        mSeekBar.setSecondaryProgress(position);
    }

    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(mRunnable);
        super.onDestroy();
    }
}
