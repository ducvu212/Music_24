package com.framgia.music_24.screens.playinglist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.framgia.music_24.R;
import com.framgia.music_24.data.model.Track;
import com.framgia.music_24.screens.genre.GenreAdapter;
import java.util.List;

/**
 * Created by CuD HniM on 18/09/07.
 */
public class PlayingListAdapter extends RecyclerView.Adapter<PlayingListAdapter.PlayListHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Track> mTracks;
    private String mName;
    private GenreAdapter.OnClickListener mListener;

    PlayingListAdapter(Context context, List<Track> tracks, String name,
            GenreAdapter.OnClickListener OnClickListener) {
        mContext = context;
        mTracks = tracks;
        mName = name;
        mListener = OnClickListener;
    }

    @NonNull
    @Override
    public PlayListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mInflater.inflate(R.layout.item_genre, viewGroup, false);
        return new PlayListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListHolder playListHolder, int i) {
        playListHolder.bindData(mContext, mTracks, mName, mListener);
    }

    @Override
    public int getItemCount() {
        return mTracks == null ? 0 : mTracks.size();
    }

    static class PlayListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImageViewAva;
        private ImageView mImageViewFavorite;
        private TextView mTextViewName;
        private TextView mTextViewSinger;
        private TextView mTextViewCreated;
        private TextView mTextViewDuration;
        private List<Track> mTracks;
        private ConstraintLayout mLayout;
        private GenreAdapter.OnClickListener mListener;

        PlayListHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            mImageViewAva = itemView.findViewById(R.id.imageview_avatar);
            mImageViewFavorite = itemView.findViewById(R.id.imageview_favorite_genre);
            mTextViewCreated = itemView.findViewById(R.id.textview_created);
            mTextViewDuration = itemView.findViewById(R.id.textview_time);
            mTextViewName = itemView.findViewById(R.id.textview_name);
            mTextViewSinger = itemView.findViewById(R.id.textview_singer);
            mLayout = itemView.findViewById(R.id.layout_item_genre);
            mLayout.setOnClickListener(this);
            mImageViewFavorite.setOnClickListener(this);
        }
    }
}
