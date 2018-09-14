package com.framgia.music_24.screens.playinglist;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.framgia.music_24.R;
import com.framgia.music_24.data.model.Track;
import com.framgia.music_24.data.repository.TracksRepository;
import com.framgia.music_24.data.source.local.TrackLocalDataSource;
import com.framgia.music_24.data.source.local.config.sqlite.TrackDatabaseHelper;
import com.framgia.music_24.data.source.remote.TracksRemoteDataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayingListFragment extends Fragment {

    public static final String TAG = "PlayingListFragment";
    private static final String ARGUMENT_LIST_PLAYING = "ARGUMENT_LIST_PLAYING";
    private static final String ARGUMENT_PLAYING_TRACK = "ARGUMENT_PLAYING_TRACK";
    private static final String ARGUMENT_PLAYING_TYPE = "ARGUMENT_PLAYING_TYPE";
    private FragmentActivity mContext;
    private PlayingListContract.Presenter mPresenter;
    private String mType;
    private ImageView mImageViewBack;
    private RecyclerView mRecyclerPlaying;
    private List<Track> mTracks;
    private PlayingListAdapter mAdapter;
    private FragmentManager mManager;

    public PlayingListFragment() {
        // Required empty public constructor
    }

    public static PlayingListFragment newInstance(List<Track> tracks, String type, String name) {
        Bundle args = new Bundle();
        args.putString(ARGUMENT_PLAYING_TRACK, name);
        args.putString(ARGUMENT_PLAYING_TYPE, type);
        args.putParcelableArrayList(ARGUMENT_LIST_PLAYING,
                (ArrayList<? extends Parcelable>) tracks);
        PlayingListFragment fragment = new PlayingListFragment();
        fragment.setArguments(args);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playing_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    private void initViews(View view) {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initComponents();
    }

    private void initComponents() {
        mPresenter = new PlayingListPresenter(
                TracksRepository.getInstance(TracksRemoteDataSource.getInstance(),
                        TrackLocalDataSource.getInstance(mContext,
                                TrackDatabaseHelper.getInstance(mContext))));
        mPresenter.setView(this);
        mTracks = new ArrayList<>();
        mManager = mContext.getSupportFragmentManager();
        mImageViewBack.setOnClickListener(this);
        handleBackKey();
    }

    private void handleBackKey() {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    ((AppCompatActivity) getActivity()).getSupportActionBar().show();
                    return true;
                }
                return true;
            }
        });
    }
}
