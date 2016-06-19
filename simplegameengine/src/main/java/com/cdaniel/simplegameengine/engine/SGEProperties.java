package com.cdaniel.simplegameengine.engine;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by christopher.daniel on 5/7/16.
 */
public class SGEProperties implements SGEPipeline {


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Contstruct / Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    private Map<String, Object> propertyMap;
    private Set<String> dirtyProperties;

    SGEProperties() {

        propertyMap = new HashMap<>();
        dirtyProperties = new HashSet<>();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * General Settable/Readable Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void apply(Map<String, Object> contextPropertyMap) {

        for (String property : contextPropertyMap.keySet()) {
            apply(property, contextPropertyMap.get(property));
        }
    }
    public void apply(String propertyName, Object propertyValue) {

        propertyMap.put(propertyName, propertyValue);
        dirtyProperties.add(propertyName);
    }
    public Object read(String propertyName) {

        return propertyMap.get(propertyName);
    }
    public boolean isPropertyDirty(String propertyName) {

        if(dirtyProperties.contains(propertyName)){
            return true;
        }
        return false;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Pipeline
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void beforeDraw(){}

    void afterDraw(){

        dirtyProperties = new HashSet<>();
        doFrameRate();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Frame Rate
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public int totalFrames(){return totalFramesProcessed;}
    public float frameRate(){return frameRate;}
    public long currentMilliseconds(){
        return currentMilliseconds;
    }
    public boolean isFrameRateDirty(){
        return dirtyFrameRate;
    }

    private int totalFramesProcessed = 0;

    private boolean dirtyFrameRate = false;
    private float frameRate = 35f;
    private int   frameCount = 0;
    private float frameCountSeconds = 0;
    private float frCalcPeriod = 8000f;

    private long  currentMilliseconds = new Date().getTime();
    private long  lastFRCalcTime      = new Date().getTime();



    private void doFrameRate(){

        frameCount++;
        totalFramesProcessed++;

        /* **********************************/
        // Do not update for first 100 Frames
        /* **********************************/
        if(totalFramesProcessed < 100){
            return;
        }

        /* **********************************/
        // Frame Rate Calculation
        /* **********************************/
        currentMilliseconds = new Date().getTime();

        if(currentMilliseconds - lastFRCalcTime > frCalcPeriod){

            frameCountSeconds = (currentMilliseconds - lastFRCalcTime) / 1000f;
            frameRate = (frameCount / frameCountSeconds);

            frameCount = 0;
            dirtyFrameRate = true;
            lastFRCalcTime = currentMilliseconds;
        }
        else{
            dirtyFrameRate = false;
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Pipeline Contract
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean contentFreezeInPlace = false;

    @Override
    public void contentFreeze(boolean isFrozen){
        contentFreezeInPlace = isFrozen;
    }
    @Override
    public void pause(boolean isPaused){
    }
    @Override
    public void deleteEverything(){
    }
}