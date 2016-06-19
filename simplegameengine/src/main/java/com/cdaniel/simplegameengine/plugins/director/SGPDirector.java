package com.cdaniel.simplegameengine.plugins.director;

import com.cdaniel.simplegameengine.core.SimpleGamePlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christopher.daniel on 5/11/16.
 */
public class SGPDirector implements SimpleGamePlugin {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private List<CameraDirector> directionQueue;
    private boolean paused = false;

    public SGPDirector(){
        this.directionQueue = new ArrayList<>();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Apply
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void queueDirector(CameraDirector director){
        this.directionQueue.add(director);
    }
    public void killCurrentDirector(){

        if(!this.directionQueue.isEmpty()) {
            this.directionQueue.remove(0);
        }
    }
    public void killAllDirectors(){
        this.directionQueue.clear();
    }
    public void setPause(boolean paused){
        this.paused = paused;
    }
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
   * Plugin Contract
   *
   *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void beforeDraw() {

        if(paused){
            return;
        }

        boolean stillDirecting = true;
        while(stillDirecting) {
            if(directionQueue.isEmpty()){
                return;
            }
            if (!directionQueue.get(0).executeDirection()) {
                directionQueue.remove(0);
            } else {
                return;
            }
        }
    }
    @Override
    public void afterDraw(){
    }
    @Override
    public void contentFreeze(boolean isFrozen){
    }
    @Override
    public void pause(boolean isPaused){
    }
    @Override
    public void deleteEverything(){
    }
}
