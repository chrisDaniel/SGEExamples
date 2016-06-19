package com.cdaniel.simplegameengine.utils.constructs;

import com.cdaniel.simplegameengine.core.Vertex;

import java.util.Date;

/**
 * Created by christopher.daniel on 4/16/16.
 */
public class HistoricalVertex extends SimpleVertex {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Variables / Construct
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected Date dateTime;

    public HistoricalVertex(float x, float y, float z){
        super(x,y,z);
        this.dateTime = new Date();
    }
    public HistoricalVertex(Vertex v){
        super(v);
        this.dateTime = new Date();
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Variables
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void transform(float x, float y, float z){
        return;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Inspection
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public String toString(){

        return (super.toString() + ". Timestamp: " + dateTime.toString());
    }
}
