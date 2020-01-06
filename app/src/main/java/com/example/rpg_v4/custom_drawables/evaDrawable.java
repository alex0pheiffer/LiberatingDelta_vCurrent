package com.example.rpg_v4.custom_drawables;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

public class evaDrawable extends Drawable {

    private final Paint topPaint;
    private final Paint backPaint;
    private double evaPerc;

    public evaDrawable(int evaPerc) {

        //evaPerc is in 100s... 564 == 5.64%
        if (evaPerc > 100) {
            throw new RuntimeException("evaPerc cannot be greater than 1000 (10%)");
        }
        this.evaPerc = evaPerc;
        // Set up color and text size
        topPaint= new Paint();
        topPaint.setARGB(255, 255, 255, 255);
        backPaint= new Paint();
        backPaint.setARGB(255, 255, 255, 255);
    }

    @Override
    public void draw(Canvas canvas) {
        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int percDist = (int)(( evaPerc/1000 ) * height);

        //draw top and bottom line
        canvas.drawLine(width/4, 0,3*width/4, 0, topPaint); //top
        canvas.drawLine(width/4, height,3*width/4, height, topPaint); //bottom
        //draw small circle in center of end lines
        canvas.drawCircle(width/2, 0,10, topPaint); //top
        canvas.drawCircle(width/2, height,10, topPaint); //bottom
        // draw line up to the perc mark
        canvas.drawLine(width/2,height,width/2, percDist,topPaint);
        //add special circle to top of perc mark
        canvas.drawCircle(width/2,percDist,10,topPaint);
    }

    @Override
    public void setAlpha(int alpha) {
        // This method is required
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        // This method is required
    }

    @Override
    public int getOpacity() {
        // Must be PixelFormat.UNKNOWN, TRANSLUCENT, TRANSPARENT, or OPAQUE
        return PixelFormat.OPAQUE;
    }
}
