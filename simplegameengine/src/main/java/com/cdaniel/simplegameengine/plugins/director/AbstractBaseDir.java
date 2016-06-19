package com.cdaniel.simplegameengine.plugins.director;

import com.cdaniel.simplegameengine.core.AbstractHasProperties;
import com.cdaniel.simplegameengine.engine.SGE;

/**
 * Created by christopher.daniel on 5/7/16.
 */
abstract public class AbstractBaseDir extends AbstractHasProperties implements CameraDirector {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /*****************************
     * Definition
     *****************************/
    private int globalDirId;
    protected Float duration;


    /*****************************
     * Iteration
     *****************************/
    protected Integer totalIterations;
    protected Integer currentIteration;



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Play Options
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    public void setGlobalId(int id){
        this.globalDirId = id;
    }
    public int getGlobalId(){
        return this.globalDirId;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Director Interface
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public boolean executeDirection(){

        if(totalIterations == null){
            this.initializeDirection();
            initialize_II();
        }

        if(duration == null || duration <= 0.01f){
            executeDirection_II(1f);
            return false;
        }

        if(currentIteration > totalIterations){
            finishUp();
            return false;
        }

        currentIteration++;
        float executionPercent = (float) currentIteration / (float) totalIterations;
        executeDirection_II(executionPercent);
        return true;
    }
    private void initializeDirection(){

        if(duration == null || duration <= .01f){
            this.totalIterations = 1;
            this.currentIteration = 0;
            return;
        }

        float frameRate = Math.min(21, SGE.properties().frameRate());

        this.totalIterations = (int) (duration * frameRate);
        this.currentIteration = 0;
    }
    abstract protected void initialize_II();
    abstract protected void executeDirection_II(float executionPercent);
    abstract protected void finishUp();

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * From Abstract Has Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected void beforePropertyApply(String propertyName, Object propertyValue){
    }
}
