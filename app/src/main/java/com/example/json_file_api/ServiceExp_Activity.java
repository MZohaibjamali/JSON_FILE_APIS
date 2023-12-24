package com.example.json_file_api;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ServiceExp_Activity extends AppCompatActivity {
    Button idBtnStopAudioService , idBtnStartAudioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_exp);
        idBtnStartAudioService = findViewById(R.id.idBtnStartAudioService);
        idBtnStopAudioService = findViewById(R.id.idBtnStopAudioService);


        idBtnStartAudioService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(ServiceExp_Activity.this , Service_Class.class));
                Toast.makeText(ServiceExp_Activity.this, "Service is Starting", Toast.LENGTH_SHORT).show();
            }
        });

        idBtnStopAudioService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(ServiceExp_Activity.this, Service_Class.class));
                // displaying toast message on below line.
                Toast.makeText(ServiceExp_Activity.this, "Audio Service Stopped..", Toast.LENGTH_SHORT).show();
            }
        });

    }
}