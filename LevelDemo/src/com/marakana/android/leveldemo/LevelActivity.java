package com.marakana.android.leveldemo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class LevelActivity extends Activity implements SensorEventListener {
	SensorManager sensorManager;
	Sensor sensor;
	LevelView level;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// UI stuff
		setContentView(R.layout.activity_level);
		level = (LevelView) findViewById(R.id.levelView);

		// Sensor stuff
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
	}

	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, sensor,
				SensorManager.SENSOR_DELAY_UI);
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	// --- SensorEventListener callbacks ---
	@Override
	public void onSensorChanged(SensorEvent event) {
		level.setOrientation(event.values);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

}
