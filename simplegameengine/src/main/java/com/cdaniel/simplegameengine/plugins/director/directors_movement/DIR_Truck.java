package com.cdaniel.simplegameengine.plugins.director.directors_movement;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.AbstractBaseDir;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VectorMath;
import com.cdaniel.simplegameengine.utils.constants.Constants;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

/**
 * Created by christopher.daniel on 6/2/16.
 */
public class DIR_Truck extends AbstractBaseDir {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Float truck;    //moving the entire camera left/right

    private Vector pathVector;
    private Transform_Move t;

    DIR_Truck(Float truck, Float duration) {

        this.duration = duration;
        this.truck = truck;
    }

    @Override
    protected void initialize_II(){

        pathVector = new SimpleVector(SGE.camera().getEyePosition(), SGE.camera().getLookingAt());

        float rotation = truck > 0 ? -1f* Constants.PI/4 : Constants.PI/4;
        Calc_VectorMath.rotateAboutY(pathVector, rotation);

        t = Transform_Move.builder().build();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Action
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected void executeDirection_II(float executionPercent){

        float thisTruck = truck * executionPercent;
        Vector scaled = Calc_VectorMath.scaleVector(this.pathVector, thisTruck);

        SimpleVertex tweenVertex = new SimpleVertex(scaled.getEx(), scaled.getEy(), scaled.getEz());

        t.updateValues(tweenVertex);
        SGE.camera().applyPositionTransform(t);
    }
    protected void finishUp(){

    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static DIR_TruckBuilder builder() {
        return new DIR_TruckBuilder();
    }
    public static class DIR_TruckBuilder {

        private Float truck;
        private Float duration;

        public DIR_Truck.DIR_TruckBuilder truck(float truck) {
            this.truck = truck;
            return this;
        }
        public DIR_Truck.DIR_TruckBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public DIR_Truck build() {
            return new DIR_Truck(truck, duration);
        }
    }
}
