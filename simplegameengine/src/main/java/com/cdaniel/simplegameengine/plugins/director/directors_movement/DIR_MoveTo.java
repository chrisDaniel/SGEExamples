package com.cdaniel.simplegameengine.plugins.director.directors_movement;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.AbstractBaseDir;
import com.cdaniel.simplegameengine.plugins.director.CameraDirector;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

/**
 * Created by christopher.daniel on 5/21/16.
 */
public class DIR_MoveTo extends AbstractBaseDir implements CameraDirector {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Float toX;
    private Float toY;
    private Float toZ;
    private Float fromX;
    private Float fromY;
    private Float fromZ;

    private float deltaX = 0f;
    private float deltaY = 0f;
    private float deltaZ = 0f;


    private Transform_Move t;
    
    DIR_MoveTo(Float x, Float y, Float z, Float duration) {

        this.duration = duration;
        this.toX = x;
        this.toY = y;
        this.toZ = z;
    }

    @Override
    protected void initialize_II(){

        fromX = SGE.camera().getEyePosition().getX();
        fromY = SGE.camera().getEyePosition().getY();
        fromZ = SGE.camera().getEyePosition().getZ();
        
        toX = (toX == null ? fromX : toX);
        toY = (toY == null ? fromY : toY);
        toZ = (toZ == null ? fromZ : toZ);
        
        deltaX = toX - fromX;
        deltaY = toY - fromY;
        deltaZ = toZ - fromZ;

        t = Transform_Move.builder().build();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Action
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected void executeDirection_II(float executionPercent){

        SimpleVertex tweenVertex = new SimpleVertex(
                (fromX + executionPercent * deltaX),
                (fromY + executionPercent * deltaY),
                (fromZ + executionPercent * deltaZ) );

        t.updateValues(tweenVertex);
        SGE.camera().applyPositionTransform(t);
    }
    protected void finishUp(){

        SimpleVertex tweenVertex = new SimpleVertex(toX, toY, toZ);
        t.updateValues(tweenVertex);
        SGE.camera().applyPositionTransform(t);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static DIR_MoveToBuilder builder() {
        return new DIR_MoveToBuilder();
    }
    public static class DIR_MoveToBuilder {

        private Float toX;
        private Float toY;
        private Float toZ;
        private Float duration;
        
        public DIR_MoveTo.DIR_MoveToBuilder toX(float toX) {
            this.toX = toX;
            return this;
        }
        public DIR_MoveTo.DIR_MoveToBuilder toY(float toY) {
            this.toY = toY;
            return this;
        }
        public DIR_MoveTo.DIR_MoveToBuilder toZ(float toZ) {
            this.toZ = toZ;
            return this;
        }
        public DIR_MoveTo.DIR_MoveToBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public DIR_MoveTo build() {
            return new DIR_MoveTo(toX, toY, toZ, duration);
        }
    }
}
