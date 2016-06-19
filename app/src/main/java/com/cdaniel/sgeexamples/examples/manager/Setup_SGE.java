package com.cdaniel.sgeexamples.examples.manager;

import com.cdaniel.simplegameengine.engine.SGE;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class Setup_SGE {


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
        SGE.devTools().logging_turnOn();
        SGE.devTools().logging_includeContentDetail(false);
    }
}
