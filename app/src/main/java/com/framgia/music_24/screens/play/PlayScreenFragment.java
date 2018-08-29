package com.framgia.music_24.screens.play;

import android.content.Context;
import android.os.Bundle;
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
import java.util.ArrayList;
import java.util.List;

import static com.framgia.music_24.BuildConfig.API_KEY;
import static com.framgia.music_24.screens.discover.DiscoverFragment.ARGUMENT_POSITION_ITEM;
import static com.framgia.music_24.utils.Constants.STREAM;
import static com.framgia.music_24.utils.Constants.STREAM_CLIENT_ID;
import static com.framgia.music_24.utils.Constants.STREAM_TRACK_ID;
import static com.framgia.music_24.utils.Constants.STREAM_URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayScreenFragment extends Fragment implements PlayScreenContract.View, View.OnClickListener {

    public static final String TAG = "PlayScreenFragment";
    private static final String ARGUMENT_LIST_PLAY = "LIST_TRACKS_PLAYING";
    private PlayScreenContract.Presenter mPresenter;
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
    private FragmentActivity mContext;

    public PlayScreenFragment() {
        // Required empty public constructor
    }

    public static PlayScreenFragment newInstance(List<Track> tracks, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT_POSITION_ITEM, position);
        bundle.putParcelableArrayList(ARGUMENT_LIST_PLAY, (ArrayList<? extends Parcelable>) tracks);
        PlayScreenFragment fragment = new PlayScreenFragment();
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
        mPresenter = new PlayScreenPresenter();
        mPresenter.setView(this);
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
        List<Track> tracks = new ArrayList<>();
        if (getArguments() != null) {
            int position = getArguments().getInt(ARGUMENT_POSITION_ITEM);
            tracks = getArguments().getParcelableArrayList(ARGUMENT_LIST_PLAY);
            int id = tracks.get(position).getId();
            StringBuilder builder = new StringBuilder();
            String url = builder.append(STREAM_URL)
                    .append(STREAM_TRACK_ID)
                    .append(STREAM)
                    .append(STREAM_CLIENT_ID)
                    .append(API_KEY)
                    .toString();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imageview_play:

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
}
