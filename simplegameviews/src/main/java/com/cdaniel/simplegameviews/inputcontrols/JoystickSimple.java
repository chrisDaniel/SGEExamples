package com.cdaniel.simplegameviews.inputcontrols;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VectorMath;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;

/**
 * Created by christopher.daniel on 6/19/16.
 */
public class JoystickSimple extends View implements Runnable{

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    // Constants
    public final static long DEFAULT_LOOP_INTERVAL = 100;

    //JoyStick Position Vars
    private float centerX        = 0;
    private float centerY        = 0;
    private float touchX         = 0;
    private float touchY         = 0;
    private float joystickRadius = 0;
    private float buttonRadius   = 0;

    //Display Components
    private Paint mainCircle;
    private Paint button;
    private Paint xAxis;
    private Paint yAxis;

    //Draw Parameters (like defaults)
    private int    param_defaultSize   = 100;
    private double param_buttonRatio   = .25;
    private double param_moveAreaRatio = .75;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construction
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public JoystickSimple(Context context) {
        super(context);
    }

    public JoystickSimple(Context context, AttributeSet attrs) {
        super(context, attrs);
        initJoystickView();
    }
    public JoystickSimple(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
        initJoystickView();
    }

    protected void initJoystickView() {
        mainCircle = new Paint(Paint.ANTI_ALIAS_FLAG);
        mainCircle.setColor(Color.WHITE);
        mainCircle.setStyle(Paint.Style.FILL_AND_STROKE);

        yAxis = new Paint();
        yAxis.setStrokeWidth(2);
        yAxis.setColor(Color.BLACK);

        xAxis = new Paint();
        xAxis.setStrokeWidth(2);
        xAxis.setColor(Color.BLACK);

        button = new Paint(Paint.ANTI_ALIAS_FLAG);
        button.setColor(Color.RED);
        button.setStyle(Paint.Style.FILL);
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

        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        touchX =  getWidth() / 2;
        touchY =  getHeight() / 2;

        int maxRadius  = Math.min(xNew, yNew) / 2;
        buttonRadius   = (float) (maxRadius * this.param_buttonRatio);
        joystickRadius = (float) (maxRadius * this.param_moveAreaRatio);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int d = Math.min(measure(widthMeasureSpec), measure(heightMeasureSpec));
        setMeasuredDimension(d, d);
    }
    private int measure(int measureSpec) {

        // Is the Measurement Specified?
        // If not return the default Parameter
        if (MeasureSpec.getMode(measureSpec) == MeasureSpec.UNSPECIFIED) {
            return this.param_defaultSize;
        }
        else {
            return MeasureSpec.getSize(measureSpec);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        // full circle
        canvas.drawCircle(centerX, centerY, joystickRadius, mainCircle);

        // axis lines
        canvas.drawLine(centerX, centerY + joystickRadius, centerX, centerY - joystickRadius, yAxis);
        canvas.drawLine(centerX - joystickRadius, centerY, centerX + joystickRadius, centerY, xAxis);

        // the control button
        canvas.drawCircle(touchX, touchY, buttonRadius, button);
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Touch Handler
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        onTouch_setTouchPoint(event);
        invalidate();

        if (event.getAction() == MotionEvent.ACTION_UP) {
            onTouch_handleRelease();
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            onTouch_handleEngage();
        }

        return true;
    }

    private void onTouch_setTouchPoint(MotionEvent event) {

        //Step 1...
        //Update the Touch Point
        touchX = event.getX();
        touchY = event.getY();

        //Step 2...
        //Keep the touch point inside the allowed boundary
        double abs = Math.sqrt((touchX - centerX) * (touchX - centerX)
                + (touchY - centerY) * (touchY - centerY));
        if (abs > joystickRadius) {
            touchX = (float) ( (touchX - centerX) * joystickRadius / abs + centerX);
            touchY = (float) ( (touchY - centerY) * joystickRadius / abs + centerY);
        }
    }
    private void onTouch_handleRelease() {
        touchX = centerX;
        touchY = centerY;

        thread_stop();
    }
    private void onTouch_handleEngage(){
        thread_startNew();
    }




    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * How our Monitoring and listener works
    *
    * Interface: anybody who want to listen just meets the interface
    *
    * Thread: only monitor this component at predefined interval .... i.e. every 100 milliseconds
    *         dont want to dispatch move updates all the freaking time when we can dispatch summaries that the user wont notice
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * The Listener Interface
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    private JoystickListener listener;

    public interface JoystickListener {
        void onJoystickControl(Vector joyVector);
    }
    public void setListener(JoystickListener listener, long repeatInterval) {
        this.listener = listener;
        this.loopInterval = repeatInterval;
    }
    private void notifyListeners() {

        if (listener == null) {
            return;
        }

        Vector joyVector = new SimpleVector(0f, 0f, 0f, touchX-centerX, centerY-touchY, 0f);
        Vector normalizedJoy = Calc_VectorMath.normalizeVector(joyVector);

        float maximumJoyMagnitude = joystickRadius;
        float normalizedMagnitude = Calc_VectorMath.magnitude(joyVector) / maximumJoyMagnitude;

        joyVector = Calc_VectorMath.scaleVector(normalizedJoy, normalizedMagnitude);
        listener.onJoystickControl(joyVector);
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Managing The Thread
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
    private Thread thread = new Thread(this);
    private long loopInterval = DEFAULT_LOOP_INTERVAL;

    @Override
    public void run() {

        while (!Thread.interrupted()) {

            //part 1...
            //dispatch the joystick position
            post(new Runnable() {
                public void run() {notifyListeners();}
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
    private void thread_startNew() {

        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }

        thread = new Thread(this);
        thread.start();
    }
    private void thread_stop() {

        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }
    }
}
