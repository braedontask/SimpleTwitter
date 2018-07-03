package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class ComposeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        findViewById(R.id.btnTweetSend).setOnClickListener(new handleOnClick());
//        handleOnClick();
    }

    // then we need to have an on click function that gets called
    // this function will put the body of the tweet back into the tweet
    // then the function will use the finish method to return to parent
    private class handleOnClick implements View.OnClickListener {
        public void onClick(View view) {
            EditText etBody = (EditText) findViewById(R.id.etTweetBody);
            // construct and pass tweet back to timeline activity
            Intent data = new Intent();
            data.putExtra("tweetBody", etBody.getText().toString());
            setResult(RESULT_OK, data);
            finish();
        }
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
