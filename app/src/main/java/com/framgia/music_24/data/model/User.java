package com.framgia.music_24.data.model;

/**
 * Created by CuD HniM on 18/08/24.
 */
public class User {

    private String mAvatarUrl;
    private String mFirstName;
    private String mFullName;
    private int mId;
    private String mKind;
    private String mLastModified;
    private String mLastName;
    private String mPermalink;
    private String mPermalinkUrl;
    private String mUri;
    private String mUrn;
    private String mUsername;
    private String mCity;
    private String mCountryCode;

    private User(UserBuilder userBuilder) {
        mAvatarUrl = userBuilder.mAvatarUrl;
        mFirstName = userBuilder.mFirstName;
        mFullName = userBuilder.mFullName;
        mId = userBuilder.mId;
        mKind = userBuilder.mKind;
        mLastModified = userBuilder.mLastModified;
        mLastName = userBuilder.mLastName;
        mPermalink = userBuilder.mPermalink;
        mPermalinkUrl = userBuilder.mPermalinkUrl;
        mUri = userBuilder.mUri;
        mUrn = userBuilder.mUrn;
        mUsername = userBuilder.mUsername;
        mCity = userBuilder.mCity;
        mCountryCode = userBuilder.mCountryCode;
    }

    public String getAvatarUrl() {
        return mAvatarUrl;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public String getFullName() {
        return mFullName;
    }

    public int getId() {
        return mId;
    }

    public String getKind() {
        return mKind;
    }

    public String getLastModified() {
        return mLastModified;
    }

    public String getLastName() {
        return mLastName;
    }

    public String getPermalink() {
        return mPermalink;
    }

    public String getPermalinkUrl() {
        return mPermalinkUrl;
    }

    public String getUri() {
        return mUri;
    }

    public String getUrn() {
        return mUrn;
    }

    public String getUsername() {
        return mUsername;
    }

    public String getCity() {
        return mCity;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public static final class UserBuilder {
        private String mAvatarUrl;
        private String mFirstName;
        private String mFullName;
        private int mId;
        private String mKind;
        private String mLastModified;
        private String mLastName;
        private String mPermalink;
        private String mPermalinkUrl;
        private String mUri;
        private String mUrn;
        private String mUsername;
        private String mCity;
        private String mCountryCode;

        public UserBuilder() {
        }

        public UserBuilder AvatarUrl(String avatarUrl) {
            mAvatarUrl = avatarUrl;
            return this;
        }

        public UserBuilder FirstName(String firstName) {
            mFirstName = firstName;
            return this;
        }

        public UserBuilder FullName(String fullName) {
            mFullName = fullName;
            return this;
        }

        public UserBuilder Id(int id) {
            mId = id;
            return this;
        }

        public UserBuilder Kind(String kind) {
            mKind = kind;
            return this;
        }

        public UserBuilder LastModified(String lastModified) {
            mLastModified = lastModified;
            return this;
        }

        public UserBuilder LastName(String lastName) {
            mLastName = lastName;
            return this;
        }

        public UserBuilder Permalink(String permalink) {
            mPermalink = permalink;
            return this;
        }

        public UserBuilder PermalinkUrl(String permalinkUrl) {
            mPermalinkUrl = permalinkUrl;
            return this;
        }

        public UserBuilder Uri(String uri) {
            mUri = uri;
            return this;
        }

        public UserBuilder Urn(String urn) {
            mUrn = urn;
            return this;
        }

        public UserBuilder Username(String username) {
            mUsername = username;
            return this;
        }

        public UserBuilder City(String city) {
            mCity = city;
            return this;
        }

        public UserBuilder CountryCode(String countryCode) {
            mCountryCode = countryCode;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
