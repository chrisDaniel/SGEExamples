package com.cdaniel.sgeexamples.examples.examples;

import com.cdaniel.sgeexamples.examples.manager.ExampleManager;
import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.directors_focus.DIR_PanLeftRight;
import com.cdaniel.simplegameengine.plugins.director.directors_focus.DIR_PanUpDown;
import com.cdaniel.simplegameengine.utils.constants.Constants;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Slide;
import com.cdaniel.simplegameviews.inputcontrols.FingerActivityArea;
import com.cdaniel.simplegameviews.inputcontrols.JoystickSimple;
import com.cdaniel.simplegameviews.inputcontrols.TwoButtons;

/**
 * Created by christopher.daniel on 6/18/16.
 */
public abstract class AbstractXample implements JoystickSimple.JoystickListener, FingerActivityArea.FingerListener, TwoButtons.TwoButtonListener {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * The main Draw Method
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public abstract void onFrame();


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Set Up Listeners
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void startListeningToUserActions(){
        ExampleManager.getInstance().getJoystick().setListener(this,50);
        ExampleManager.getInstance().getFingerActivityArea().setListener(this);
        ExampleManager.getInstance().getTwoButtons().setListener(this);
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Handle User Actions - Finger Swipes
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/


    @Override
    public void onFingerDown(float x, float y){}
    @Override
    public void onFingerUp(float x, float y){}

    @Override
    public void onFingerSlide(float dx, float dy){

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


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Handle User Actions - Finger Swipes
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void onJoystickControl(Vector joyVector){
        //do nothing
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Handle User Actions - Finger Swipes
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void buttonDown(int buttonId){

    }
    @Override
    public void buttonUp(int buttonId){

    }
}
