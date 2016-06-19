package com.cdaniel.simplegameengine.plugins.director.directors_movement;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.AbstractBaseDir;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VectorMath;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

/**
 * Created by christopher.daniel on 6/2/16.
 */
public class DIR_Dolly extends AbstractBaseDir {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Float dolly;    //moving the entire camera forward and back
    private boolean fixY;

    private float dollyPerIteration;

    private Transform_Move tEye;
    private Transform_Move tLookAt;

    DIR_Dolly(Float dolly, Boolean fixY, Float duration) {

        this.duration = duration;
        this.dolly = dolly;

        this.fixY = fixY == null ? false : true;
    }

    @Override
    protected void initialize_II(){

        tEye = Transform_Move.builder().build();
        tLookAt = Transform_Move.builder().build();

        this.dollyPerIteration = (dolly / this.totalIterations);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Action
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Vector eyeLookVector;
    protected void executeDirection_II(float executionPercent){

        eyeLookVector = new SimpleVector(SGE.camera().getEyePosition(), SGE.camera().getLookingAt());

        Vector newEye = Calc_VectorMath.resizeVector(this.eyeLookVector, dollyPerIteration);
        Vector newLookAt = Calc_VectorMath.addVectors(eyeLookVector, newEye);

        SimpleVertex eyeTweenVertex = new SimpleVertex(newEye.getEx(), newEye.getEy(), newEye.getEz());
        SimpleVertex laTweenVertex = new SimpleVertex(newLookAt.getEx(), newLookAt.getEy(), newLookAt.getEz());

        if(fixY){
            eyeTweenVertex.transform(eyeTweenVertex.getX(), SGE.camera().getEyePosition().getY(), eyeTweenVertex.getZ());
            laTweenVertex.transform(laTweenVertex.getX(), SGE.camera().getLookingAt().getY(), laTweenVertex.getZ());
        }

        tEye.updateValues(eyeTweenVertex);
        tLookAt.updateValues(laTweenVertex);
        SGE.camera().applyPositionTransform(tEye);
        SGE.camera().applyLookAtTransform(tLookAt);
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
        private Boolean fixY;

        public DIR_Dolly.DIR_DollyBuilder dolly(float dolly) {
            this.dolly = dolly;
            return this;
        }
        public DIR_Dolly.DIR_DollyBuilder fixY(boolean fixY) {
            this.fixY = fixY;
            return this;
        }
        public DIR_Dolly.DIR_DollyBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public DIR_Dolly build() {
            return new DIR_Dolly(dolly, fixY, duration);
        }
    }
}
