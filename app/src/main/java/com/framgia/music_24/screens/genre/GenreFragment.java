package com.framgia.music_24.screens.genre;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import com.framgia.music_24.R;
import com.framgia.music_24.data.model.Track;
import com.framgia.music_24.data.repository.TracksRepository;
import com.framgia.music_24.data.source.remote.TracksRemoteDataSource;
import com.framgia.music_24.screens.EndlessScrollListener;
import com.framgia.music_24.screens.play.PlayScreenFragment;
import com.framgia.music_24.utils.DisplayUtils;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.music_24.screens.discover.DiscoverFragment.ARGUMENT_POSITION_ITEM;
import static com.framgia.music_24.screens.discover.DiscoverFragment.ARGUMENT_TITLE_ITEM;
import static com.framgia.music_24.utils.Constants.ARROW;

/**
 * A simple {@link Fragment} subclass.
 */
public class GenreFragment extends Fragment
        implements GenreContract.View, GenreAdapter.OnClickListener {

    public static final String TAG = "GenreDetails";
    private static final String[] mGenres = new String[] {
            "all-music", "all-audio", "alternativerock", "ambient", "classical", "country"
    };
    private static final int LIMIT_PER_CALL = 10;
    private static final int NUMBER_ONE = 1;
    private FragmentActivity mContext;
    private GenreContract.Presenter mPresenter;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerGenre;
    private List<Track> mTracks;
    private GenreAdapter mAdapter;
    private int mLimit = 10;
    private int mPosition;

    public static GenreFragment newInstance(int position, String genre) {
        Bundle args = new Bundle();
        GenreFragment fragment = new GenreFragment();
        args.putInt(ARGUMENT_POSITION_ITEM, position);
        args.putString(ARGUMENT_TITLE_ITEM, genre.replace(ARROW, ""));
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

    public GenreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genre, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new GenrePresenter(
                TracksRepository.getInstance(TracksRemoteDataSource.getInstance()));
        mPresenter.setView(this);
        initComponents();
    }

    private void initViews(View view) {
        mProgressBar = view.findViewById(R.id.progress_genre);
        mRecyclerGenre = view.findViewById(R.id.recycler_gender);
    }

    private void initComponents() {
        mTracks = new ArrayList<>();
        getDataFromActivity();
    }

    private void getDataFromActivity() {
        if (getArguments() != null) {
            mPosition = getArguments().getInt(ARGUMENT_POSITION_ITEM);
            String genre = getArguments().getString(ARGUMENT_TITLE_ITEM);
            mTracks = new ArrayList<>();
            mPresenter.loadDataGenre(mGenres[mPosition], genre, mLimit, mTracks);
        }
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
        super.onStop();
    }

    @Override
    public void setupData(List<Track> tracks) {
        mTracks = tracks;
        setupRecycleView();
    }

    private void setupRecycleView() {
        final RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerGenre.setLayoutManager(manager);
        mAdapter = new GenreAdapter(getContext(), mTracks, this);
        mRecyclerGenre.setAdapter(mAdapter);
        mRecyclerGenre.addOnScrollListener(
                new EndlessScrollListener((LinearLayoutManager) mRecyclerGenre.getLayoutManager()) {
                    @Override
                    public void OnLoadMore() {
                        loadMore();
                    }
                });
    }

    @Override
    public void onGetDataError(Exception e) {

    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void loadMore(List<Track> tracks) {
        mTracks.remove(mTracks.size() - NUMBER_ONE);
        mAdapter.notifyItemRemoved(mTracks.size());
        tracks.subList(0, mTracks.size()).clear();
        mTracks.addAll(tracks);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnItemClick(List<Track> tracks, int position) {
        DisplayUtils.addFragment(mContext.getSupportFragmentManager(),
                PlayScreenFragment.newInstance(tracks, position), R.id.coordinator_add_play, PlayScreenFragment.TAG);
    }

    private void loadMore() {
        mTracks.add(null);
        mLimit += LIMIT_PER_CALL;
        mAdapter.notifyItemInserted(mTracks.size() - NUMBER_ONE);
        mPresenter.loadDataGenre(mGenres[mPosition], "", mLimit, mTracks);
    }
}
