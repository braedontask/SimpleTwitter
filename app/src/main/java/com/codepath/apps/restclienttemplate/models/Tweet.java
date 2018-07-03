package com.codepath.apps.restclienttemplate.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {
    public String body;
    public long uid;
    public User user;
    public String createdAt;

    // deserialize the JSON data
    public static Tweet fromJSON(JSONObject obj) throws JSONException {
        Tweet tweet = new Tweet();

        // place all of the object data into the tweet model
        tweet.body = obj.getString("text");
        tweet.uid = obj.getLong("id");
        tweet.createdAt = obj.getString("created_at");
        tweet.user = User.fromJSON(obj.getJSONObject("user"));

        return tweet;
    }
}
