package com.example.antoniocanvas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Actividad3 extends AppCompatActivity {

    private int width;
    private int height;
    private GameView mGameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad3);
        width=this.getWindowManager().getDefaultDisplay().getWidth();
        height=this.getWindowManager().getDefaultDisplay().getHeight();
        FlashlightCone flashlightCone = new FlashlightCone(width, height);
        mGameView=new GameView(this);
        mGameView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}