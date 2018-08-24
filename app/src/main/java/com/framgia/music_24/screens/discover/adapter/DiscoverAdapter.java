package com.framgia.music_24.screens.discover.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.framgia.music_24.R;
import com.framgia.music_24.data.model.Discover;
import com.framgia.music_24.data.model.Music;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.DiscoverHolder> {

    private DiscoverHolder.onItemClickListener mListener;
    private List<Discover> mList;
    private Context mContext;
    private LayoutInflater mInflater;

    public DiscoverAdapter(Context context, List<Discover> discovers,
            DiscoverHolder.onItemClickListener onItemClickListener) {
        mContext = context;
        mList = discovers;
        mListener = onItemClickListener;
    }

    @NonNull
    @Override
    public DiscoverHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(viewGroup.getContext());
        }
        View view = mInflater.inflate(R.layout.item_discover_gender, viewGroup, false);
        return new DiscoverHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscoverHolder discoverHolder, int i) {
        discoverHolder.bindData(mContext, mList.get(i), mListener);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class DiscoverHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView mTextViewGender;
        private RecyclerView mRecyclerGender;
        private onItemClickListener mListener;

        DiscoverHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewGender = itemView.findViewById(R.id.textview_gender);
            mRecyclerGender = itemView.findViewById(R.id.recycler_gender);
        }

        public void bindData(Context context, Discover discover,
                onItemClickListener onItemClickListener) {
            mTextViewGender.setText(discover.getGender());
            mTextViewGender.setOnClickListener(this);
            initRecycleGenders(context, discover);
            mListener = onItemClickListener;
        }

        private void initRecycleGenders(Context context, Discover discover) {
            ArrayList<Music> music = (ArrayList<Music>) discover.getMusics();
            MusicAdapter adapter = new MusicAdapter(context, music);
            mRecyclerGender.setHasFixedSize(true);
            mRecyclerGender.setLayoutManager(
                    new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            mRecyclerGender.setAdapter(adapter);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.textview_gender:
                    if (mListener != null) mListener.onClick(getAdapterPosition());
                    break;
            }
        }

        public interface onItemClickListener {
            void onClick(int position);
        }
    }
}
