package com.qualcomm.myamba;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {
	static final String[] FROM = { YambaApp.C_USER, YambaApp.C_MESSAGE,
			YambaApp.C_CREATED_AT };
	static final int[] TO = { R.id.text_user, R.id.text_message,
			R.id.text_created_at };
	YambaApp yamba;
	SimpleCursorAdapter adapter;
	RefreshReceiver receiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		yamba = (YambaApp) getApplication();

		// Create and setup adapter
		adapter = new SimpleCursorAdapter(this, R.layout.row, yamba.cursor,
				FROM, TO);
		setListAdapter(adapter);

		// Register receiver
		receiver = new RefreshReceiver();
		registerReceiver(receiver, new IntentFilter(
				RefreshService.REFRESH_BRAODCAST));
	}

	// --- Menu callbacks ---

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.item_prefs:
			startActivity(new Intent(this, PrefsActivity.class));
			return true;

		case R.id.item_status_update:
			startActivity(new Intent(this, StatusActivity.class));
			return true;

		case R.id.item_refresh:
			startService(new Intent("com.qualcomm.action.refresh_timeline"));
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	class RefreshReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			adapter.changeCursor(yamba.cursor);
			Log.d("RefreshReceiver", "onReceived");
		}
	}

}
