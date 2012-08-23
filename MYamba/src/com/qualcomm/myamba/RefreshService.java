package com.qualcomm.myamba;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.MatrixCursor;
import android.preference.PreferenceManager;
import android.util.Log;

import com.marakana.android.yamba.clientlib.YambaClient;
import com.marakana.android.yamba.clientlib.YambaClient.TimelineProcessor;
import com.marakana.android.yamba.clientlib.YambaClient.TimelineStatus;

public class RefreshService extends IntentService {
	static final String TAG = "RefreshService";
	static final String REFRESH_BRAODCAST = "com.qualcomm.broadcast.new_statuses";

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

	class MyTimelineProcessor implements TimelineProcessor {
		YambaApp yamba;

		@Override
		public boolean isRunnable() {
			return true;
		}

		@Override
		public void onStartProcessingTimeline() {
			Log.d(TAG, "starting to process timeline");

			yamba = (YambaApp) getApplication();
			yamba.cursor = new MatrixCursor(YambaApp.COLUMN_NAMES);
		}

		@Override
		public void onTimelineStatus(TimelineStatus status) {

			yamba.cursor.newRow().add(status.getId())
					.add(status.getCreatedAt()).add(status.getUser())
					.add(status.getMessage());

			Log.d(TAG,
					String.format("%s: %s", status.getUser(),
							status.getMessage()));
		}

		@Override
		public void onEndProcessingTimeline() {
			sendBroadcast( new Intent(REFRESH_BRAODCAST) );
			
			Log.d(TAG,
					"finished processing timeline count: "
							+ yamba.cursor.getCount() );
		}
	}
}
