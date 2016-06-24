package com.cdaniel.simplegameviews.inputcontrols;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by christopher.daniel on 6/23/16.
 */
public class FingerActivityArea extends View{

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float touchX = 0f;
    private float touchY = 0f;
    
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construction
    *
    * *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public FingerActivityArea(Context context) {
        super(context);
    }

    public FingerActivityArea(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public FingerActivityArea(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * "On" Handlers
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld) {

        super.onSizeChanged(xNew, yNew, xOld, yOld);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int w = MeasureSpec.getSize(widthMeasureSpec);
        int h = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(w, h);
    }
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Touch Handler
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean onTouchEvent(MotionEvent e) {

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {

            case MotionEvent.ACTION_DOWN:

                touchX = x;
                touchY = y;

                this.notifyListeners_down();

            case MotionEvent.ACTION_MOVE:

                this.notifyListeners_slide(x, y);

                touchX = x;
                touchY = y;
            
            case MotionEvent.ACTION_UP:
                
                touchX = x;
                touchY = y;
                
                this.notifyListeners_up();
        }

        return true;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * How our Monitoring and listener works
    *
    * Interface: anybody who want to listen just meets the interface
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * The Listener Interface
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    private FingerListener listener;
    private float slideSensitivity = .7f;

    public interface FingerListener {
        void onFingerSlide(float dx, float dy);
        void onFingerDown(float x, float y);
        void onFingerUp(float x, float y);
    }
    public void setListener(FingerListener listener) {
        this.listener = listener;
        slideSensitivity = 1f;
    }
    public void setListener(FingerListener listener, float sensitivity){
        this.listener = listener;
        slideSensitivity = sensitivity;
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Notifications
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    private void notifyListeners_down(){

        if (listener == null) {
            return;
        }
        listener.onFingerDown(touchX, touchY);
    }
    private void notifyListeners_up(){

        if (listener == null) {
            return;
        }
        listener.onFingerUp(touchY, touchX);
    }
    private void notifyListeners_slide(float x, float y) {

        if (listener == null) {
            return;
        }

        float dx = x - touchX;
        float dy = y - touchY;

        // reverse direction of rotation above the mid-line
        if (y > getHeight() / 2) {
        //     dy = dy * -1;
        }

        // reverse direction of rotation to left of the mid-line
        if (x < getWidth() / 2) {
        //     dx = dx * -1;
        }

        dx = dx * slideSensitivity;
        dy = dy * slideSensitivity;

        listener.onFingerSlide(dx, dy);
    }
}
