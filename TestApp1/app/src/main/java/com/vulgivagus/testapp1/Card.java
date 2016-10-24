package com.vulgivagus.testapp1;

import android.graphics.Bitmap;

/**
 * Created by Nika on 20.09.2016.
 */
public class Card {

    private int id;
    private Bitmap bmp;
    public Card(int newId) {
        id = newId;
    }
    public void setBitmap(Bitmap newBitmap) {
        bmp = newBitmap;
    }
    public Bitmap getBitmap() {
        return bmp;
    }
    public int getId() {
        return id;
    }
}
