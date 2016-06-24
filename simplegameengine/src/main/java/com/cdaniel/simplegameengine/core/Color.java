package com.cdaniel.simplegameengine.core;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public interface Color {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Standard Colors
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    float[] WHITE = {1.0f, 1.0f, 1.0f, 1f};
    float[] BLACK = {0.0f, 0.0f, 0.0f, 1f};
    float[] RED = {1.0f, 0.0f, 0.0f, 1f};
    float[] GREEN = {0.0f, 1.0f, 0.0f, 1f};
    float[] YELLOW = {1.0f, 1.0f, 0.0f, 1f};
    float[] BLUE = {0.0f, 0.0f, 1.0f, 1f};
    float[] MAGENTA = {1.0f, 0.0f, 1.0f, 1f};
    float[] CYAN = {0.0f, 1.0f, 1.0f, 1f};
    float[] PURPLE = {160f/256f, 32f/256f, 240f/256f, 1f};
    float[] GRAY = {.2f, .2f, .2f, 1f};

    float[] JUPITER ={238f/256f, 213f/256f, 183f/256f, 1f};

    float[] HA_GREENSEA = {147f/256f, 250f/256f, 255f/256f, 1f};
    float[] HA_BLUEDARKMETAL = {70f/256f, 96f/256f, 109f/256f, 1f};
    float[] HA_BLUEDEEPPURPLE = {67f/256f, 59f/256f, 199f/256f, 1f};
    //float[] HA_ = {f/256f, f/256f, f/256f, 1f};
    //float[] HA_ = {f/256f, f/256f, f/256f, 1f};

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Interface Methods
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    Color copy();

    void setColor(float[] color);
    float[] getColor();
    
    void setRed(float red);
    float getRed();

    void setGreen(float green);
    float getGreen();

    void setBlue(float blue);
    float getBlue();

    void setAlpha(float alpha);
    float getAlpha();
}
