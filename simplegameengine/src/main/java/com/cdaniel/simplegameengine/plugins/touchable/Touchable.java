package com.cdaniel.simplegameengine.plugins.touchable;

import com.cdaniel.simplegameengine.core.SimpleGameEngine;
import com.cdaniel.simplegameengine.core.SimpleGamePlugin;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VertexMath;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;
import com.cdaniel.simplegameengine.utils.converters.Conv_PropertyValues;

/**
 * Created by christopher.daniel on 5/19/16.
 */
public class Touchable implements SimpleGamePlugin {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Using 'Ray Tracing' via a Parametric equation that describes a line
    *
    * Here's a good Site:
    * http://tutorial.math.lamar.edu/Classes/CalcIII/EqnsOfLines.aspx
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Touchable(){
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Touch Evaluation
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Integer findTouchedContent(int screenX, int screenY){

        SimpleVertex eye = new SimpleVertex(SGE.camera().getEyePosition());
        SimpleVertex touch = getTouchedVertex(screenX, screenY);

        return null;
    }
    public SimpleVertex getTouchedVertex(int screenX, int screenY) {

        return calculateTouchVertex(screenX, screenY);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Private Calculation
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public SimpleVertex calculateTouchVertex(int screenX, int screenY){

        //step 1...
        //We will construct a formula for a line that
        //starts at the eye and passes through the look at point
        SimpleVertex eye = new SimpleVertex(SGE.camera().getEyePosition());
        SimpleVertex lookAt = new SimpleVertex(SGE.camera().getLookingAt());

        //step 2...
        //VParallel is a vector which is parallel to the line through eye and look at
        SimpleVertex vParallel = Calc_VertexMath.subtract(eye, lookAt);

        //step 3...
        //to construct the equation
        //ray = (eye position) + T(vParallel)
        //     --> x(t) = eye.x + T * v.x
        //     --> y(t) = eye.y + T * v.y
        //     --> z(t) = eye.z + T * v.z
        //we are looking for T such that distance(ray to eye) = frustNear
        SimpleVertex r0 = Calc_VertexMath.subtract(SGE.camera().getLookingAt(), SGE.camera().getEyePosition());
        SimpleVertex r1 = Calc_VertexMath.scale(r0, SGE.camera().getFrustNear());

        SimpleVertex viewPortCenter  = Calc_VertexMath.add(SGE.camera().getEyePosition(), r1);
        return viewPortCenter;
    }

    //1: Normalized device Coordinates
    private void rc_1(int touchX, int touchY){

        Object wValue = SGE.properties().read(SimpleGameEngine.SGE_PIXEL_WIDTH);
        float screenWidth = Conv_PropertyValues.extract_Float(wValue);

        Object hValue = SGE.properties().read(SimpleGameEngine.SGE_PIXEL_HEIGHT);
        float screenHeight = Conv_PropertyValues.extract_Float(hValue);

        float nX = (2f * touchX / screenWidth ) - 1f;
        float nY =  1f - (2f * touchY / screenHeight);
        float nZ = 1f;
    }



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Plugin Contract
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean contentFreezeInPlace = false;

    @Override
    public void beforeDraw() {
    }
    @Override
    public void afterDraw(){
    }
    @Override
    public void contentFreeze(boolean isFrozen){
        this.contentFreezeInPlace = true;
    }
    @Override
    public void pause(boolean isPaused){
    }
    @Override
    public void deleteEverything(){
    }
}
