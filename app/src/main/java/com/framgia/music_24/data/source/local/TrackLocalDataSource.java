package com.framgia.music_24.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.framgia.music_24.data.model.Track;
import com.framgia.music_24.data.source.TracksDataSource;
import com.framgia.music_24.data.source.local.config.shareprefs.SharedPrefsImpl;
import com.framgia.music_24.data.source.local.config.sqlite.TrackDatabaseHelper;
import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.Intents.Insert.NAME;
import static com.framgia.music_24.data.source.local.config.sqlite.TrackDatabaseHelper
        .QUERY_ALL_RECODRD;
import static com.framgia.music_24.data.source.local.config.sqlite.TrackDatabaseHelper.TrackEntry
        .ART_URL;
import static com.framgia.music_24.data.source.local.config.sqlite.TrackDatabaseHelper.TrackEntry
        .DATABASE_TABLE_NAME;
import static com.framgia.music_24.data.source.local.config.sqlite.TrackDatabaseHelper.TrackEntry
        .DOWNLOADED;
import static com.framgia.music_24.data.source.local.config.sqlite.TrackDatabaseHelper.TrackEntry
        .FAVORITE;
import static com.framgia.music_24.data.source.local.config.sqlite.TrackDatabaseHelper.TrackEntry
        .TRACK_ID;
import static com.framgia.music_24.data.source.local.config.sqlite.TrackDatabaseHelper.TrackEntry
        .TRACK_URI;
import static com.framgia.music_24.data.source.local.config.sqlite.TrackDatabaseHelper.TrackEntry
        .TRACK_URL;
import static com.framgia.music_24.data.source.remote.TracksRemoteDataSource.buildStreamUrl;

/**
 * Created by CuD HniM on 18/09/04.
 */
public class TrackLocalDataSource implements TracksDataSource.TrackLocalDataSource {

    private static TrackLocalDataSource sInstance;
    private TrackDatabaseHelper mHelper;
    private SharedPrefsImpl mSharedPrefs;

    private TrackLocalDataSource(Context context, TrackDatabaseHelper trackDatabaseHelper) {
        mHelper = trackDatabaseHelper;
        mSharedPrefs = SharedPrefsImpl.getInstance(context);
    }

    public static synchronized TrackLocalDataSource getInstance(Context context,
            TrackDatabaseHelper trackDatabaseHelper) {
        if (sInstance == null) {
            synchronized (PlaySettingLocalDataSource.class) {
                if (sInstance == null) {
                    sInstance = new TrackLocalDataSource(context, trackDatabaseHelper);
                }
            }
        }
        return sInstance;
    }

    @Override
    public void addTrack(Track track) {
        if (track == null) {
            return;
        }
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, track.getTitle());
        values.put(TRACK_URL, buildStreamUrl(track.getId()));
        values.put(TRACK_ID, track.getId());
        values.put(ART_URL, track.getArtworkUrl());
        values.put(DOWNLOADED, false);
        values.put(FAVORITE, false);
        db.insert(DATABASE_TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public List<Track> getAllTracks() {
        List<Track> tracks = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(QUERY_ALL_RECODRD, null);
        tracks.add(createTrack(tracks, cursor));
        cursor.close();
        db.close();
        return tracks;
    }

    private Track createTrack(List<Track> tracks, Cursor cursor) {
        Track track = null;
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            do {
                String title = cursor.getString(1);
                String url = cursor.getString(2);
                int id = cursor.getInt(3);
                String art = cursor.getString(4);
                int download = cursor.getInt(5);
                int favorite = cursor.getInt(6);
                track = new Track.TrackBuilder().Title(title)
                        .Downloaded(download)
                        .Url(url)
                        .Id(id)
                        .ArtworkUrl(art)
                        .Favorite(favorite)
                        .build();
                if (tracks != null) {
                    tracks.add(track);
                }
            } while (cursor.moveToNext());
        }
        return track;
    }

    @Override
    public void updateFavorite(Track track, int fav) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FAVORITE, fav);
        db.update(DATABASE_TABLE_NAME, values, TRACK_ID + "=?",
                new String[] { String.valueOf(track.getId()) });
    }

    @Override
    public boolean isExistRow(Track track) {
        Cursor cursor;
        boolean check;
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "SELECT * FROM " + DATABASE_TABLE_NAME + " WHERE TRACK_ID=" + track.getId();
        cursor = db.rawQuery(sql, null);
        if (cursor.getCount() > 0) {
            check = true;
        } else {
            check = false;
        }
        cursor.close();
        return check;
    }

    @Override
    public Track findTrackById(String trackId) {
        Cursor cursor;
        Track track;
        SQLiteDatabase db = mHelper.getWritableDatabase();
        String sql = "SELECT * FROM " + DATABASE_TABLE_NAME + " WHERE TRACK_ID=" + trackId;
        cursor = db.rawQuery(sql, null);
        track = createTrack(null, cursor);
        cursor.close();
        db.close();
        return track;
    }

    @Override
    public void updateDownload(Track track, int download, String uri) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DOWNLOADED, download);
        values.put(TRACK_URI, uri);
        db.update(DATABASE_TABLE_NAME, values, TRACK_ID + "=?",
                new String[] { String.valueOf(track.getId()) });
    }
}
