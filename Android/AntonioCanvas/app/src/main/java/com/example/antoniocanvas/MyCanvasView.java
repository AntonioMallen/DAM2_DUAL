package com.example.antoniocanvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class MyCanvasView extends View {

    private Paint mPaint;
    private Path mPath;
    private Bitmap mExtraBitmap;
    private Canvas mExtraCanvas;
    private int mDrawColor;
    private int mBackgroundColor;
    private float mX;
    private float mY;
    private float TOUCH_TOLERANCE=0;
    private Rect mFrame = new Rect();


    public MyCanvasView(Context context) {
        super(context);
        mPath=new Path();
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        mDrawColor=R.color.opaque_yellow;
        mBackgroundColor=R.color.opaque_orange;
    }

    protected void onSizeChanged(int w, int h,int oldw, int oldh){
        mExtraBitmap= Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mExtraCanvas= new Canvas(mExtraBitmap);
        mExtraCanvas.drawColor(mBackgroundColor);
    }

    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                // No need to invalidate because we are not drawing anything.
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();

                break;
            default:
        }
        return true;
    }

    private void touchStart(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }


    private void touchUp() {
        mPath.reset();
    }

    private void touchMove(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            // QuadTo() adds a quadratic bezier from the last point,
            // approaching control point (x1,y1), and ending at (x2,y2).
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            // Reset mX and mY to the last drawn point.
            mX = x;
            mY = y;
            // Save the path in the extra bitmap,
            // which we access through its canvas.
            mExtraCanvas.drawPath(mPath, mPaint);
        }
    }


    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // Draw a frame around the picture.
        canvas.drawRect(mFrame, mPaint);
        // Draw the bitmap that has the saved path.
        canvas.drawBitmap(mExtraBitmap, 0, 0, null);
    }

}
