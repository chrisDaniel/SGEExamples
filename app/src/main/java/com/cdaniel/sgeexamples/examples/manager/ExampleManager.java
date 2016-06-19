package com.cdaniel.sgeexamples.examples.manager;

import android.content.Context;
import android.view.MotionEvent;

import com.cdaniel.sgeexamples.examples.examples.X1_ShapesTweensDirectors;
import com.cdaniel.sgeexamples.examples.examples.X2_Infrastructure;
import com.cdaniel.sgeexamples.examples.examples.X3_Physics;
import com.cdaniel.sgeexamples.examples.examples.X3_PhysicsPendulum;
import com.cdaniel.sgeexamples.examples.examples.X4_Interactive_Camera;
import com.cdaniel.sgeexamples.examples.examples.X5_FPSLevelPrototype;
import com.cdaniel.sgeexamples.examples.exampleview.ExampleView;
import com.cdaniel.sgeexamples.examples.examples.AbstractXample;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.directors_focus.DIR_PanLeftRight;
import com.cdaniel.simplegameengine.plugins.director.directors_focus.DIR_PanUpDown;
import com.cdaniel.simplegameengine.utils.constants.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by christopher.daniel on 6/18/16.
 */
public class ExampleManager {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Example Ids
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static int EX1_1 = 101;
    public static int EX2_1 = 201;
    public static int EX3_1 = 301;
    public static int EX3_2 = 302;
    public static int EX4_1 = 401;
    public static int EX5_1 = 501;


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Singleton Instance and Constructor
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private static ExampleManager singleton;
    private Interaction_Handler interactionHandler;

    public ExampleManager(){
        this.initializeExamples();
        this.interactionHandler = new Interaction_Handler();
    }

    public static ExampleManager getInstance(){

        if(singleton == null){
            singleton = new ExampleManager();
        }
        return singleton;
    }

    public Interaction_Handler interactHandler(){
        return  interactionHandler;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * State Variables
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Context currContext;
    public void setContext(Context c){this.currContext = c;}
    public Context getContext(){return currContext; }

    private ExampleView view;
    public void setView(ExampleView view){this.view = view; }
    public ExampleView getView(){return this.view; }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Manage Example Classes
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private int activeExampleId = -1;
    private Map<Integer, AbstractXample> examplesMap = new HashMap<>();


    private void initializeExamples(){

        examplesMap.put(EX1_1, new X1_ShapesTweensDirectors());
        examplesMap.put(EX2_1, new X2_Infrastructure());
        examplesMap.put(EX3_1, new X3_Physics());
        examplesMap.put(EX3_2, new X3_PhysicsPendulum());
        examplesMap.put(EX4_1, new X4_Interactive_Camera());
        examplesMap.put(EX5_1, new X5_FPSLevelPrototype());
    }
    public void activateExample(int exampleId) {

        this.activeExampleId = exampleId;
    }
    public AbstractXample getActiveExample(){

        return examplesMap.get(activeExampleId);
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Act on the active Example
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void onFrame(){
        this.examplesMap.get(activeExampleId).onFrame();
    }




    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *
    * Interaction Handler Sub Class
    *
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private class Interaction_Handler {

        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        * Constructor / Variables
        *
        * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public Interaction_Handler() {

        }

        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        * Accept Touch Events
        *
        * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        float mPreviousX = 0f;
        float mPreviousY = 0f;
        float TOUCH_SCALE_FACTOR = 1f;

        public void handleTouch(MotionEvent e) {

            // MotionEvent reports input details from the touch screen
            // and other input controls. In this case, we are only
            // interested in events where the touch position changed.

            float x = e.getX();
            float y = e.getY();

            switch (e.getAction()) {
                case MotionEvent.ACTION_MOVE:

                    float dx = x - mPreviousX;
                    float dy = y - mPreviousY;

                    // reverse direction of rotation above the mid-line
                    if (y > ExampleManager.getInstance().getView().getHeight() / 2) {
                        dx = dx * -1;
                    }

                    // reverse direction of rotation to left of the mid-line
                    if (x < ExampleManager.getInstance().getView().getWidth() / 2) {
                        dy = dy * -1;
                    }

                    dx = dx * TOUCH_SCALE_FACTOR;
                    dy = dy * TOUCH_SCALE_FACTOR;

                    touchMove_pan(dx, dy);
            }

            mPreviousX = x;
            mPreviousY = y;
        }


        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        * Public Handlers
        *
        * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        private void touchMove_pan(float dx, float dy) {

            float full360 = 1200;
            float angle = Constants.PI * (-1 * dx / full360);
            if (angle < Constants.PI / -6f) {
                angle = -1 * Constants.PI / 6f;
            }
            if (angle > Constants.PI / 6f) {
                angle = Constants.PI / 6f;
            }

            SGE.director().queueDirector(DIR_PanLeftRight.builder().left(angle).build());

            float fullUpDown = 600;
            float delta = Constants.PI * (-1 * dy / fullUpDown);
            if (delta < Constants.PI / -6f) {
                delta = -1 * Constants.PI / 6f;
            }
            if (delta > Constants.PI / 6f) {
                delta = Constants.PI / 6f;
            }

            SGE.director().queueDirector(DIR_PanUpDown.builder().up(delta).build());
        }
    }
}
