package com.cdaniel.simplegameengine.utils.constructs;

import com.cdaniel.simplegameengine.core.Vertex;

/**
 * Created by christopher.daniel on 4/16/16.
 */
public class LockableVertex extends SimpleVertex {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Variables / Construct
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected boolean locked = false;

    public LockableVertex(){
        super();
        this.locked = false;
    }
    public LockableVertex(boolean lockStatus){
        super();
        this.locked = lockStatus;
    }
    public LockableVertex(float x, float y, float z){
        super(x,y,z);
        this.locked = false;
    }
    public LockableVertex(float x, float y, float z, boolean lockStatus){
        super(x,y,z);
        this.locked = lockStatus;
    }
    public LockableVertex(Vertex v){
        super(v);
        this.locked = false;
    }
    public LockableVertex(Vertex v, boolean lockStatus){
        super(v);
        this.locked = lockStatus;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Variables
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void transform(float x, float y, float z){

        if(this.locked){
            throw new RuntimeException("Trying to alter a locked vertex");
        }
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Lock Stuff
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void lock(){

        locked = true;
    }
    public void unlock(){

        locked = false;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Inspection
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString(){

        if(locked){
            return (super.toString() + ". LOCKED");
        }
        else{
            return super.toString();
        }
    }
}
