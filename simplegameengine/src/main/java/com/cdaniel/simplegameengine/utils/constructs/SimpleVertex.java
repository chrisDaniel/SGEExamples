package com.cdaniel.simplegameengine.utils.constructs;

import com.cdaniel.simplegameengine.core.Vertex;

/**
 * Created by christopher.daniel on 4/16/16.
 */
public class SimpleVertex implements Vertex {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Variables
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected float x;
    protected float y;
    protected float z;

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Construct
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public SimpleVertex(){

        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    public SimpleVertex(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public SimpleVertex(Vertex v){

        this.x = v.getX();
        this.y = v.getY();
        this.z = v.getZ();
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Transform / Clone
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void transform(float x, float y, float z){

        this.x = x;
        this.y = y;
        this.z = z;
    }
    public void transform(Vertex v){

        this.x = v.getX();
        this.y = v.getY();
        this.z = v.getZ();
    }
    public SimpleVertex copy(){

        SimpleVertex copy = new SimpleVertex(x, y, z);
        return copy;
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Inspection
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public float getX(){
        return this.x;
    }
    public float getY(){
        return this.y;
    }
    public float getZ(){
        return this.z;
    }

    public String toString(){
        String xPart = "X:" + x;
        String yPart = "Y:" + y;
        String zPart = "Z:" + z;

        return (xPart + ", " + yPart +", " + zPart);
    }
}
