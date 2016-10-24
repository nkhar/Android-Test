package com.vulgivagus.testapp1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Nika on 19.09.2016.
 */
public class MyView extends View {

    private Paint redPaint;
    private int circleX;
    private int circleY;
    private float radius;

    public MyView(Context context) {
        super(context);
        redPaint = new Paint();
        redPaint.setAntiAlias(true);
        redPaint.setColor(Color.RED);
        circleX = 550;
        circleY = 800;
        radius = 200;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(circleX, circleY, radius,
                redPaint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();
        int X = (int) event.getX();
        int Y = (int) event.getY();
        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                circleX = X;
                circleY = Y;
                break;
        }
        invalidate();
        return true;
    }
}
