package com.example.android.javadevlagos;

/**
 * Created by tosin1 on 8/21/2017.
 */

public class Developers {

    /** Github Username*/
    private String mUsername;

    /** Github Profile Url*/
    private String mProfileUrl;

    /** Github Image Url*/
    private  String mImageUrl;

    /**
     * Constructs a new {@link Developers} object.
     *
     * @param username is the username of the developer
     * @param profile is the url to he developers Github Profile
     *he profile picture of the developer on Github* @param url is the website URL to find more details about the earthquake
     */
    public Developers(String username, String profile, String image) {
        mUsername = username;
        mProfileUrl = profile;
        mImageUrl = image;
    }

    /** getter functions to return the various values of the params*/

    public String getmUsername() { return mUsername; }

    public String getmProfileUrl() { return mProfileUrl; }

    public String getmImageUrl() { return mImageUrl; }
}
