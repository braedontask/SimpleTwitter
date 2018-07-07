package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    public EditText etBody;
    public TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        etBody = (EditText) findViewById(R.id.etTweetBody);
        tvCount = (TextView) findViewById(R.id.tvCount);
        etBody.addTextChangedListener(etBodyTextWatcher);
        client = TwitterApp.getRestClient(this);
        findViewById(R.id.btnTweetSend).setOnClickListener(new handleOnClick());
    }

    // then we need to have an on click function that gets called
    // this function will put the body of the tweet back into the tweet
    // then the function will use the finish method to return to parent
    private class handleOnClick implements View.OnClickListener {
        public void onClick(View view) {
            publishTweet(etBody.getText().toString());
        }
    }

    public void publishTweet(final String tweetBody) {
        client.sendTweet(tweetBody, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                Log.d("TwitterClient", response.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("TwitterClient", responseString.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    tweet = Tweet.fromJSON(response);
                    Intent data = new Intent();
                    data.putExtra("tweetBody", tweetBody);
                    data.putExtra(Tweet.class.getSimpleName(), Parcels.wrap(tweet));
                    setResult(RESULT_OK, data);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("TwitterClient", responseString);
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("TwitterClient", errorResponse.toString());
                throwable.printStackTrace();
            }
        });
    }

    private final TextWatcher etBodyTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            tvCount.setText(String.valueOf(s.length()));
        }

        public void afterTextChanged(Editable s) {
        }
    };
}
