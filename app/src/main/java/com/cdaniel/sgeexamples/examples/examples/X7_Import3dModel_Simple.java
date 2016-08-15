package com.cdaniel.sgeexamples.examples.examples;

import android.content.res.Resources;

import com.cdaniel.sgeexamples.examples.manager.ExampleManager;
import com.cdaniel.simplegameengine.core.Color;
import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.directors_movement.DIR_MoveTo;
import com.cdaniel.simplegameengine.plugins.director.directors_strategy.DIR_Orbit;
import com.cdaniel.simplegameengine.utils.constructs.SimpleColor;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Slide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by christopher.daniel on 8/13/16.
 */
public class X7_Import3dModel_Simple extends AbstractXample {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Vars
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Integer shape = null;
    private Color fogColor = new SimpleColor(.3f, .3f, .3f, 1f);

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * On Frame
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void onFrame() {

        //Part 1...
        //Show How the Walls/Floor Work
        if (SGE.properties().totalFrames() == 1) {

            SGE.otherGL().setClearColor(fogColor);
            SGE.director().killAllDirectors();
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(0f).toX(0f).toY(1f).toZ(10f).build());
        }
        if (SGE.properties().totalFrames() == 3) {

            try{
                Resources appResources = ExampleManager.getInstance().getView().getResources();
                InputStream is  = appResources.getAssets().open("models/monkey_face.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                SGE.construct().models().custom(reader).x(0f).y(0f).z(0f).size(2f)
                        .color(new SimpleColor(Color.YELLOW))
                        .build();
            }
            catch (IOException io){

            }
        }
        if (SGE.properties().totalFrames() == 5){
            SGE.construct().models().cube().x(5f).y(2f).z(0f).size(1.5f)
                    .color(new SimpleColor(Color.WHITE))
                    .build();
        }
        if(SGE.properties().totalFrames() == 190) {
            SGE.director().queueDirector(DIR_Orbit.builder().duration(15f).secondsPerRevolution(8f).build());
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
