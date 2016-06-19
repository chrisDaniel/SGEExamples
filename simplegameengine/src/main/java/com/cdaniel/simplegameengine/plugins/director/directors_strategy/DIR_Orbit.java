package com.cdaniel.simplegameengine.plugins.director.directors_strategy;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.AbstractBaseDir;
import com.cdaniel.simplegameengine.plugins.director.CameraDirector;
import com.cdaniel.simplegameengine.utils.constants.Constants;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Rotate_GlobalSpace;

/**
 * Created by christopher.daniel on 5/21/16.
 */
public class DIR_Orbit extends AbstractBaseDir implements CameraDirector {


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float secondsPerRevolution;
    private Transform_Rotate_GlobalSpace t;

    DIR_Orbit(float secondsPerRevolution, float duration){

        this.duration = duration;
        this.secondsPerRevolution = secondsPerRevolution;

    }
    @Override
    protected void initialize_II(){

        float frameRate = Math.min(21, SGE.properties().frameRate());
        float framesPerRevolution = secondsPerRevolution * frameRate;

        float radiansPerFrame = 2 * Constants.PI / framesPerRevolution;

        t = Transform_Rotate_GlobalSpace.builder().rotationAboutY(radiansPerFrame).build();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Action
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected void executeDirection_II(float executionPercent){

        SGE.camera().applyPositionTransform(t);
    }
    protected void finishUp(){
        
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static DIR_OrbitBuilder builder() {
        return new DIR_OrbitBuilder();
    }
    public static class DIR_OrbitBuilder {

        private float secondsPerRevolution = 10f;
        private float duration = 10f;

        public DIR_Orbit.DIR_OrbitBuilder secondsPerRevolution(float secondsPerRevolution) {
            this.secondsPerRevolution = secondsPerRevolution;
            return this;
        }
        public DIR_Orbit.DIR_OrbitBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public DIR_Orbit build() {
            return new DIR_Orbit(secondsPerRevolution, duration);
        }
    }


}
