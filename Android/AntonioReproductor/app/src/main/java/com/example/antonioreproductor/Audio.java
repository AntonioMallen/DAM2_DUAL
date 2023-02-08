package com.example.antonioreproductor;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;

import java.io.IOException;

public class Audio extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private SeekBar seekbar;
    private Handler myHandler=new Handler();
    private boolean loop=false;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        mediaPlayer = MediaPlayer.create(this, R.raw.audioreproducir);
        mediaPlayer.setLooping(loop);
    }

    public void play(View view){

            mediaPlayer.start();
            seekbar.setProgress(time);

            myHandler.postDelayed(actualizarBarra,100);
    }

    public void pausa(View view ){

        mediaPlayer.pause();
    }
    public void stop(View view){
        mediaPlayer.stop();
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loop (View view){
        mediaPlayer.setLooping((!loop));
    }

    private Runnable actualizarBarra = new Runnable() {
        @Override
        public void run() {
            time = mediaPlayer.getCurrentPosition();
            seekbar.setProgress((int)time);
            myHandler.postDelayed(this,1);
        }
    };
}

