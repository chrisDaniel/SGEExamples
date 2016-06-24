package com.cdaniel.simplegameviews.inputcontrols;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by christopher.daniel on 6/23/16.
 */
public class FingerActivityArea extends View implements Runnable {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float TOUCH_SCALE_FACTOR = 1f;
    
    private float mPreviousX = 0f;
    private float mPreviousY = 0f;
    private float mCurrentX = 0f;
    private float mCurrentY = 0f;
    
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

        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, we are only
        // interested in events where the touch position changed.

        float x = e.getX();
        float y = e.getY();

        switch (e.getAction()) {

            case MotionEvent.ACTION_DOWN:

                mPreviousX = x;
                mPreviousY = y;
                mCurrentX = x;
                mCurrentX = y;

                this.notifyListeners_down(x, y);
                this.thread_startNewSlide();

            case MotionEvent.ACTION_MOVE:

                mCurrentX = x;
                mCurrentX = y;
            
            case MotionEvent.ACTION_UP:
                
                mPreviousX = x;
                mPreviousY = y;
                mCurrentX = x;
                mCurrentX = y;
                
                this.notifyListeners_up(x, y);
                this.thread_stopSlide();
        }

        return true;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * How our Monitoring and listener works
    *
    * Interface: anybody who want to listen just meets the interface
    *
    * Thread: only dispatch slides at predefined interval .... i.e. every 10 milliseconds
    *         dont want to dispatch slides all the freaking time when we can dispatch summaries that the user wont notice
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * The Listener Interface
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    private FingerListener listener;

    public interface FingerListener {
        void onFingerSlide(float dx, float dy);
        void onFingerDown(float x, float y);
        void onFingerUp(float x, float y);
    }
    public void setListener(FingerListener listener) {
        this.listener = listener;
    }

    private void notifyListeners_down(float x, float y){

        if (listener == null) {
            return;
        }
        listener.onFingerDown(x, y);
    }
    private void notifyListeners_up(float x, float y){

        if (listener == null) {
            return;
        }
        listener.onFingerUp(x, y);
    }
    private void notifyListeners_slide() {

        if (listener == null) {
            return;
        }

        float dx = mCurrentX - mPreviousX;
        float dy = mCurrentY - mPreviousY;

        listener.onFingerSlide(dx, dy);

        mPreviousX = mCurrentX;
        mPreviousY = mCurrentY;
    }
    
    
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Managing The Thread
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    private Thread thread = new Thread(this);
    private long loopInterval = 10;

    @Override
    public void run() {

        while (!Thread.interrupted()) {

            //part 1...
            //dispatch the joystick position
            post(new Runnable() {
                public void run() {notifyListeners_slide();}
            });

            //part 2...
            //sleep until we want to monitor this thing again
            try {
                Thread.sleep(loopInterval);
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }
    private void thread_startNewSlide() {

        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }

        thread = new Thread(this);
        thread.start();
    }
    private void thread_stopSlide() {

        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }
    }
}
