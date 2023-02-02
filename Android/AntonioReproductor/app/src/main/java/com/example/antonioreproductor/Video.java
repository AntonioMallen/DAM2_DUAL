package com.example.antonioreproductor;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video extends AppCompatActivity {
    private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        video = (VideoView) findViewById(R.id.videoCaja);
        MediaController mediaController = new MediaController(this);
        video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.videoreproducir));
        video.setMediaController(mediaController);
        video.start();
    }
}