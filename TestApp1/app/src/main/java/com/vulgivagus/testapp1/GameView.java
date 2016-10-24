package com.vulgivagus.testapp1;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Nika on 20.09.2016.
 */
public class GameView extends View {
    public GameView(Context context){
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {

    }

    public boolean onTouchEvent(MotionEvent event){

        int eventaction = event.getAction();
        int X = (int)event.getX();
        int Y = (int)event.getY();
        switch (eventaction ) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }
}
