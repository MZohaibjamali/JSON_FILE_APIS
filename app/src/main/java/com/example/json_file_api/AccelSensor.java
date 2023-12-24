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

    TextView meterChecker_Text, meterChecker_PROXIMITY, LightChecker_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accel_sensor);
        meterChecker_Text = findViewById(R.id.meterChecker_Text);
        meterChecker_PROXIMITY = findViewById(R.id.meterChecker_PROXIMITY);
        LightChecker_Text = findViewById(R.id.LightChecker_Text);


        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sensorManager != null) {
            Sensor accelrosensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if (accelrosensor != null) {
                // 1:- Reference of Activity , Selected Sensor , Time of update
                sensorManager.registerListener(this, accelrosensor, SensorManager.SENSOR_DELAY_NORMAL);
            }
        } else {
            Toast.makeText(this, "Sensor Services not detected", Toast.LENGTH_SHORT).show();
        }

        if (sensorManager != null) {

            Sensor proxySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

            if (proxySensor != null) {
                sensorManager.registerListener(this, proxySensor, SensorManager.SENSOR_DELAY_GAME);
            }

        } else {
            Toast.makeText(this, "Sensor Services not detected", Toast.LENGTH_SHORT).show();
        }
        if (sensorManager != null) {
            Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
            if (lightSensor != null) {
                sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_GAME);
            } else {
                Toast.makeText(this, "Light Sensor  not found", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            meterChecker_Text.setText("Values of X: " + event.values[0] + "\nValues of Y: " + event.values[1] + "\nValues of Z: " + event.values[2]);
        } else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {

            meterChecker_PROXIMITY.setText("Value of Event: " + event.values[0]);
            if (event.values[0] > 0) {
                Toast.makeText(this, "Object is far", Toast.LENGTH_SHORT).show();
                meterChecker_PROXIMITY.setText("Away: " + event.values[0]);
            } else {
                Toast.makeText(this, "Object is Near", Toast.LENGTH_SHORT).show();
                meterChecker_PROXIMITY.setText("Near: " + event.values[0]);

            }
        } else if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            LightChecker_Text.setText("Light: " + event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}