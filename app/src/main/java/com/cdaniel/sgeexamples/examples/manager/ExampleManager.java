package com.cdaniel.sgeexamples.examples.manager;

import android.content.Context;
import android.view.MotionEvent;

import com.cdaniel.sgeexamples.examples.examples.X1_ShapesTweensDirectors;
import com.cdaniel.sgeexamples.examples.examples.X2_Infrastructure;
import com.cdaniel.sgeexamples.examples.examples.X3_Physics;
import com.cdaniel.sgeexamples.examples.examples.X3_PhysicsPendulum;
import com.cdaniel.sgeexamples.examples.examples.X4_Interactive_Camera;
import com.cdaniel.sgeexamples.examples.examples.X5_Joystick;
import com.cdaniel.sgeexamples.examples.examples.X6_FPSLevelPrototype;
import com.cdaniel.sgeexamples.examples.exampleview.ExampleView;
import com.cdaniel.sgeexamples.examples.examples.AbstractXample;
import com.cdaniel.simplegameviews.inputcontrols.JoystickSimple;

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
    public static int EX6_1 = 601;


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Singleton Instance and Constructor
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private static ExampleManager singleton;
    private Interaction_Handler interactionHandler;

    public ExampleManager() {
        this.initializeExamples();
        this.interactionHandler = new Interaction_Handler();
    }

    public static ExampleManager getInstance() {

        if (singleton == null) {
            singleton = new ExampleManager();
        }
        return singleton;
    }

    public Interaction_Handler interactHandler() {
        return interactionHandler;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * State Variables
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Context currContext;

    public void setContext(Context c) {
        this.currContext = c;
    }

    public Context getContext() {
        return currContext;
    }

    private ExampleView view;

    public void setView(ExampleView view) {
        this.view = view;
    }

    public ExampleView getView() {
        return this.view;
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Manage Example Classes
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private int activeExampleId = -1;
    private Map<Integer, AbstractXample> examplesMap = new HashMap<>();


    private void initializeExamples() {

        examplesMap.put(EX1_1, new X1_ShapesTweensDirectors());
        examplesMap.put(EX2_1, new X2_Infrastructure());
        examplesMap.put(EX3_1, new X3_Physics());
        examplesMap.put(EX3_2, new X3_PhysicsPendulum());
        examplesMap.put(EX4_1, new X4_Interactive_Camera());
        examplesMap.put(EX5_1, new X5_Joystick());
        examplesMap.put(EX6_1, new X6_FPSLevelPrototype());
    }

    public void activateExample(int exampleId) {

        this.activeExampleId = exampleId;
    }

    public AbstractXample getActiveExample() {

        return examplesMap.get(activeExampleId);
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Act on the active Example
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void onFrame() {
        this.examplesMap.get(activeExampleId).onFrame();
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Handle User Controls
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private JoystickSimple joystick;
    public void setJoystick(JoystickSimple joystick){
        this.joystick = joystick;
    }
    public JoystickSimple getJoystick(){
        return joystick;
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *
    * Interaction Handler Sub Class
    *
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public class Interaction_Handler {

        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        * Constructor / Variables
        *
        * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        Interaction_Handler() {

        }

        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        * Accept Touch Events
        *
        * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        private float mPreviousX = 0f;
        private float mPreviousY = 0f;
        private float TOUCH_SCALE_FACTOR = 1f;

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

                    ExampleManager.getInstance().getActiveExample().handleUserAction_move(dx, dy);
            }

            mPreviousX = x;
            mPreviousY = y;
        }
    }
}
