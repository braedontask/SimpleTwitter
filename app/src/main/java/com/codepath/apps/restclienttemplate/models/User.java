package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class User {
    public String name;
    public long uid;
    public String screenName;
    public String profileImageUrl;

    public static User fromJSON(JSONObject obj) throws JSONException {
        User user = new User();

        // extract data from object and throw error on failure
        user.name = obj.getString("name");
        user.uid = obj.getLong("id");
        user.screenName = obj.getString("screen_name");
        user.profileImageUrl = obj.getString("profile_image_url");

        return user;
    }
}
