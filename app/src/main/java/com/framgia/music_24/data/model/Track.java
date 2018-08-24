package com.framgia.music_24.data.model;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class Track {

    private String mArtworkUrl;
    private String mCreatedAt;
    private String mDescription;
    private boolean mDownloadable;
    private int mDuration;
    private int mFullDuration;
    private int mId;
    private String mLastModified;
    private int mLikesCount;
    private int mPlaybackCount;
    private String mTitle;
    private int mUserId;
    private String mDisplayDate;
    private User mUser;

    private Track(TrackBuilder trackBuilder) {
        mArtworkUrl = trackBuilder.mArtworkUrl;
        mCreatedAt = trackBuilder.mCreatedAt;
        mDescription = trackBuilder.mDescription;
        mDownloadable = trackBuilder.mDownloadable;
        mDuration = trackBuilder.mDuration;
        mFullDuration = trackBuilder.mFullDuration;
        mId = trackBuilder.mId;
        mLastModified = trackBuilder.mLastModified;
        mLikesCount = trackBuilder.mLikesCount;
        mPlaybackCount = trackBuilder.mPlaybackCount;
        mTitle = trackBuilder.mTitle;
        mUserId = trackBuilder.mUserId;
        mDisplayDate = trackBuilder.mDisplayDate;
        mUser = trackBuilder.mUser;
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

    public int getLikesCount() {
        return mLikesCount;
    }

    public int getPlaybackCount() {
        return mPlaybackCount;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getUserId() {
        return mUserId;
    }

    public String getDisplayDate() {
        return mDisplayDate;
    }

    public User getUser() {
        return mUser;
    }

    public static final class TrackBuilder {
        private String mArtworkUrl;
        private String mCreatedAt;
        private String mDescription;
        private boolean mDownloadable;
        private int mDuration;
        private int mFullDuration;
        private int mId;
        private String mLastModified;
        private int mLikesCount;
        private int mPlaybackCount;
        private String mTitle;
        private int mUserId;
        private String mDisplayDate;
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

        public TrackBuilder LikesCount(int likesCount) {
            mLikesCount = likesCount;
            return this;
        }

        public TrackBuilder PlaybackCount(int playbackCount) {
            mPlaybackCount = playbackCount;
            return this;
        }

        public TrackBuilder Title(String title) {
            mTitle = title;
            return this;
        }

        public TrackBuilder UserId(int userId) {
            mUserId = userId;
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
}
