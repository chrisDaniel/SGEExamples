package com.cdaniel.simplegameengine.plugins.physics.physicalProperties;

/**
 * Created by christopher.daniel on 5/23/16.
 */
public class AbstractPhysicalProperty {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Global Id
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected  int globalId;

    public void setGlobalId(int id){
        globalId = id;
    }
    public int getGlobalId(){
        return globalId;
    }

}
