package com.qualcomm.myamba;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.marakana.android.yamba.clientlib.YambaClient;

public class StatusActivity extends Activity implements OnClickListener {
	Button buttonUpdate;
	EditText editStatus;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_status);

		buttonUpdate = (Button) findViewById(R.id.button_update);
		editStatus = (EditText) findViewById(R.id.edit_status);

		buttonUpdate.setOnClickListener(this);
	}

	// Callback from OnClickListener
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button_update:
			String status = editStatus.getText().toString();

			new UpdateTask().execute(status);

			break;
		case R.id.button_clear:
			editStatus.setText("");
			break;
		}
	}

	class UpdateTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			SharedPreferences prefs = PreferenceManager
					.getDefaultSharedPreferences(getApplication());
			String username = prefs.getString("username", null);
			String password = prefs.getString("password", null);

			// Check if we even have username and password
			if (username == null || username.isEmpty() || password == null
					|| password.isEmpty()) {
				// bounce user to PrefsActivity
				startActivity( new Intent(StatusActivity.this, PrefsActivity.class) );
				
				// if not, tell user that via a Toast
				return "Please login first";
			} else {
				// else
				YambaClient client = new YambaClient(username, password);
				client.updateStatus(params[0]);
				return "Posted " + params[0];
			}
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG)
					.show();
		}

	}
}
