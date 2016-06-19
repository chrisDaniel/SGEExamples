package com.cdaniel.simplegameengine.plugins.tween.tweenclasses;

import com.cdaniel.simplegameengine.core.AbstractHasProperties;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.engine.SGEContentWrapper;
import com.cdaniel.simplegameengine.plugins.tween.Tween;
import com.cdaniel.simplegameengine.plugins.tween.TweenEase;
import com.cdaniel.simplegameengine.utils.converters.Conv_PropertyValues;

/**
 * Created by christopher.daniel on 5/7/16.
 */
abstract public class AbstractBaseTween extends AbstractHasProperties{

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /*****************************
     * Definition
     *****************************/
    private int globalTweenId;
    protected TweenEase easer;
    protected SGEContentWrapper attachedTo;
    protected float duration = 10f;


    /*****************************
     * Iteration
     *****************************/
    protected float totalIterations = 0;
    protected float currentIteration = 0;


    /*****************************
     * Loop/Replay
     *****************************/
    protected boolean onPause = false;

    private String reverseValue = Tween.TWEEN_REVERSE_No;
    protected boolean inReverse = false;

    private int loopValue = Tween.TWEEN_LOOP_None;
    private int loopNumber = 0;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Play Options
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    public void setGlobalId(int id){
        this.globalTweenId = id;
    }

    public int getGlobalId(){
        return this.globalTweenId;
    }

    public void setPauseState(boolean pauseStatus){
        onPause = true;
    }

    public int getContentId(){return this.attachedTo.getContentId(); }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Tween Interface
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public boolean executeTween(){

        //***********************************
        //Pause ... Just return true and stop
        //***********************************
        if(onPause){
            return true;
        }

        //***********************************
        //Inside current Tween step
        //***********************************
        if(!inReverse && (currentIteration < totalIterations)){
            return true;
        }
        if(inReverse && (currentIteration > 0)){
            return true;
        }

        //***********************************
        //Done with current Tween step
        //Decide need to loop and/or reverse
        //***********************************
        if(loopNumber >= loopValue){
            if(reverseValue.equals(Tween.TWEEN_REVERSE_No)){
                return finishTween();
            }
            if(!inReverse){
                inReverse = true;
                return true;
            }
        }
        else{
            loopNumber++;
            if(reverseValue.equals(Tween.TWEEN_REVERSE_No)){
                currentIteration = 0;
                return true;
            }
            else if(!inReverse){
                inReverse = true;
                return true;
            }
            else if(inReverse){
                inReverse = false;
                return true;
            }
        }
        return finishTween();
    }
    private boolean finishTween(){
        executeTween_Last();

        String finalAction = Conv_PropertyValues.extract_String(this.getProperty(Tween.TWEEN_COMPLETE_ACT));
        if(finalAction.equals(Tween.TWEEN_COMPLETE_ACT_remove)){
            SGE.contents().remove(this.attachedTo.getContentId());
        }

        return false;
    }
    abstract protected void executeTween_Last();

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * From Abstract Has Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected void beforePropertyApply(String propertyName, Object propertyValue){

        if(propertyName.equals(Tween.TWEEN_REVERSE)){
            this.reverseValue  = Conv_PropertyValues.extract_String(propertyValue);
        }
        if(propertyName.equals(Tween.TWEEN_LOOP)){
            this.loopValue = Conv_PropertyValues.extract_Integer(propertyValue);
        }
    }
}
