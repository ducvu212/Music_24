package com.framgia.music_24.screens.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.framgia.music_24.R;
import com.framgia.music_24.data.model.Discover;
import com.framgia.music_24.screens.discover.adapter.DiscoverAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment
        implements DiscoverContract.View, DiscoverAdapter.DiscoverHolder.onItemClickListener {

    public static final String TAG = "DiscoverFragment";
    private DiscoverContract.Presenter mPresenter;
    private RecyclerView mRecyclerAllGenders;
    private List<Discover> mDiscovers;

    public DiscoverFragment() {
        // Required empty public constructor
    }

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initComponents();
    }

    private void initViews(View view) {
        mRecyclerAllGenders = view.findViewById(R.id.recycler_gender);
    }

    private void initComponents() {
        mDiscovers = new ArrayList<>();
        setupRecycleView();
    }

    private void setupRecycleView() {
        DiscoverAdapter adapter = new DiscoverAdapter(getContext(), mDiscovers, this);
        mRecyclerAllGenders.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerAllGenders.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new DiscoverPresenter();
        mPresenter.setView(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onClick(int position) {

    }
}
