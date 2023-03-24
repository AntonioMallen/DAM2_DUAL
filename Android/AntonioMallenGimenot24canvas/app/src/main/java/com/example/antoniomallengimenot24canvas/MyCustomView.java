package com.example.antoniomallengimenot24canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

public class MyCustomView extends View {

    private Paint mPaint;
    private Path mPath;
    private Bitmap mExtraBitmap;
    private Canvas mExtraCanvas;
    private float mX;
    private float mY;
    private float TOUCH_TOLERANCE=0;
    private Rect mFrame = new Rect();


    public MyCustomView(Context context) {
        super(context);
        mPath=new Path();
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(12);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

    }

    protected void onSizeChanged(int w, int h,int oldw, int oldh){
        mExtraBitmap= Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mExtraCanvas= new Canvas(mExtraBitmap);
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

    public void set(float tamano,String colorElegido){
        mPaint.setStrokeWidth(tamano);
        switch(colorElegido){
            case "Negro": {
                mPaint.setColor(Color.BLACK);
                break;
            }
            case "Azul": {

                mPaint.setColor(Color.BLUE);
                break;
            }
            case "Rojo": {
                mPaint.setColor(Color.RED);
                break;
            }
            case "Verde": {
                mPaint.setColor(Color.GREEN);
                break;
            }
            case "Blanco": {
                mPaint.setColor(Color.WHITE);
                break;
            }
            default:
                /*
                Realmente la variable colorElegido se crea con el color negro
                por defecto y si no se elige uno se mandara este, pero por si acaso
                en el dafult le pongo tambien el negro.
                 */
                mPaint.setColor(Color.BLACK);
        }
    }

    public void reset(){
        mExtraCanvas.drawColor(Color.WHITE);
    }

}
