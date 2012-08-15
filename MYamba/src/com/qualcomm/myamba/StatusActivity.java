package com.qualcomm.myamba;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_status, menu);
		return true;
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
			YambaClient client = new YambaClient("student", "password");
			client.updateStatus(params[0]);
			return "Posted " + params[0];
		}

		@Override
		protected void onPostExecute(String result) {
			Toast.makeText(StatusActivity.this, result, Toast.LENGTH_LONG).show();
		}

	}
}
