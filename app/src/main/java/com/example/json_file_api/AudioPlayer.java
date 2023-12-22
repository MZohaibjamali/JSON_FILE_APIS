package com.example.json_file_api;


import androidx.appcompat.app.AppCompatActivity;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.IOException;

public class AudioPlayer extends AppCompatActivity {
    Button playBtn, pauseBtn, stopBtn;
    MediaPlayer mediaPlayer;
    VideoView videoView;
    ProgressBar progress_bar_Video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);

        playBtn = findViewById(R.id.playBtn);
        pauseBtn = findViewById(R.id.pauseBtn);
        stopBtn = findViewById(R.id.stopBtn);
        progress_bar_Video = findViewById(R.id.progress_bar_Video);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        String onlinePathAudio = "https://onlinetestcase.com/wp-content/uploads/2023/06/1-MB-MP3.mp3";
        Uri onlineUri = Uri.parse(onlinePathAudio);

        String onlinePathVideo = "https://flutter.github.io/assets-for-api-docs/assets/videos/butterfly.mp4";

        Uri uri = Uri.parse(onlinePathVideo);


        // Video view and Controller
        VideoView video = (VideoView) findViewById(R.id.videoView);
        video.setVideoURI(uri);

        MediaController mediaController = new MediaController(AudioPlayer.this);
        mediaController.setAnchorView(video);
        mediaController.setMediaPlayer(video);
        video.setMediaController(mediaController);
        video.setOnPreparedListener(mediaPlayer -> {
            // Hide the progress bar when the video is prepared
            progress_bar_Video.setVisibility(View.GONE);
        });
        video.start();
        progress_bar_Video.setVisibility(View.VISIBLE);

        try {
            mediaPlayer.setDataSource(AudioPlayer.this, onlineUri);
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });
            mediaPlayer.prepareAsync(); // Use prepareAsync for asynchronous preparation
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Staring audio implementation
        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                    mediaPlayer.seekTo(0);
                }
            }
        });

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                } else {
                    Toast.makeText(AudioPlayer.this, "Already Starting", Toast.LENGTH_SHORT).show();
                }
            }
        });

        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);

                }
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
