package com.cdaniel.simplegameengine.core;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public interface Vector {

    Vertex getStartVertex();
    Vertex getEndVertex();

    void transform(Vertex start, Vertex end);
    void transform(Vector v);
    void transform(float sX, float sY, float sZ, float eX, float eY, float eZ);

    Vector copy();

    float getSx();
    float getSy();
    float getSz();
    float getEx();
    float getEy();
    float getEz();
}
