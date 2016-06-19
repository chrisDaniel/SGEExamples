package com.cdaniel.sgeexamples.examples.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cdaniel.sgeexamples.examples.exampleview.ExampleView;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.sgeexamples.R;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class Setup_Textures {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Textures
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static Integer texture1;
    public static Integer texture2;
    public static Integer texture3;
    public static Integer texture4;

    public static Integer texture_birchwood;
    public static Integer texture_brickwall;
    public static Integer texture_cartoonBrickwall;
    public static Integer texture_orangeShine;
    public static Integer texture_metalPanel;
    public static Integer texture_rope;

    public static Integer tile_black;
    public static Integer tile_blueMetal;
    public static Integer tile_white;



    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Create the Textures in SGE
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static void createTextures(GL10 gl, Context context){

        SGE.textures().initializeTexturesPlugin(gl, 30);

        
        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Planets
         *~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture_earth);
        texture1 = SGE.textures().loadTexture(gl, bitmap);
        bitmap.recycle();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture_moon);
        texture2 = SGE.textures().loadTexture(gl, bitmap);
        bitmap.recycle();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture_sun);
        texture3 = SGE.textures().loadTexture(gl, bitmap);
        bitmap.recycle();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture_jupiter);
        texture4 = SGE.textures().loadTexture(gl, bitmap);
        bitmap.recycle();


        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Bricks / Wood / etc
         *~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture_birchwood);
        texture_birchwood = SGE.textures().loadTexture(gl, bitmap);
        bitmap.recycle();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture_brickwall);
        texture_brickwall = SGE.textures().loadTexture(gl, bitmap);
        bitmap.recycle();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture_cartoonbrick);
        texture_cartoonBrickwall = SGE.textures().loadTexture(gl, bitmap);
        bitmap.recycle();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture_orangeshine);
        texture_orangeShine = SGE.textures().loadTexture(gl, bitmap);
        bitmap.recycle();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture_metalpanel);
        texture_metalPanel = SGE.textures().loadTexture(gl, bitmap);
        bitmap.recycle();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.texture_rope);
        texture_rope = SGE.textures().loadTexture(gl, bitmap);
        bitmap.recycle();


        /* ~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Tiles
         *~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_black);
        tile_black = SGE.textures().loadTexture(gl, bitmap);
        bitmap.recycle();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_bluemetal);
        tile_blueMetal = SGE.textures().loadTexture(gl, bitmap);
        bitmap.recycle();

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.tile_white);
        tile_white = SGE.textures().loadTexture(gl, bitmap);
        bitmap.recycle();
    }
}
