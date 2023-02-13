package com.example.antoniocanvas;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable{

    Context mContext;

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attributeSet) {
        super(context,attributeSet);
        init();
    }

    public GameView(Context context, AttributeSet attributeSet,int entero) {
        super(context,attributeSet,entero);
        init();
    }

    private void init(){
        mContext=getContext();
    }

    public void  pause(){

    }
    public void resume(){

    }

    @Override
    public void run() {

    }
}
