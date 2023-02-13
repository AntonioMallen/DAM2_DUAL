package com.example.antoniocanvas;

import androidx.appcompat.app.AppCompatActivity;

public class FlashlightCone {
    private int mX, mY, mRadius;

    public int getmX() {
        return mX;
    }

    public int getmY() {
        return mY;
    }

    public int getmRadius() {
        return mRadius;
    }

    public FlashlightCone(int viewWidth, int viewHeight) {
        mX=viewWidth/2;
        mY=viewHeight/2;

        if(viewHeight>viewWidth){
            mRadius=viewWidth/3;
        }else if(viewHeight<viewWidth){
            mRadius=viewHeight/3;
        }else{
            mRadius=viewWidth/3;
        }
    }

    public void update(int newX, int newY){
        mX=newX;
        mY=newY;
    }


}
