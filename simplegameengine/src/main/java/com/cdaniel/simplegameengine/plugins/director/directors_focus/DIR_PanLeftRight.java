package com.cdaniel.simplegameengine.plugins.director.directors_focus;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.AbstractBaseDir;
import com.cdaniel.simplegameengine.plugins.director.CameraDirector;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VectorMath;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

/**
 * Created by christopher.daniel on 5/23/16.
 */
public class DIR_PanLeftRight extends AbstractBaseDir implements CameraDirector{

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float panAngle;
    private float radiansPerIteration;
    private Transform_Move t;
    private Vector lookVector;

    DIR_PanLeftRight(float panAngle, Float duration) {

        this.panAngle = panAngle;
        this.duration = duration;
    }
    
    @Override
    protected void initialize_II(){
        
        radiansPerIteration = panAngle / totalIterations;
        lookVector = new SimpleVector(SGE.camera().getEyePosition(), SGE.camera().getLookingAt());

        t = Transform_Move.builder().build();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Action
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private SimpleVector v = new SimpleVector();

    @Override
    public void executeDirection_II(float executionPercent){

        lookVector = Calc_VectorMath.rotateAboutY(lookVector, radiansPerIteration);
        t.updateValues(lookVector.getEx(), lookVector.getEy(), lookVector.getEz());
        SGE.camera().applyLookAtTransform(t);
    }
    protected void finishUp(){

        return;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static DIR_PanLeftRightBuilder builder() {
        return new DIR_PanLeftRightBuilder();
    }
    public static class DIR_PanLeftRightBuilder {

        private Float alpha;
        private Float duration;

        public DIR_PanLeftRight.DIR_PanLeftRightBuilder alpha(float alpha) {
            this.alpha = alpha;
            return this;
        }
        public DIR_PanLeftRight.DIR_PanLeftRightBuilder left(float alpha) {
            this.alpha = alpha;
            return this;
        }
        public DIR_PanLeftRight.DIR_PanLeftRightBuilder right(float alpha) {
            this.alpha = -1*alpha;
            return this;
        }
        public DIR_PanLeftRight.DIR_PanLeftRightBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public DIR_PanLeftRight build() {
            return new DIR_PanLeftRight(alpha, duration);
        }
    }
}
