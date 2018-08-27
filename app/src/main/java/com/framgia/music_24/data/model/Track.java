package com.framgia.music_24.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

import static com.framgia.music_24.data.model.Track.TrackConstants.TRACK_ARTWORK_URL;
import static com.framgia.music_24.data.model.Track.TrackConstants.TRACK_CREATED_AT;
import static com.framgia.music_24.data.model.Track.TrackConstants.TRACK_DESCRIPTION;
import static com.framgia.music_24.data.model.Track.TrackConstants.TRACK_DISPLAY_DATE;
import static com.framgia.music_24.data.model.Track.TrackConstants.TRACK_DOWNLOADABLE;
import static com.framgia.music_24.data.model.Track.TrackConstants.TRACK_DURATION;
import static com.framgia.music_24.data.model.Track.TrackConstants.TRACK_FULL_DURATION;
import static com.framgia.music_24.data.model.Track.TrackConstants.TRACK_ID;
import static com.framgia.music_24.data.model.Track.TrackConstants.TRACK_LAST_MODIFIED;
import static com.framgia.music_24.data.model.Track.TrackConstants.TRACK_TITLE;
import static com.framgia.music_24.data.model.Track.TrackConstants.TRACK_USER;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class Track implements Parcelable {

    private String mArtworkUrl;
    private String mCreatedAt;
    private String mDescription;
    private String mLastModified;
    private String mTitle;
    private String mDisplayDate;
    private boolean mDownloadable;
    private int mDuration;
    private int mFullDuration;
    private int mId;
    private User mUser;

    public Track(TrackBuilder trackBuilder) {
        mArtworkUrl = trackBuilder.mArtworkUrl;
        mCreatedAt = trackBuilder.mCreatedAt;
        mDescription = trackBuilder.mDescription;
        mDownloadable = trackBuilder.mDownloadable;
        mDuration = trackBuilder.mDuration;
        mFullDuration = trackBuilder.mFullDuration;
        mId = trackBuilder.mId;
        mLastModified = trackBuilder.mLastModified;
        mTitle = trackBuilder.mTitle;
        mDisplayDate = trackBuilder.mDisplayDate;
        mUser = trackBuilder.mUser;
    }

    public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel source) {
            return new Track(source);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };

    public Track(JSONObject jsonObject) throws JSONException {
        mArtworkUrl = jsonObject.getString(TRACK_ARTWORK_URL);
        mCreatedAt = jsonObject.getString(TRACK_CREATED_AT);
        mDescription = jsonObject.getString(TRACK_DESCRIPTION);
        mLastModified = jsonObject.getString(TRACK_LAST_MODIFIED);
        mDisplayDate = jsonObject.getString(TRACK_DISPLAY_DATE);
        mTitle = jsonObject.getString(TRACK_TITLE);
        mDownloadable = jsonObject.getBoolean(TRACK_DOWNLOADABLE);
        mDuration = jsonObject.getInt(TRACK_DURATION);
        mFullDuration = jsonObject.getInt(TRACK_FULL_DURATION);
        mId = jsonObject.getInt(TRACK_ID);
        mUser = new User(jsonObject.getJSONObject(TRACK_USER));
    }

    protected Track(Parcel in) {
        mArtworkUrl = in.readString();
        mCreatedAt = in.readString();
        mDescription = in.readString();
        mDownloadable = in.readByte() != 0;
        mDuration = in.readInt();
        mFullDuration = in.readInt();
        mId = in.readInt();
        mLastModified = in.readString();
        mTitle = in.readString();
        mDisplayDate = in.readString();
        mUser = in.readParcelable(User.class.getClassLoader());
    }

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public String getCreatedAt() {
        return mCreatedAt;
    }

    public String getDescription() {
        return mDescription;
    }

    public boolean getDownloadable() {
        return mDownloadable;
    }

    public int getDuration() {
        return mDuration;
    }

    public int getFullDuration() {
        return mFullDuration;
    }

    public int getId() {
        return mId;
    }

    public String getLastModified() {
        return mLastModified;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getDisplayDate() {
        return mDisplayDate;
    }

    public User getUser() {
        return mUser;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mArtworkUrl);
        dest.writeString(mCreatedAt);
        dest.writeString(mDescription);
        dest.writeByte(mDownloadable ? (byte) 1 : (byte) 0);
        dest.writeInt(mDuration);
        dest.writeInt(mFullDuration);
        dest.writeInt(mId);
        dest.writeString(mLastModified);
        dest.writeString(mTitle);
        dest.writeString(mDisplayDate);
        dest.writeParcelable(mUser, flags);
    }

    public static final class TrackBuilder {
        private String mArtworkUrl;
        private String mCreatedAt;
        private String mDescription;
        private String mLastModified;
        private String mDisplayDate;
        private String mTitle;
        private boolean mDownloadable;
        private int mDuration;
        private int mFullDuration;
        private int mId;
        private User mUser;

        public TrackBuilder() {
        }

        public TrackBuilder ArtworkUrl(String artworkUrl) {
            mArtworkUrl = artworkUrl;
            return this;
        }

        public TrackBuilder CreatedAt(String createdAt) {
            mCreatedAt = createdAt;
            return this;
        }

        public TrackBuilder Description(String description) {
            mDescription = description;
            return this;
        }

        public TrackBuilder Downloadable(boolean downloadable) {
            mDownloadable = downloadable;
            return this;
        }

        public TrackBuilder Duration(int duration) {
            mDuration = duration;
            return this;
        }

        public TrackBuilder FullDuration(int fullDuration) {
            mFullDuration = fullDuration;
            return this;
        }

        public TrackBuilder Id(int id) {
            mId = id;
            return this;
        }

        public TrackBuilder LastModified(String lastModified) {
            mLastModified = lastModified;
            return this;
        }

        public TrackBuilder Title(String title) {
            mTitle = title;
            return this;
        }

        public TrackBuilder DisplayDate(String displayDate) {
            mDisplayDate = displayDate;
            return this;
        }

        public TrackBuilder User(User users) {
            mUser = users;
            return this;
        }

        public Track build() {
            return new Track(this);
        }
    }

    static class TrackConstants {
        //Track
        static final String TRACK_ARTWORK_URL = "artwork_url";
        static final String TRACK_CREATED_AT = "created_at";
        static final String TRACK_DESCRIPTION = "description";
        static final String TRACK_LAST_MODIFIED = "last_modified";
        static final String TRACK_DISPLAY_DATE = "display_date";
        static final String TRACK_TITLE = "title";
        static final String TRACK_DOWNLOADABLE = "downloadable";
        static final String TRACK_DURATION = "duration";
        static final String TRACK_FULL_DURATION = "full_duration";
        static final String TRACK_ID = "id";
        static final String TRACK_USER = "user";
    }
}
