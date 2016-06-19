package com.cdaniel.simplegameengine.utils.constructs;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.core.Vertex;

/**
 * Created by christopher.daniel on 4/16/16.
 */
public class SimpleVector implements Vector {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Variables
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected float sX = 0f;
    protected float sY = 0f;
    protected float sZ = 0f;
    protected float eX = 0f;
    protected float eY = 0f;
    protected float eZ = 0f;


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Construct
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public SimpleVector(){

    }
    public SimpleVector(float xS, float yS, float zS, float xE, float yE, float zE){
        this.sX = xS;
        this.sY = yS;
        this.sZ = zS;
        this.eX = xE;
        this.eY = yE;
        this.eZ = zE;
    }
    public SimpleVector(Vertex start, Vertex end){

        this.sX = start.getX();
        this.sY = start.getY();
        this.sZ = start.getZ();
        this.eX = end.getX();
        this.eY = end.getY();
        this.eZ = end.getZ();
    }
    public SimpleVector(Vector v){
        this.sX = v.getSx();
        this.sY = v.getSy();
        this.sZ = v.getSz();
        this.eX = v.getEx();
        this.eY = v.getEy();
        this.eZ = v.getEz();
    }
    public SimpleVector(Vertex end){

        this.sX = 0f;
        this.sY = 0f;
        this.sZ = 0f;
        this.eX = end.getX();
        this.eY = end.getY();
        this.eZ = end.getZ();
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Transform / Clone
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void transform(Vertex start, Vertex end){
        this.sX = start.getX();
        this.sY = start.getY();
        this.sZ = start.getZ();
        this.eX = end.getX();
        this.eY = end.getY();
        this.eZ = end.getZ();
    }
    public void transform(Vector v){
        this.sX = v.getSx();
        this.sY = v.getSy();
        this.sZ = v.getSz();
        this.eX = v.getEx();
        this.eY = v.getEy();
        this.eZ = v.getEz();
    }
    public void transform(float xS, float yS, float zS, float xE, float yE, float zE) {
        this.sX = xS;
        this.sY = yS;
        this.sZ = zS;
        this.eX = xE;
        this.eY = yE;
        this.eZ = zE;
    }
    public SimpleVector copy(){

        SimpleVector copy = new SimpleVector(sX, sY, sZ, eX, eY, eZ);
        return copy;
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Inspection
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Vertex getStartVertex(){
        return new SimpleVertex(sX, sY, sZ);
    }
    public Vertex getEndVertex(){
        return new SimpleVertex(eX, eY, eZ);
    }
    public float getSx() {
        return sX;
    }
    public float getSy() {
        return sY;
    }
    public float getSz() {
        return sZ;
    }
    public float getEx() {
        return eX;
    }
    public float getEy() {
        return eY;
    }
    public float getEz() {
        return eZ;
    }

    @Override
    public String toString(){
        String s = "Vector: (" + sX +", " + sY + ", " + sZ + ")  -->  (" + eX +", " + eY + ", " + eZ + ")";
        return s;
    }
}
