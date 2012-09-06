package com.qualcomm.compass;

import android.app.Activity;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView output;
	SensorManager manager;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        output = (TextView) findViewById(R.id.output);
        
        manager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    
}
