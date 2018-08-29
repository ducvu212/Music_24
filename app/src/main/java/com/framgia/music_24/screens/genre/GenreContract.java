package com.framgia.music_24.screens.genre;

import com.framgia.music_24.data.model.Track;
import com.framgia.music_24.screens.base.BasePresenter;
import java.util.List;

/**
 * Created by CuD HniM on 18/08/28.
 */
public interface GenreContract {

    /**
     * View
     */

    interface View {
        void setupData(List<Track> tracks);

        void onGetDataError(Exception e);

        void showProgress();

        void hideProgress();

        void loadMore(List<Track> tracks);
    }

    /**
     * Presenter
     */

    interface Presenter extends BasePresenter<View> {
       void loadDataGenre(String genre, String genreTitle, int limit, List<Track> tracks);
    }
}
