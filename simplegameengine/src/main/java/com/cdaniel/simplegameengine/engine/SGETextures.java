package com.cdaniel.simplegameengine.engine;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 4/19/16.
 */
public class SGETextures implements SGEPipeline {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables and Construction
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private int[] textures;
    private int   currTextureSlot;

    public SGETextures(){
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Textures Up
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void initializeTexturesPlugin(GL10 gl, int numberOfTextures){

        currTextureSlot = -1;
        textures = new int[numberOfTextures];
        gl.glGenTextures(textures.length, textures, 0);

    }
    public int loadTexture(GL10 gl, Bitmap fullTextureBitmap) {

        currTextureSlot++;
        Bitmap bitmap = fullTextureBitmap; //Bitmap.createScaledBitmap(fullTextureBitmap, 512, 512, false);

        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[currTextureSlot]);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_LINEAR);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

        bitmap.recycle();
        return currTextureSlot+1;
    }

    void setDrawTexture(int textureId, GL10 gl){

        gl.glEnable(GL10.GL_TEXTURE_2D);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
    }
    void clearDrawTexture(GL10 gl){
        gl.glDisable(GL10.GL_TEXTURE_2D);
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Pipeline Contract
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean contentFreezeInPlace = false;

    @Override
    public void contentFreeze(boolean isFrozen){
        contentFreezeInPlace = isFrozen;
    }
    @Override
    public void pause(boolean isPaused){
    }
    @Override
    public void deleteEverything(){
    }
}
