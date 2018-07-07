package com.codepath.apps.restclienttemplate;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;


public class ComposeModalFragment extends DialogFragment {
    private EditText tweetBody;
    private Button btnSend;

    public interface SendTweetDialogListener {
        void onSendTweet(String tweetBody);
    }


    public ComposeModalFragment() { }

    public static ComposeModalFragment newInstance(String title) {
        ComposeModalFragment frag = new ComposeModalFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_compose_tweet, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        tweetBody = (EditText) view.findViewById(R.id.etComposeModal);
        btnSend = (Button) view.findViewById(R.id.btnCompose);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTweet();
            }
        });
        // Fetch arguments from bundle and set title
        String title = getArguments().getString("title", "Compose Tweet");
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        tweetBody.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void sendTweet() {
        SendTweetDialogListener listener = (SendTweetDialogListener) getActivity();
        listener.onSendTweet(tweetBody.getText().toString());
        dismiss();
    }

}
