package com.cdaniel.simplegameengine.plugins.tween.tweenclasses;

import com.cdaniel.simplegameengine.plugins.tween.Tween;
import com.cdaniel.simplegameengine.plugins.tween.TweenEase;
import com.cdaniel.simplegameengine.utils.constants.Constants;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Rotate;

import java.util.HashMap;

/**
 * Created by christopher.daniel on 5/9/16.
 */
public class Tween_Rotate extends AbstractPerFrameTween implements Tween {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float thruRadians;
    private Transform_Rotate t;

    private Tween_Rotate(float thruRadians, float duration,
                         TweenEase easer, HashMap<String, Object> props, int framesBetweenIterations){

        this.thruRadians = thruRadians;
        this.duration = duration;
        this.easer = easer;
        this.framesBetweenIterations = framesBetweenIterations;

        this.applyProperties(props);

        t = Transform_Rotate.builder().build();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * After Attach
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    @Override
    protected void afterAttach(){

    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Execute the tween
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    protected void executeTween_II(float executionPercent){

        float radians = thruRadians / 400;
        t.updateValues(radians);

        attachedTo.applyTransform(t);
    }
    @Override
    protected void executeTween_Last(){
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static Tween_RotateBuilder builder() {
        return new Tween_RotateBuilder();
    }
    public static class Tween_RotateBuilder {

        private HashMap<String, Object> props = new HashMap<>();
        private float duration = 5000f;
        protected TweenEase easer;

        private int framesBetweenIterations = 1;

        private float thruRadians = 2 * Constants.PI;

        public Tween_Rotate.Tween_RotateBuilder property(String propertyName, Object propertyValue) {
            this.props.put(propertyName, propertyValue);
            return this;
        }
        public Tween_Rotate.Tween_RotateBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public Tween_Rotate.Tween_RotateBuilder easer(TweenEase easer) {
            this.easer = easer;
            return this;
        }
        public Tween_Rotate.Tween_RotateBuilder framesPerIteration(int nbrFrames) {
            this.framesBetweenIterations = nbrFrames;
            return this;
        }
        public Tween_Rotate.Tween_RotateBuilder thruRadians(float thruAngle) {
            this.thruRadians = thruAngle;
            return this;
        }

        public Tween_Rotate build() {
            return new Tween_Rotate(thruRadians, duration, easer, props, framesBetweenIterations);
        }
    }
}
