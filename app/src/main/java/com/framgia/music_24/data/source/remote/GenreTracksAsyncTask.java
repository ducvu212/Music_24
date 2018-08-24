package com.framgia.music_24.data.source.remote;

import android.os.AsyncTask;
import com.framgia.music_24.data.model.Collection;
import com.framgia.music_24.data.source.CallBack;
import java.util.List;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class GenreTracksAsyncTask extends AsyncTask<String, Void, List<Collection>> {

    private CallBack mCallBack;

    public GenreTracksAsyncTask(CallBack callBack) {
        mCallBack = callBack;
    }

    @Override
    protected List<Collection> doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(List<Collection> collections) {
        super.onPostExecute(collections);
    }
}
