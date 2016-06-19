package com.cdaniel.simplegameengine.plugins.director.directors_focus;

import com.cdaniel.simplegameengine.plugins.director.AbstractBaseDir;
import com.cdaniel.simplegameengine.plugins.director.CameraDirector;

/**
 * Created by christopher.daniel on 5/23/16.
 */
public class DIR_StillShot extends AbstractBaseDir implements CameraDirector{

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    DIR_StillShot(Float duration) {

        this.duration = duration;
    }

    @Override
    protected void initialize_II(){
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Action
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void executeDirection_II(float executionPercent){
    }
    @Override
    protected void finishUp(){
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static DIR_StillShotBuilder builder() {
        return new DIR_StillShotBuilder();
    }
    public static class DIR_StillShotBuilder {

        private Float duration;

        public DIR_StillShot.DIR_StillShotBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public DIR_StillShot build() {
            return new DIR_StillShot(duration);
        }
    }
}
