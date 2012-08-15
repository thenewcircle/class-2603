package com.qualcomm.myamba;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

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
			Log.d("Yamba", "onClick'ed with status " + status);
			break;
		case R.id.button_clear:
			editStatus.setText("");
			break;
		}
	}
}
