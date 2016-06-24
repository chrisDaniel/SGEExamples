package com.cdaniel.sgeexamples.examples.manager;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

import com.cdaniel.sgeexamples.examples.examples.X1_ShapesTweensDirectors;
import com.cdaniel.sgeexamples.examples.examples.X2_Infrastructure;
import com.cdaniel.sgeexamples.examples.examples.X3_Physics;
import com.cdaniel.sgeexamples.examples.examples.X3_PhysicsPendulum;
import com.cdaniel.sgeexamples.examples.examples.X4_Interactive_Camera;
import com.cdaniel.sgeexamples.examples.examples.X5_Joystick;
import com.cdaniel.sgeexamples.examples.examples.X6_FPSLevelPrototype;
import com.cdaniel.sgeexamples.examples.exampleview.ExampleView;
import com.cdaniel.sgeexamples.examples.examples.AbstractXample;
import com.cdaniel.simplegameviews.inputcontrols.FingerActivityArea;
import com.cdaniel.simplegameviews.inputcontrols.JoystickSimple;
import com.cdaniel.simplegameviews.inputcontrols.TwoButtons;

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

    public ExampleManager() {
        this.initializeExamples();
    }

    public static ExampleManager getInstance() {

        if (singleton == null) {
            singleton = new ExampleManager();
        }
        return singleton;
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
    * Reference to User Controls
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private JoystickSimple joystick;
    public void setJoystick(JoystickSimple joystick){
        this.joystick = joystick;
    }
    public JoystickSimple getJoystick(){
        return joystick;
    }
    public void showJoystick(){ExampleManager.getInstance().getJoystick().setVisibility(View.VISIBLE);}
    public void hideJoystick(){ExampleManager.getInstance().getJoystick().setVisibility(View.INVISIBLE);}

    private TwoButtons twoButtons;
    public void setTwoButtons(TwoButtons twoButtons){this.twoButtons = twoButtons; }
    public TwoButtons getTwoButtons() { return  twoButtons; }

    private FingerActivityArea fingerActivityArea;
    public void setFingerActivityArea(FingerActivityArea fingerActivityArea){this.fingerActivityArea = fingerActivityArea; }
    public FingerActivityArea getFingerActivityArea() { return  fingerActivityArea; }
}
