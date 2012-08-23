package com.qualcomm.myamba;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClient.TimelineProcessor;
import com.marakana.android.yamba.clientlib.YambaClient.TimelineStatus;

public class RefreshService extends IntentService {
	static final String TAG = "RefreshService";
	
	public RefreshService() {
		super(TAG);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "onCreated");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroyed");
	}

	@Override
	public void onHandleIntent(Intent intent) {
		Log.d(TAG, "onStarted");

		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(getApplication());
		String username = prefs.getString("username", null);
		String password = prefs.getString("password", null);

		YambaClient client = new YambaClient(username, password);

		client.fetchFriendsTimeline(new MyTimelineProcessor());
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	class MyTimelineProcessor implements TimelineProcessor {

		@Override
		public boolean isRunnable() {
			return true;
		}

		@Override
		public void onStartProcessingTimeline() {
			Log.d(TAG, "starting to process timeline");
		}

		@Override
		public void onTimelineStatus(TimelineStatus status) {
			Log.d(TAG,
					String.format("%s: %s", status.getUser(),
							status.getMessage()));
		}

		@Override
		public void onEndProcessingTimeline() {
			Log.d(TAG, "finished processing timeline");
		}
	}
}
