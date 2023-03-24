package com.example.antoniomallengimenot24canvas2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Canvas mCanvas;
    private Paint mPaint= new Paint();
    private Paint mPaintText=new Paint();
    private Bitmap mBitmap;
    private ImageView mImageView;
    Rect mRect=new Rect();
    Rect mBounds=new Rect();
    private final  int OFFSET=120;
    private int mOffset =OFFSET;
    private final int MULTIPLIER = 100;
    private int mColorBackground, mColorRectangle, mColorAccent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mColorAccent = ResourcesCompat.getColor(getResources(),
                R.color.teal_700, null);
        mPaintText.setColor(Color.GREEN);
        mPaintText.setTextSize(70);
        mImageView=(ImageView) findViewById(R.id.imageView2);


    }


    public void drawSomething(View view){
        int width=view.getWidth();
        int height=view.getHeight();

        if(OFFSET==mOffset) {
            mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);
            mCanvas=new Canvas(mBitmap);
            mCanvas.drawColor(Color.GRAY);
            mCanvas.drawText(getString(R.string.keep_taping), 100, 100, mPaintText);
            mOffset += OFFSET;
        }else{
            if(mOffset< (width/2) && mOffset< (height/2)){
                mPaint.setColor(mColorRectangle - MULTIPLIER*mOffset);
                mRect.set(
                        mOffset, mOffset, width - mOffset,height - mOffset);
                mCanvas.drawRect(mRect, mPaint);
                mOffset += OFFSET;

            }else {
                mPaint.setColor(mColorAccent);
                mCanvas.drawCircle((width/2), (height/2), (width/2) / 3, mPaint);
                String text = getString(R.string.done);
                mPaintText.getTextBounds(text, 0, text.length(), mBounds);
                int x = (width/2) - mBounds.centerX();
                int y = (height/2) - mBounds.centerY();
                mCanvas.drawText(text, x, y, mPaintText);
            }

        }
        view.invalidate();
    }
}