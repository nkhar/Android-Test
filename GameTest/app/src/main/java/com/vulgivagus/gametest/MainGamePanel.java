package com.vulgivagus.gametest;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Nika on 07.11.2016.
 */

public class MainGamePanel extends SurfaceView implements
        SurfaceHolder.Callback {

    private static final String TAG = MainGamePanel.class.getSimpleName();
    private MainThread thread;
    private Panda panda;

    public MainGamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);

        //create droid and load bitmap
        panda = new Panda(BitmapFactory.decodeResource(getResources(), R.drawable.pandasmall40), 200, 200);
        // create the game loop thread
        thread = new MainThread(getHolder(), this);

        // make the GamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // the surface is created and game loop can be started
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "Surface is being destroyed");
        // tell the thread to shut down and wait for it to finish
        // this is a clean shutdown
        boolean retry = true;
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // try again shutting down the thread
            }
        }
        Log.d(TAG, "Thread was shut down cleanly");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // delegating event handling to the droid
            panda.handleActionDown((int) event.getX(), (int) event.getY());

            // check if in lower part of the screen we exit
            if (event.getY() > getHeight() - 200) {
                thread.setRunning(false);
                ((Activity) getContext()).finish();
            } else {
                Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());
            }
        }
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            // the gestures
            if (panda.isTouched()) {
                // the droid was picked up and is being dragged
                panda.setX((int) event.getX());
                panda.setY((int) event.getY());
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            // touch was released
            if (panda.isTouched()) {
                panda.setTouched(false);
            }
        }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // draw black on screen
        canvas.drawColor(Color.BLACK);
        panda.draw(canvas);
    }

}
