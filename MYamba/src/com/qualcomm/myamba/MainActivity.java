package com.qualcomm.myamba;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

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

}
