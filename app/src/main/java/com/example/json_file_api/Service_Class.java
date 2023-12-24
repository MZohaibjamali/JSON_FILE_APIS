package com.example.json_file_api;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.MediaStore;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class Service_Class extends Service {
    MediaPlayer mp;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp = MediaPlayer.create(Service_Class.this, Settings.System.DEFAULT_RINGTONE_URI);
        mp.isLooping();
        mp.setLooping(true);
        mp.start();

        return super.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        mp.stop();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }
}
