package com.framgia.music_24.data.source.remote;

import android.os.AsyncTask;
import com.framgia.music_24.data.model.Discover;
import com.framgia.music_24.data.model.Track;
import com.framgia.music_24.data.source.CallBack;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

import static com.framgia.music_24.data.source.remote.UrlDataParser.getJsonFromUrl;
import static com.framgia.music_24.data.source.remote.UrlDataParser.parseJsonTrack;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class TracksAsyncTask extends AsyncTask<String, Void, List<Track>> {

    private CallBack mCallBack;
    private Exception mException;
    private String mGenreTitle;
    private List<Object> mDataList;

    TracksAsyncTask(String genreTitle, List<Object> datas, CallBack callBack) {
        mDataList = datas;
        mCallBack = callBack;
        mGenreTitle = genreTitle;
    }

    @Override
    protected List<Track> doInBackground(String... strings) {
        List<Track> tracks = new ArrayList<>();
        try {
            String json = getJsonFromUrl(strings[0]);
            tracks = parseJsonTrack(json);
        } catch (IOException e) {
            e.printStackTrace();
            mException = e;
        } catch (JSONException e) {
            e.printStackTrace();
            mException = e;
        }
        return tracks;
    }

    @Override
    protected void onPostExecute(List<Track> tracks) {
        super.onPostExecute(tracks);
        if (mException == null) {
            if (mGenreTitle.equals("")) {
                mCallBack.onSuccess(tracks);
            } else {
                mDataList.add(new Discover.Builder().mGender(mGenreTitle).mTracks(tracks).build());
                mCallBack.onSuccess(mDataList);
            }
        } else {
            mCallBack.onError(mException);
        }
    }
}
