package com.cdaniel.simplegameengine.plugins.director.directors_positionAndlookat;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.AbstractBaseDir;
import com.cdaniel.simplegameengine.plugins.director.CameraDirector;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

/**
 * Created by christopher.daniel on 5/21/16.
 */
public class DIR_Slide extends AbstractBaseDir implements CameraDirector {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    
    //how much to slide
    private float deltaX = 0f;
    private float deltaY = 0f;
    private float deltaZ = 0f;
    
    //how much of the slide has been completed
    private float slidX = 0f;
    private float slidY = 0f;
    private float slidZ = 0f;
    
    private Transform_Move t;

    DIR_Slide(Float deltaX, Float deltaY, Float deltaZ, Float duration) {

        this.duration = duration;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
    }

    @Override
    protected void initialize_II(){
        
        t = Transform_Move.builder().build();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Action
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected void executeDirection_II(float executionPercent){
        
        //Update 1...
        //Eye Positon
        SimpleVertex tweenVertex = new SimpleVertex(
                (SGE.camera().getEyePosition().getX() + (executionPercent * deltaX) - slidX),
                (SGE.camera().getEyePosition().getY() + (executionPercent * deltaY) - slidY),
                (SGE.camera().getEyePosition().getZ() + (executionPercent * deltaZ) - slidZ));

        t.updateValues(tweenVertex);
        SGE.camera().applyPositionTransform(t);
        
        
        //Update 2...
        //Look at position
        tweenVertex = new SimpleVertex(
                (SGE.camera().getLookingAt().getX() + (executionPercent * deltaX) - slidX),
                (SGE.camera().getLookingAt().getY() + (executionPercent * deltaY) - slidY),
                (SGE.camera().getLookingAt().getZ() + (executionPercent * deltaZ) - slidZ));

        t.updateValues(tweenVertex);
        SGE.camera().applyLookAtTransform(t);
        
        
        //Update 3...
        //The total slid counters
        slidX = executionPercent * deltaX;
        slidY = executionPercent * deltaY;
        slidZ = executionPercent * deltaZ;

    }
    protected void finishUp(){
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static DIR_SlidePositionAndLookBuilder builder() {
        return new DIR_SlidePositionAndLookBuilder();
    }
    public static class DIR_SlidePositionAndLookBuilder {

        private float deltaX;
        private float deltaY;
        private float deltaZ;
        private Float duration;

        public DIR_Slide.DIR_SlidePositionAndLookBuilder deltaX(float deltaX) {
            this.deltaX = deltaX;
            return this;
        }
        public DIR_Slide.DIR_SlidePositionAndLookBuilder deltaY(float deltaY) {
            this.deltaY = deltaY;
            return this;
        }
        public DIR_Slide.DIR_SlidePositionAndLookBuilder deltaZ(float deltaZ) {
            this.deltaZ = deltaZ;
            return this;
        }
        public DIR_Slide.DIR_SlidePositionAndLookBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public DIR_Slide build() {
            return new DIR_Slide(deltaX, deltaY, deltaZ, duration);
        }
    }
}
