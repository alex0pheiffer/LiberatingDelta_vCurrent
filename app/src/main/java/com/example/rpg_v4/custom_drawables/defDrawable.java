package com.example.rpg_v4.custom_drawables;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class defDrawable extends Drawable {

    private final Paint topPaint;
    private final Paint backPaint;
    private int[] allDef;
    private Double[] defDeg;

    public defDrawable(int def, int defF, int defW, int defL, int defA) {

        //defense is in 100s... 564 == 5.64%
        double sum = def + defF + defW + defL + defA;
        int orgsum = (int)sum;
        double scale = 0;
        allDef = new int[5];
        allDef[0] = def; allDef[1] = defF; allDef[2] = defW; allDef[3] = defL; allDef[4] = defA;
        defDeg = new Double[5];
        int numOfZeros = 0;
        for (int n : allDef) {
            if (n < .05*sum) {
                numOfZeros++;
            }
        }
        if (numOfZeros != 0) {
            Log.d("DEFPIE","initSum = "+sum);
            scale = ((sum*.05)+sum)*.05 / (sum+.05*sum);
            sum = sum + (sum*.05);
            Log.d("DEFPIE", "modSum = "+sum+" scale = "+scale);
        }
        for (int a = 0; a < 5; a++) {
            if (allDef[a] < .05*orgsum) {
                defDeg[a] = scale;
            }
            else {
                defDeg[a] = allDef[a] / sum;
                Log.d("DEFPIE", "b4 numZero...defDeg["+a+"] : "+defDeg[a]);
                if (numOfZeros != 0) {
                    defDeg[a] = defDeg[a]-(scale*(numOfZeros-1));
                }
                Log.d("DEFPIE", "after numZero...defDeg["+a+"] : "+defDeg[a]);
            }
            Log.d("DEFPIE", "defDeg["+a+"] : "+defDeg[a]);
        }

        topPaint= new Paint();
        topPaint.setStyle(Paint.Style.STROKE);
        topPaint.setARGB(255, 255, 255, 255);
        backPaint= new Paint();
        backPaint.setStyle(Paint.Style.STROKE);
        backPaint.setARGB(255, 255, 255, 255);
    }

    @Override
    public void draw(Canvas canvas) {
        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int radius = width/2;
        if (width != height) {
            radius = Math.min(width, height) / 2;
        }
        double totalPerc = 0;
        for (int a =0; a < 5; a++) {
            //draw the line
            double rand = 0;
            while (rand < .2 || rand > .9) {
                rand = Math.random();
            }
            int tempRad = (int)(radius*rand);
            if (totalPerc == 0) {
                //draw first line from center up...for physical def
                canvas.drawLine(width/2, height/2,width/2, height/2 - tempRad, topPaint);
            }
            else {
                canvas.drawLine(width/2, height/2,getCircleCoor(tempRad, width/2, (totalPerc*2*3.1415),true), getCircleCoor(tempRad, height/2, (totalPerc*2*3.1415),false), topPaint);
            }
            //draw the arc
            final RectF oval = new RectF();
            oval.set(width/2 - radius, height/2 - radius, width/2 + radius, height/2 + radius);
            Path myPath = new Path();
            if (defDeg[a] <= .10) {
                myPath.arcTo(oval, -(float) ((totalPerc * 360) + (defDeg[a] * 360 * .2) + 90), -(float) ((defDeg[a]) * 360 - (defDeg[a]) * 360 * .2), true);
            }
            else myPath.arcTo(oval, -(float) ((totalPerc * 360) + (defDeg[a] * 360 * .1) + 90), -(float) ((defDeg[a]) * 360 - (defDeg[a]) * 360 * .1), true);
            canvas.drawPath(myPath,topPaint);

            //update totalPerc
            totalPerc += defDeg[a];
        }
    }

    private int getCircleCoor(int rad, int centercoor, double radians, boolean isX) {
        int coor = 0;
        if (isX) {
            coor = (int)(centercoor+Math.cos(radians)*rad); //(3.1415/2)-   for use if doesnt start at top
        }
        else {
            coor = (int)(centercoor+Math.sin(radians)*rad); //(3.1415/2)-
        }
        return coor;
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