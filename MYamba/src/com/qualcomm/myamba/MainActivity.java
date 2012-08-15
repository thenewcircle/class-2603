package com.qualcomm.myamba;

import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}


}
