package com.example.rpg_v4;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatImageView;

//see the invisible image button test for more information...

public class PolymorphousButton extends AppCompatImageView {
    public PolymorphousButton(Context context) {
        super(context);
        // You need to set DrawingCacheEnabled to true
        super.setDrawingCacheEnabled(true);
    }

    public PolymorphousButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        // You need to set DrawingCacheEnabled to true
        super.setDrawingCacheEnabled(true);
    }

    public PolymorphousButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // You need to set DrawingCacheEnabled to true
        super.setDrawingCacheEnabled(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // Process ACTION_UP and ACTION_DOWN events

        //if (event.getAction()) {

        if (event.getAction() == MotionEvent.ACTION_DOWN)  {

            // For the ACTION_DOWN event, we want to check the color at the touch location
            int touchLocationColor;

            // Get the location of the touch event and cast to an Int
            int xTouchLocation = (int)(event.getX());
            int yTouchLocation = (int)(event.getY());

            try {

                // Get the pixel at the touch location, which means getting the
                // pixel color.

                touchLocationColor = this.getDrawingCache().getPixel(xTouchLocation, yTouchLocation);

            } catch (IllegalArgumentException e) {

                // The IllegalArgumentException error will get thrown only if the
                // location of the touch event was outside the bounds of the view.
                // And in that case, it obviously should be bypassed.

                return false;
            }

            // Return true and accept the touch event if the color of the pixel at the
            // touch location was TRANSPARENT. Otherwise, don't process the event.

            return touchLocationColor != Color.TRANSPARENT;
        }


        if (event.getAction() == MotionEvent.ACTION_UP) {

            // Presumably, by this point, you have determined that the touch event was
            // inside the view bounds and not TRANSPARENT. So you can process the click.

            performClick();

            return true;
        }
        //}

        return false; // Return false for other touch events

    }
}
