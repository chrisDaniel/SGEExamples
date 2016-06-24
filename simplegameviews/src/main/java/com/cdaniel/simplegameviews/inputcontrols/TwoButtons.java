package com.cdaniel.simplegameviews.inputcontrols;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by christopher.daniel on 6/20/16.
 */
public class TwoButtons extends View{

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    //A variable which defines the button that currently down
    //1 = button1, 2 = button2, 0 is neither
    private int buttonState = 0;

    //Button Position Vars
    private float centerX        = 0;
    private float centerY        = 0;
    private float button1X       = 0;
    private float button1Y       = 0;
    private float button2X       = 0;
    private float button2Y       = 0;

    //Display Components
    private Paint button1Rim;
    private Paint button1Up;
    private Paint button1Down;
    private Paint button1Acc;

    private Paint button2Rim;
    private Paint button2Up;
    private Paint button2Down;
    private Paint button2Acc;


    //Draw Parameters (like defaults)
    private int    param_defaultSize   = 100;
    private int    param_buttonRadius    = 35;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construction
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public TwoButtons(Context context) {
        super(context);
    }

    public TwoButtons(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public TwoButtons(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
        initView();
    }

    protected void initView() {

        //Button 1 ... Red
        button1Rim = new Paint(Paint.ANTI_ALIAS_FLAG);
        button1Rim.setColor(Color.RED);
        button1Rim.setStyle(Paint.Style.FILL);

        button1Up = new Paint(Paint.ANTI_ALIAS_FLAG);
        button1Up.setColor(Color.DKGRAY);
        button1Up.setAlpha(96);
        button1Up.setStyle(Paint.Style.FILL);

        button1Down = new Paint(Paint.ANTI_ALIAS_FLAG);
        button1Down.setColor(Color.BLACK);
        button1Down.setAlpha(96);
        button1Down.setStyle(Paint.Style.FILL);

        button1Acc = new Paint(Paint.ANTI_ALIAS_FLAG);
        button1Acc.setColor(Color.WHITE);
        button1Acc.setAlpha(67);
        button1Acc.setStyle(Paint.Style.FILL);

        //Button 2 ... Green
        button2Rim = new Paint(Paint.ANTI_ALIAS_FLAG);
        button2Rim.setColor(Color.GREEN);
        button2Rim.setStyle(Paint.Style.FILL);

        button2Up = new Paint(Paint.ANTI_ALIAS_FLAG);
        button2Up.setColor(Color.DKGRAY);
        button2Up.setAlpha(96);
        button2Up.setStyle(Paint.Style.FILL);

        button2Down = new Paint(Paint.ANTI_ALIAS_FLAG);
        button2Down.setColor(Color.BLACK);
        button2Down.setAlpha(96);
        button2Down.setStyle(Paint.Style.FILL);

        button2Acc = new Paint(Paint.ANTI_ALIAS_FLAG);
        button2Acc.setColor(Color.WHITE);
        button2Acc.setAlpha(67);
        button2Acc.setStyle(Paint.Style.FILL);
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

        int eachButtonArea = getWidth() / 2;

        button1X = centerX - (eachButtonArea/2);
        button1Y = centerY + centerY*.4f;

        button2X = centerX + (eachButtonArea/2);
        button2Y = centerY - centerY*.4f;
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

        canvas.drawCircle(button1X, button1Y, param_buttonRadius, button1Rim);
        canvas.drawCircle(button2X, button2Y, param_buttonRadius, button2Rim);

        if(buttonState == 1) {
            canvas.drawCircle(button1X, button1Y, param_buttonRadius * .9f, button1Down);
            canvas.drawCircle(button2X, button2Y, param_buttonRadius * .9f, button2Up);
        }
        else if(buttonState == 2) {
            canvas.drawCircle(button1X, button1Y, param_buttonRadius * .9f, button1Up);
            canvas.drawCircle(button2X, button2Y, param_buttonRadius * .9f, button2Down);
        }
        else {
            canvas.drawCircle(button1X, button1Y, param_buttonRadius * .9f, button1Up);
            canvas.drawCircle(button2X, button2Y, param_buttonRadius * .9f, button2Up);
        }

        canvas.drawCircle(button1X, button1Y-(param_buttonRadius/2), param_buttonRadius/2f, button1Acc);
        canvas.drawCircle(button2X, button2Y-(param_buttonRadius/2), param_buttonRadius/2f, button2Acc);
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Touch Handler
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int buttonTouched = onTouch_evaluateButtonPress(event);
        invalidate();

        if (event.getAction() == MotionEvent.ACTION_UP) {

            if(this.buttonState > 0){
                this.notifyListenersUp();
            }
            this.buttonState = 0;
        }
        else if (event.getAction() == MotionEvent.ACTION_DOWN) {

            if(buttonTouched==0){
                //do nothing they pressed nothing
            }
            else{
                buttonState = buttonTouched;
                this.notifyListenersDown();
            }
        }
        else{

            if(buttonTouched == this.buttonState){
                //do nothing ... theyre just holding the thing
            }
            else if(buttonTouched == 0){
                this.notifyListenersUp();
                this.buttonState = 0;
            }
            else{
                buttonState = buttonTouched;
                this.notifyListenersDown();
            }
        }

        return true;
    }

    private int onTouch_evaluateButtonPress(MotionEvent event) {

        float touchX = event.getX();
        float touchY = event.getY();


        //eval button 1
        if(touchX < centerX) {

            double abs = Math.sqrt((touchX - button1X) * (touchX - button1X)
                    + (touchY - button1Y) * (touchY - button1Y));

            if (abs <= this.param_buttonRadius) {
                return 1;
            }
        }
        
        //eval button 2
        if(touchX > centerX) {

            double abs = Math.sqrt((touchX - button2X) * (touchX - button2X)
                    + (touchY - button2Y) * (touchY - button2Y));

            if (abs <= this.param_buttonRadius) {
                return 2;
            }
        }

        //no button depressed
        return 0;
    }




    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * How our Listener works
    *
    * Interface: anybody who want to listen just meets the interface
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    private TwoButtonListener listener;

    public interface TwoButtonListener {
        void onDown(int buttonId);
        void onUp(int buttonId);
    }
    public void setListener(TwoButtonListener listener) {
        this.listener = listener;
    }
    private void notifyListenersUp() {

        if (listener == null) {
            return;
        }

        listener.onUp(buttonState);
    }
    private void notifyListenersDown() {

        if (listener == null) {
            return;
        }

        listener.onDown(buttonState);
    }
}
