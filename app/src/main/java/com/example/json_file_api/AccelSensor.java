package com.example.json_file_api;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AccelSensor extends AppCompatActivity implements SensorEventListener {

    TextView meterChecker_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accel_sensor);
        meterChecker_Text = findViewById(R.id.meterChecker_Text);



        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            Sensor accelrosensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if(accelrosensor != null){
                // 1:- Reference of Activity , Selected Sensor , Time of update
                sensorManager.registerListener(this ,accelrosensor , SensorManager.SENSOR_DELAY_NORMAL);
            }
        } else {
            Toast.makeText(this, "Sensor Services not detected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() ==  Sensor.TYPE_ACCELEROMETER){
            meterChecker_Text.setText("Values of X: " + event.values[0] + "\nValues of Y: " + event.values[1]  + "\nValues of Z: " + event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}