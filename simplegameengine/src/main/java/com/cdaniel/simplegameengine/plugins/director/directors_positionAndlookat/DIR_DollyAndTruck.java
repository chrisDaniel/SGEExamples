package com.cdaniel.simplegameengine.plugins.director.directors_positionAndlookat;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.AbstractBaseDir;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VectorMath;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VertexMath;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

/**
 * Created by christopher.daniel on 6/2/16.
 */
public class DIR_DollyAndTruck extends AbstractBaseDir {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * How does Dolly/Truck work
     *
     * Consider the camera ... and a LookVector from the eye position to the look position
     *
     *                . lookAt = (lX, lY, lZ)
     *             .
     *          .                   LookVector = (eX, eY, eZ) -> (lX, lY, lZ)
     *       .
     *  eye = (eX, eY, eZ)
     *
     *
     * 'Dolly' moves the eye position towards the lookAt position along the LookVector
     *         so this gives you an easy command for move forward
     *
     * 'Truck' means left and right movement. So a movement that is parallel to the LookVector
     *
     * 'Fix Y' so...fixxing Y is ensuring that our eye position does not increase in height.
     *         if you are looking upwards ... you might not want to actually increase Y.
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    //input parameters
    private Float dolly;
    private Float truck;
    private boolean fixY;

    //transform calculations
    private SimpleVector originalLookVector;
    private Vector dollyVector;
    private float dollyM;

    //transforms we are using
    private Transform_Move t;


    DIR_DollyAndTruck(Float dolly, Float truck, Boolean fixY, Float duration) {

        this.duration = duration;
        this.dolly = dolly;
        this.truck = truck;

        this.fixY = fixY == null ? false : true;

        t = Transform_Move.builder().build();
    }

    @Override
    protected void initialize_II(){

        originalLookVector = new SimpleVector(SGE.camera().getEyePosition(), SGE.camera().getLookingAt());

        if(fixY){
            originalLookVector.transform(originalLookVector.getSx(), originalLookVector.getSy(), originalLookVector.getSz(),
                                         originalLookVector.getEx(), originalLookVector.getSy(), originalLookVector.getEz());
        }

        Vector rootedEyeLookV = Calc_VectorMath.standardizeVector(originalLookVector);
        Vector rootedDollyVector = new SimpleVector(0f, 0f, 0f, truck, 0f, dolly);

        float dollyAngle = Calc_VectorMath.angleBetween(rootedEyeLookV, SimpleVector.xUnit);

        dollyVector = Calc_VectorMath.rotateAboutY(rootedDollyVector, -1*dollyAngle);
        Vertex dollyEndAt= Calc_VertexMath.add(originalLookVector.getStartVertex(), dollyVector.getEndVertex());
        dollyVector = new SimpleVector(originalLookVector.getStartVertex(), dollyEndAt);

        dollyM = Calc_VectorMath.magnitude(dollyVector);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Action
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    protected void executeDirection_II(float executionPercent){

        float currentDollyM = executionPercent * dollyM;
        Vector newEye = Calc_VectorMath.resizeVector(dollyVector, currentDollyM);
        Vector newLookAt = Calc_VectorMath.addVectors(newEye, originalLookVector);

        SimpleVertex eyeTweenVertex = new SimpleVertex(newEye.getEx(), newEye.getEy(), newEye.getEz());
        SimpleVertex laTweenVertex = new SimpleVertex(newLookAt.getEx(), newLookAt.getEy(), newLookAt.getEz());

        t.updateValues(eyeTweenVertex);
        SGE.camera().applyPositionTransform(t);

        t.updateValues(laTweenVertex);
        SGE.camera().applyLookAtTransform(t);
    }
    @Override
    protected void finishUp(){

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static DIR_DollyAndTruckBuilder builder() {
        return new DIR_DollyAndTruckBuilder();
    }
    public static class DIR_DollyAndTruckBuilder {

        private Float dolly = 0f;
        private Float truck = 0f;
        private Float duration;
        private Boolean fixY;

        public DIR_DollyAndTruck.DIR_DollyAndTruckBuilder dolly(float dolly) {
            this.dolly = dolly;
            return this;
        }
        public DIR_DollyAndTruck.DIR_DollyAndTruckBuilder truck(float truck) {
            this.truck = truck;
            return this;
        }
        public DIR_DollyAndTruck.DIR_DollyAndTruckBuilder fixY(boolean fixY) {
            this.fixY = fixY;
            return this;
        }
        public DIR_DollyAndTruck.DIR_DollyAndTruckBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public DIR_DollyAndTruck build() {
            return new DIR_DollyAndTruck(dolly, truck, fixY, duration);
        }
    }
}
