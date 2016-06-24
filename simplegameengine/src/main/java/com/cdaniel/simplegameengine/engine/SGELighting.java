package com.cdaniel.simplegameengine.engine;

import com.cdaniel.simplegameengine.utils.converters.Conv_ToBuffer;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 4/19/16.
 */
public class SGELighting implements SGEPipeline {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables and Construction
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean lightingEnabled = false;

    public SGELighting(){
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Lighting On / Off
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void enableLight(GL10 gl){

        lightingEnabled = true;
        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL10.GL_COLOR_MATERIAL);
    }
    public void disableLight(GL10 gl){

        lightingEnabled = false;
        gl.glDisable(GL10.GL_LIGHTING);
        gl.glDisable(GL10.GL_LIGHT0);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Enable Types of Light
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void enable_fullLighting(GL10 gl){

        if(!lightingEnabled){
            return;
        }

        float light_position[] = { 0f, 256f, 0f, 1f };
        FloatBuffer pos = Conv_ToBuffer.getFloatBuffer(light_position);

        float light_ambient[] = { .2f, .2f, .2f, 1.0f };
        FloatBuffer amb = Conv_ToBuffer.getFloatBuffer(light_ambient);

        float light_diffuse[] = { .7f, .8f, .8f, 1.0f };
        FloatBuffer dif = Conv_ToBuffer.getFloatBuffer(light_diffuse);

        float light_specular[] = { .7f, .7f, .7f, 1.0f };
        FloatBuffer spec = Conv_ToBuffer.getFloatBuffer(light_specular);

        gl.glEnable(GL10.GL_LIGHT0);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, amb);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, dif);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, spec);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, pos);
    }
    public void enable_directionalLight(GL10 gl, float x, float y, float z){

        if(!lightingEnabled){
            return;
        }

        float light_position[] = { x, y, z, 1f };
        FloatBuffer pos = Conv_ToBuffer.getFloatBuffer(light_position);

        float light_ambient[] = { .1f, .1f, .1f, 1.0f };
        FloatBuffer amb = Conv_ToBuffer.getFloatBuffer(light_ambient);

        float light_diffuse[] = { .7f, .8f, .8f, 1.0f };
        FloatBuffer dif = Conv_ToBuffer.getFloatBuffer(light_diffuse);

        float light_specular[] = { .7f, .7f, .7f, 1.0f };
        FloatBuffer spec = Conv_ToBuffer.getFloatBuffer(light_specular);

        gl.glEnable(GL10.GL_LIGHT0);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, amb);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, dif);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, spec);
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, pos);
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Material Properties for Lighting
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void setMaterialProperties(GL10 gl, SGEContentWrapper wrapper){

        if(!lightingEnabled){
            return;
        }

        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, wrapper.getMaterialAmbient());
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, wrapper.getMaterialDiffuse());

        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_EMISSION, wrapper.getMaterialEmmissive());

        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, wrapper.getMaterialSpecular());
        gl.glMaterialf (GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, wrapper.getMatShininess());
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Pipeline Contract
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void contentFreeze(boolean isFrozen){
    }
    @Override
    public void pause(boolean isPaused){
    }
    @Override
    public void deleteEverything(){
    }
}
