package com.example.antoniocanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Actividad3 extends AppCompatActivity {
/*
    private int width;
    private int height;
    setContentView(R.layout.activity_actividad3);
     FlashlightCone flashlightCone = new FlashlightCone(width, height);
    width=this.getWindowManager().getDefaultDisplay().getWidth();
    height=this.getWindowManager().getDefaultDisplay().getHeight();
*/
    private GameView mGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGameView=new GameView(this);
        mGameView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(mGameView);
    }



    @Override
    protected void onResume() {
        super.onResume();
        mGameView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGameView.pause();
    }

}