package com.cdaniel.simplegameengine.engine;

import com.cdaniel.simplegameengine.core.Color;
import com.cdaniel.simplegameengine.utils.constructs.SimpleColor;
import com.cdaniel.simplegameengine.utils.converters.Conv_ToBuffer;

import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 4/19/16.
 */
public class SGEOtherGL implements SGEPipeline {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables and Construction
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public SGEOtherGL(){
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Clear Color (**background**)
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean dirty_clearColor = true;
    private Color clearColor = new SimpleColor(0f, 0f, 0f, 0f);

    public void setClearColor(Color clearColor){
        dirty_clearColor = true;
        this.clearColor = clearColor;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Face Culling
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean dirty_culling = true;
    private boolean cullBack = false;

    public void setCulling(boolean cullBack){
        dirty_culling = true;
        this.cullBack = cullBack;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Fog
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean dirty_fog_enabledProp = false;
    private boolean dirty_fog = false;

    private boolean fogEnabled = false;
    private Integer fogMode = GL10.GL_LINEAR; //todo: figure out why EXP doesnt work
    private Color fogColor = new SimpleColor(.3f, .3f, .3f, 1f);
    private FloatBuffer fogColBuf = Conv_ToBuffer.getFloatBuffer(fogColor.getColor());
    private float fogStartAt = 0f;
    private float fogEndAt = 1f;
    private float fogDensity = 1f;

    public void setFogEnabled(boolean enabled){
        if(enabled==this.fogEnabled){
            return;
        }
        this.fogEnabled=enabled;
        dirty_fog_enabledProp=true;
    }
    public void setFogColor(Color color)        {this.fogColor=color; dirty_fog=true; fogColBuf = Conv_ToBuffer.getFloatBuffer(fogColor.getColor());}
    public void setFogMode(int fogMode)         {this.fogMode=fogMode; dirty_fog=true;}
    public void setFogStartAt(float start)      {this.fogStartAt=start; dirty_fog=true;}
    public void setFogEndAt(float end)          {this.fogEndAt=end; dirty_fog=true;}
    public void setFogDensity(float at)         {this.fogDensity=at; dirty_fog=true;}


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     * Pipeline
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void beforeDraw(GL10 gl) {

        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Clear Color
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        if(dirty_clearColor){

            dirty_clearColor = false;
            gl.glClearColor(this.clearColor.getRed(), this.clearColor.getGreen(), this.clearColor.getBlue(), this.clearColor.getAlpha());
        }

        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Face Culling
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        if(dirty_culling){

            dirty_culling = false;

            if(cullBack){
                gl.glEnable(GL10.GL_CULL_FACE);
                gl.glCullFace(GL10.GL_BACK);
            }
            else{
                gl.glDisable(GL10.GL_CULL_FACE);
            }
        }


        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Fog
         * ~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        if(dirty_fog_enabledProp){

            dirty_fog_enabledProp = false;

            if(fogEnabled){
                gl.glEnable(GL10.GL_FOG);
            }
            else{
                gl.glDisable(GL10.GL_FOG);
            }
        }
        if(dirty_fog && fogEnabled){

            dirty_fog = false;

            if(fogMode != null){gl.glFogf(GL10.GL_FOG_MODE, fogMode);}

            gl.glFogfv(GL10.GL_FOG_COLOR, fogColBuf);
            gl.glFogf(GL10.GL_FOG_START, fogStartAt);
            gl.glFogf(GL10.GL_FOG_END, fogEndAt);
            gl.glFogf(GL10.GL_FOG_DENSITY, fogDensity);
        }
    }

    void afterDraw(GL10 gl) {
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
