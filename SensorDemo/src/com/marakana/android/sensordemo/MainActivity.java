package com.marakana.android.sensordemo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {
	Toast toast;
	TextView output;
	SensorManager sensorManager;
	Sensor sensor;
	long lastUpdate=System.nanoTime();;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// UI
		output = (TextView) findViewById(R.id.output);
		toast = Toast.makeText(this, "SHAKE!!!", Toast.LENGTH_SHORT);

		// Sensors
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
	}

	@Override
	protected void onResume() {
		super.onResume();
		sensorManager.registerListener(this, sensor, SENSOR_DELAY);
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	static final int SENSOR_DELAY = 60 * 1000000; // 5 sec.
	static final int SHAKE_THRESHOLD = 500;

	// --- SensorEventListener callbacks ---
	@Override
	public void onSensorChanged(SensorEvent event) {
		int x = (int) event.values[0];
		int y = (int) event.values[1];
		int z = (int) event.values[2];
		int sum = (int) (x * x + y * y + z * z - Math.pow(
				(double) SensorManager.GRAVITY_EARTH, 2));
		output.setText(String.format("%s: \n%d, \n%d, \n%d\n sum: %d\ndelta: %d ms",
				event.sensor.getName(), x, y, z, sum, (System.nanoTime()-lastUpdate)/1000000));

		if (sum > SHAKE_THRESHOLD) {
			toast.show();
		}
		
		lastUpdate = System.nanoTime();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}
