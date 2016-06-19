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
public class DIR_PanUpDown extends AbstractBaseDir implements CameraDirector{

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     * Construct and Variables
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float panAngle;
    private float radiansPerIteration;
    private Transform_Move t;

    DIR_PanUpDown(float panAngle, Float duration) {

        this.panAngle = panAngle;
        this.duration = duration;
    }

    @Override
    protected void initialize_II(){

        radiansPerIteration = panAngle / totalIterations;
        t = Transform_Move.builder().build();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Action
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private SimpleVector lookVector = new SimpleVector(0f, 0f, 0f, 1f, 0f, 0f);
    private SimpleVector xUnit = new SimpleVector(0f, 0f, 0f, 1f, 0f, 0f);
    private Vector calcer = new SimpleVector(0f, 0f, 0f, 1f, 0f, 0f);

    @Override
    public void executeDirection_II(float executionPercent){

        lookVector.transform(SGE.camera().getEyePosition(), SGE.camera().getLookingAt());

        calcer = Calc_VectorMath.standardizeVector(lookVector);

        float calcerAngle = Calc_VectorMath.angleBetween(xUnit, calcer);
        calcer = Calc_VectorMath.rotateAboutY(calcer, calcerAngle);
        calcer = Calc_VectorMath.rotateAboutZ(calcer, radiansPerIteration);
        calcer = Calc_VectorMath.rotateAboutY(calcer, -1*calcerAngle);
        calcer = Calc_VectorMath.moveVector(calcer, lookVector.getSx(), lookVector.getSy(), lookVector.getSz());

        t.updateValues(calcer.getEx(), calcer.getEy(), calcer.getEz());
        SGE.camera().applyLookAtTransform(t);
    }
    protected void finishUp(){

        return;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static DIR_PanUpDownBuilder builder() {
        return new DIR_PanUpDownBuilder();
    }
    public static class DIR_PanUpDownBuilder {

        private Float alpha;
        private Float duration;

        public DIR_PanUpDown.DIR_PanUpDownBuilder alpha(float alpha) {
            this.alpha = alpha;
            return this;
        }
        public DIR_PanUpDown.DIR_PanUpDownBuilder up(float alpha) {
            this.alpha = alpha;
            return this;
        }
        public DIR_PanUpDown.DIR_PanUpDownBuilder down(float alpha) {
            this.alpha = -1*alpha;
            return this;
        }
        public DIR_PanUpDown.DIR_PanUpDownBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public DIR_PanUpDown build() {
            return new DIR_PanUpDown(alpha, duration);
        }
    }
}
