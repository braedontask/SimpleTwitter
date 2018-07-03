package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.codepath.apps.restclienttemplate.models.SampleModel;
import com.codepath.apps.restclienttemplate.models.SampleModelDao;
import com.codepath.oauth.OAuthLoginActionBarActivity;

public class LoginActivity extends OAuthLoginActionBarActivity<TwitterClient> {
    private final int REQUEST_CODE = 20;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		final SampleModel sampleModel = new SampleModel();
		sampleModel.setName("CodePath");

		final SampleModelDao sampleModelDao = ((TwitterApp) getApplicationContext()).getMyDatabase().sampleModelDao();

		AsyncTask<SampleModel, Void, Void> task = new AsyncTask<SampleModel, Void, Void>() {
			@Override
			protected Void doInBackground(SampleModel... sampleModels) {
				sampleModelDao.insertModel(sampleModels);
				return null;
			}
		};
		task.execute(sampleModel);
	}


	// Inflate the menu; this adds items to the action bar if it is present.
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.login, menu);
//		return true;
//	}

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle presses on the action bar items
//        switch (item.getItemId()) {
//            case R.id.miCompose:
//                composeMessage();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

	// OAuth authenticated successfully, launch primary authenticated activity
	// i.e Display application "homepage"
	@Override
	public void onLoginSuccess() {
		Intent i = new Intent(this, TimelineActivity.class);
		startActivity(i);
        // Toast.makeText(this, "Logged In!", Toast.LENGTH_LONG).show();
	}

	// OAuth authentication flow failed, handle the error
	// i.e Display an error dialog or toast
	@Override
	public void onLoginFailure(Exception e) {
		e.printStackTrace();
	}

	// Click handler method for the button used to start OAuth flow
	// Uses the client to initiate OAuth authorization
	// This should be tied to a button used to login
	public void loginToRest(View view) {
		getClient().connect();
	}

//	public void composeMessage(MenuItem mi) {
//	    Log.d("*********", "clicked the create tweet button");
//        Intent intent = new Intent(LoginActivity.this, ComposeActivity.class);
//        startActivityForResult(intent, REQUEST_CODE);
//    }

}