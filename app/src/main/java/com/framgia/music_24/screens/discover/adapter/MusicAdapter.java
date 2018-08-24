package com.framgia.music_24.screens.discover.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.framgia.music_24.R;
import com.framgia.music_24.data.model.Music;
import java.util.List;

import static com.framgia.music_24.utils.StringUtils.isNotBlank;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {

    private Context mContext;
    private List<Music> mMusics;

    public MusicAdapter(Context context, List<Music> musics) {
        mContext = context;
        mMusics = musics;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_music, viewGroup, false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder musicViewHolder, int i) {
        musicViewHolder.bindData(mContext, mMusics.get(i));
    }

    @Override
    public int getItemCount() {
        return mMusics == null ? 0 : mMusics.size();
    }

    static class MusicViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextViewName;
        private TextView mTextViewSinger;
        private ImageView mImageMusic;

        MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewName = itemView.findViewById(R.id.textview_name_music);
            mTextViewSinger = itemView.findViewById(R.id.textview_name_singer);
            mImageMusic = itemView.findViewById(R.id.imageview_music);
        }

        void bindData(Context context, Music music) {
            mTextViewName.setText(music.getName());
            mTextViewSinger.setText(music.getSinger());
            if (music.getUrl() != null && isNotBlank(music.getUrl())) {
                Glide.with(context)
                        .load(music.getUrl())
                        .apply(new RequestOptions().placeholder(R.drawable.ic_image_place_holder)
                                .error(R.drawable.ic_load_image_error))
                        .into(mImageMusic);
            }
        }
    }
}
