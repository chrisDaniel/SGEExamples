package com.cdaniel.sgeexamples.examples.examples;

import android.view.View;

import com.cdaniel.sgeexamples.examples.manager.ExampleManager;
import com.cdaniel.sgeexamples.examples.manager.Setup_Textures;
import com.cdaniel.simplegameengine.core.Color;
import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.directors_movement.DIR_MoveTo;
import com.cdaniel.simplegameengine.utils.constructs.SimpleColor;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Slide;
import com.cdaniel.simplegameviews.inputcontrols.JoystickSimple;

/**
 * Created by christopher.daniel on 6/19/16.
 */
public class X5_Joystick extends AbstractXample{

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Vars
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Integer floor = null;
    private Integer shape = null;



    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * On Frame
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void onFrame() {

        //Part 1...
        //Show How the Walls/Floor Work
        if (SGE.properties().totalFrames() == 1) {

            super.startListeningToUserActions();

            SGE.director().killAllDirectors();
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(0f).toX(0f).toY(20f).toZ(1f).build());
        }
        if (SGE.properties().totalFrames() == 3) {
            shape = SGE.construct().models().sphere()
                    .x(0f).y(2f).z(0f).size(2f)
                    .color(new SimpleColor(Color.BLUE))
                    .build();
        }
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * User Controls
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void onFingerSlide(float dx, float dy){
        //doNothing;
    }
    @Override
    public void onJoystickControl(Vector joyVector){

        float topSpeed = 8 / 20f;  //8 m/s and we get a joystick update 20 times per second

        float deltaX = joyVector.getEx() * topSpeed;
        float deltaZ = joyVector.getEy() * topSpeed;

        Transform_Slide t =  Transform_Slide.builder().deltaX(deltaX).deltaZ(deltaZ).build();
        SGE.contents().get(shape).applyTransform(t);
    }
}
