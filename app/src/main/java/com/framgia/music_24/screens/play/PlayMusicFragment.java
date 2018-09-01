package com.framgia.music_24.screens.play;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
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
import com.framgia.music_24.R;
import com.framgia.music_24.data.model.Track;
import com.framgia.music_24.data.repository.TracksRepository;
import com.framgia.music_24.data.source.remote.TracksRemoteDataSource;
import com.framgia.music_24.service.MusicService;
import com.framgia.music_24.service.OnMusicListener;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.music_24.data.source.remote.TracksRemoteDataSource.buildStreamUrl;
import static com.framgia.music_24.screens.discover.DiscoverFragment.ARGUMENT_POSITION_ITEM;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayMusicFragment extends Fragment
        implements PlayMusicContract.View, View.OnClickListener, OnButtonStateListener {

    public static final String TAG = "PlayMusicFragment";
    private static final String ARGUMENT_LIST_PLAY = "LIST_TRACKS_PLAYING";
    private FragmentActivity mContext;
    private PlayMusicContract.Presenter mPresenter;
    private ImageView mImageViewPlay;
    private ImageView mImageViewNext;
    private ImageView mImageViewPrevious;
    private ImageView mImageViewLoop;
    private ImageView mImageViewShuffle;
    private ImageView mImageViewFavorite;
    private ImageView mImageViewDownload;
    private TextView mTextViewName;
    private TextView mTextViewSinger;
    private TextView mTextViewTimeRunning;
    private TextView mTextViewTotalTime;
    private SeekBar mSeekBar;
    private List<Track> mTracks;
    private MusicPlayer mPlayer;
    private boolean mIsConnect;
    private int mId;
    private MusicService mMusicService;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MusicService.LocalBinder binder = (MusicService.LocalBinder) iBinder;
            mMusicService = binder.getService();
            if (mMusicService != null) {
                mIsConnect = true;
                mMusicService.registerService(mPlayer);
                mMusicService.setDataSource(buildStreamUrl(mId));
                mMediaListener = mMusicService.getListener();
                mMediaListener.play();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mIsConnect = false;
            connectService();
        }
    };
    private OnMusicListener mMediaListener;

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
        initComponents();
    }

    private void initViews(View view) {
        mImageViewDownload = view.findViewById(R.id.imageview_download);
        mImageViewFavorite = view.findViewById(R.id.imageview_favorite);
        mImageViewShuffle = view.findViewById(R.id.imageview_shuffle);
        mImageViewLoop = view.findViewById(R.id.imageview_loop);
        mImageViewPrevious = view.findViewById(R.id.imageview_previous);
        mImageViewNext = view.findViewById(R.id.imageview_next);
        mImageViewPlay = view.findViewById(R.id.imageview_play);
        mTextViewTimeRunning = view.findViewById(R.id.textview_time_running);
        mTextViewSinger = view.findViewById(R.id.textview_singer);
        mTextViewName = view.findViewById(R.id.textview_name);
        mTextViewTotalTime = view.findViewById(R.id.textview_time_total);
        FrameLayout frameLayout = view.findViewById(R.id.frame_play);
        frameLayout.setOnClickListener(this);
    }

    private void initComponents() {
        mPresenter = new PlayMusicPresenter(
                TracksRepository.getInstance(TracksRemoteDataSource.getInstance()));
        mPresenter.setView(this);
        mPlayer = new MusicPlayer(getContext(), this);
        mImageViewDownload.setOnClickListener(this);
        mImageViewFavorite.setOnClickListener(this);
        mImageViewShuffle.setOnClickListener(this);
        mImageViewLoop.setOnClickListener(this);
        mImageViewPrevious.setOnClickListener(this);
        mImageViewNext.setOnClickListener(this);
        mImageViewPlay.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDataFromActivity();
    }

    private void getDataFromActivity() {
        if (getArguments() != null) {
            int position = getArguments().getInt(ARGUMENT_POSITION_ITEM);
            mTracks = getArguments().getParcelableArrayList(ARGUMENT_LIST_PLAY);
            mId = mTracks.get(position).getId();
            mPlayer.destroyMedia();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageview_play:
                mMediaListener.play();
                break;

            case R.id.imageview_next:

                break;

            case R.id.imageview_previous:

                break;

            case R.id.imageview_loop:

                break;

            case R.id.imageview_shuffle:

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
}
