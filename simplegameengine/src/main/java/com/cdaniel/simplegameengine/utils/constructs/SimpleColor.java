package com.cdaniel.simplegameengine.utils.constructs;

import com.cdaniel.simplegameengine.core.Color;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public class SimpleColor implements Color {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Variables
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float[] color;


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructors
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public SimpleColor(){
        color = new float[4];
        color[0] = 0f;
        color[1] = 0f;
        color[2] = 0f;
        color[3] = 0f;
    }
    public SimpleColor(float red, float green, float blue, float alpha){

        color = new float[4];
        color[0] = red;
        color[1] = green;
        color[2] = blue;
        color[3] = alpha;
    }
    public SimpleColor(float[] colors){

        color = new float[4];
        color[0] = colors[0];
        color[1] = colors[1];
        color[2] = colors[2];
        color[3] = colors[3];
    }
    public SimpleColor(Color fromColor){

        color = new float[4];
        color[0] = fromColor.getRed();
        color[1] = fromColor.getGreen();
        color[2] = fromColor.getBlue();
        color[3] = fromColor.getAlpha();
    }
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Interface Methods
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void setColor(float[] color){
        this.color = color;
    }
    @Override
    public float[] getColor(){
        return this.color;
    }
    @Override
    public void setRed(float red){
        this.color[0] = red;
    }
    @Override
    public float getRed(){
        return this.color[0];
    }
    @Override
    public void setGreen(float green){
        this.color[1] = green;
    }
    @Override
    public float getGreen(){
        return this.color[1];
    }
    @Override
    public void setBlue(float blue){
        this.color[2] = blue;
    }
    @Override
    public float getBlue(){
        return this.color[2];
    }
    @Override
    public void setAlpha(float alpha){
        this.color[3] = alpha;
    }
    @Override
    public float getAlpha(){
        return this.color[3];
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Clone
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public SimpleColor copy(){
        return new SimpleColor(this.color);
    }
}
