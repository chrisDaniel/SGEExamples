package com.cdaniel.sgeexamples.examples.exampleview;

import android.opengl.GLSurfaceView;

import com.cdaniel.sgeexamples.examples.manager.ExampleManager;
import com.cdaniel.sgeexamples.examples.manager.Setup_SGE;
import com.cdaniel.sgeexamples.examples.manager.Setup_Textures;
import com.cdaniel.simplegameengine.engine.SGE;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 4/10/16.
 */
public class ExampleRenderer implements GLSurfaceView.Renderer {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ExampleRenderer(){

    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Standard GLSurfaceView Screen Update Handlers
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        int newSGEcontext = SGE.initContextGL(gl);
        Setup_Textures.createTextures(gl, ExampleManager.getInstance().getContext());
        Setup_SGE.configureDevTools();
        Setup_SGE.turnOnLights(gl);
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        ExampleManager.getInstance().onFrame();
        SGE.draw(gl);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        Setup_SGE.setScreenSizing(width, height);
    }
}
