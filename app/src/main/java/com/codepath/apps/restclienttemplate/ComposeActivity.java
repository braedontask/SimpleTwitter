package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {
    private TwitterClient client;
    public Tweet tweet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        client = TwitterApp.getRestClient(this);
        findViewById(R.id.btnTweetSend).setOnClickListener(new handleOnClick());
//        handleOnClick();
    }

    // then we need to have an on click function that gets called
    // this function will put the body of the tweet back into the tweet
    // then the function will use the finish method to return to parent
    private class handleOnClick implements View.OnClickListener {
        public void onClick(View view) {
            Log.d("*********", "im in the on click");
            EditText etBody = (EditText) findViewById(R.id.etTweetBody);
            publishTweet(etBody.getText().toString());


            // construct and pass tweet back to timeline activity, then push to twitter
        }
    }

    public void publishTweet(final String tweetBody) {
        Log.d("*********", "im gonna publish the tweet");
        client.sendTweet(tweetBody, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("*********", "i shouldnt print");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("*********", "i also shouldnt delete");
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("*********", "i should print here");
                try {
                    Log.d("*******", "about to print the response");
                    Log.d("*******", response.toString());
                    tweet = Tweet.fromJSON(response);
                    Intent data = new Intent();
                    data.putExtra("tweetBody", tweetBody);
                    data.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
                    setResult(RESULT_OK, data);
                    Log.d("*********", "im about to switch back to the timeline");
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("*********", "im a failure");

                Log.d("TwitterClient", responseString);
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("*********", "im also a failure");

                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("*********", "i have dishonered my family");

                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }
        });
    }

//    public void handleOnClick() {
//        Button sendButton = (Button) findViewById(R.id.btnTweetSend);
//        sendButton.setOnClickListener(new Button.OnClickListener() {
//            public void onClick(View v) {
//
//            }
//        });
//    }


}
