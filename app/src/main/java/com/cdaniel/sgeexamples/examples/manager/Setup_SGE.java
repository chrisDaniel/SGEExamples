package com.cdaniel.sgeexamples.examples.manager;

import com.cdaniel.simplegameengine.core.SimpleGameEngine;
import com.cdaniel.simplegameengine.engine.SGE;

import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class Setup_SGE {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Screen Size
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void setScreenSizing(int width, int height){

        HashMap<String, Object> props = new HashMap<>();
        props.put(SimpleGameEngine.SGE_PIXEL_HEIGHT, height);
        props.put(SimpleGameEngine.SGE_PIXEL_WIDTH, width);
        SGE.properties().apply(props);
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Lights / Fog / Background
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void turnOnLights(GL10 gl){
        SGE.lighting().enableLight(gl);
        SGE.lighting().enable_fullLighting(gl);
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Dev Tools
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void configureDevTools(){
        //SGE.devTools().logging_turnOn();
        //SGE.devTools().logging_includeContentDetail(true);
    }
}
