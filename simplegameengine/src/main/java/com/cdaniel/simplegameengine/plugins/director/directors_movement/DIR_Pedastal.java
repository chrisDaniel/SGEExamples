package com.cdaniel.simplegameengine.plugins.director.directors_movement;

import com.cdaniel.simplegameengine.plugins.director.AbstractBaseDir;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

/**
 * Created by christopher.daniel on 6/2/16.
 */
public class DIR_Pedastal extends AbstractBaseDir {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Float toY;    //moving the entire camera up and down
    private Float fromY;

    private Transform_Move tEye;
    private Transform_Move tLookAt;

    DIR_Pedastal(Float toY, Float duration) {

        this.duration = duration;
        this.toY = toY;
    }

    @Override
    protected void initialize_II(){

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Action
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected void executeDirection_II(float executionPercent){

    }
    protected void finishUp(){

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static DIR_DollyBuilder builder() {
        return new DIR_DollyBuilder();
    }
    public static class DIR_DollyBuilder {

        private Float dolly;
        private Float duration;

        public DIR_Pedastal.DIR_DollyBuilder dolly(float dolly) {
            this.dolly = dolly;
            return this;
        }
        public DIR_Pedastal.DIR_DollyBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public DIR_Pedastal build() {
            return new DIR_Pedastal(dolly, duration);
        }
    }
}
