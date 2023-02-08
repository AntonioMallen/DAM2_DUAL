package com.example.antoniocanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

public class MyCanvasView extends View {

    Paint mPaint;
    Path mPath;
    Bitmap mExtraBitmap;


    public MyCanvasView(Context context) {
        super(context);
    }
}
