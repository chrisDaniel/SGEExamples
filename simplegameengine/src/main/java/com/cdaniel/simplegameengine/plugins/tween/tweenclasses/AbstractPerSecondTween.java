package com.cdaniel.simplegameengine.plugins.tween.tweenclasses;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.engine.SGEContentWrapper;
import com.cdaniel.simplegameengine.plugins.tween.Tween;

/**
 * Created by christopher.daniel on 5/11/16.
 */
abstract public class AbstractPerSecondTween extends AbstractBaseTween implements Tween {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected int iterationsPerSecond = 5;

    private int currentFrameCount = 0;
    private int iterateAtFrame = 0;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Tween Interface
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void attach(SGEContentWrapper wrapper){

        attachedTo = wrapper;

        float frameRate = Math.min(21, SGE.properties().frameRate());
        int totalFrames = (int) (duration * frameRate);

        this.totalIterations = (int) duration * iterationsPerSecond;
        this.iterateAtFrame = (int) (totalFrames / totalIterations);

        afterAttach();
    }
    abstract protected void afterAttach();

    public boolean executeTween(){

        if(!super.executeTween()){
            return false;
        }

        currentFrameCount++;
        if(currentFrameCount >= iterateAtFrame) {

            currentIteration = inReverse ? currentIteration-1 : currentIteration+1;
            currentFrameCount = 0;

            float executionPercent = currentIteration / totalIterations;

            if(easer != null){
                executionPercent = easer.ease(executionPercent);
            }
            executeTween_II(executionPercent);
        }
        return true;
    }
    abstract protected void executeTween_Last();
    abstract protected void executeTween_II(float executionPercent);

}
