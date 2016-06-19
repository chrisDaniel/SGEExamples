package com.cdaniel.simplegameengine.core;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public interface Vertex {

    void transform(float x, float y, float z);

    float getX();
    float getY();
    float getZ();

    Vertex copy();
}
