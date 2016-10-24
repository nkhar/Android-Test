package com.vulgivagus.testapp1;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Nika on 20.09.2016.
 */
public class TitleView extends View {

    private Context myContext;
    private Bitmap titleGraphic;
    private Bitmap playButtonUp;
    private Bitmap playButtonDown;
    private boolean playButtonPressed;
    private int screenW;
    private int screenH;


    public TitleView(Context context) {
        super(context);
        myContext=context;
        titleGraphic = BitmapFactory.decodeResource(getResources(), R.drawable.titlepicture);
        playButtonUp = BitmapFactory.decodeResource(getResources(), R.drawable.playbutton);
        playButtonDown =BitmapFactory.decodeResource(getResources(), R.drawable.play_button_down);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw,
                              int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenW = w;
        screenH = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(titleGraphic, 0, 0, null);  // instead of 0 in x coordinate >>>(screenW-titleGraphic.getWidth())/2
        canvas.drawBitmap(playButtonUp, (screenW - titleGraphic.getWidth()) / 1024, (int) (screenH * 0.6), null);
        if (playButtonPressed) {
            canvas.drawBitmap(playButtonDown, (screenW - titleGraphic.getWidth()) / 1024, (int) (screenH * 0.6), null);
        } else {
            canvas.drawBitmap(playButtonUp, (screenW - titleGraphic.getWidth()) / 1024, (int) (screenH * 0.6), null);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int eventaction = event.getAction();
        int X = (int) event.getX();
        int Y = (int) event.getY();
        switch (eventaction) {
            case MotionEvent.ACTION_DOWN:
                if (X > (screenW - playButtonUp.getWidth()) / 1024 &&
                        (X < ((screenW - playButtonUp.getWidth()) / 1024) + playButtonUp.getWidth()) &&
                        Y > (int) (screenH * 0.6) &&
                        Y < (int) (screenH * 0.6) +
                                playButtonUp.getHeight()) {
                    playButtonPressed = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if(playButtonPressed){
                    Intent gameIntent=new Intent(myContext, GameActivity.class);
                    myContext.startActivity(gameIntent);
                }
                playButtonPressed = false;
                break;
        }
        invalidate();
        return true;
    }
}
