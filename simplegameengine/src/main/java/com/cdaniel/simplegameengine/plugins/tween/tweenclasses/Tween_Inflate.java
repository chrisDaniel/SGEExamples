package com.cdaniel.simplegameengine.plugins.tween.tweenclasses;

import com.cdaniel.simplegameengine.plugins.tween.Tween;
import com.cdaniel.simplegameengine.plugins.tween.TweenEase;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Scale;

import java.util.HashMap;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public class Tween_Inflate extends AbstractPerSecondTween implements Tween {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float toValue;
    Transform_Scale scaleTransform;

    Tween_Inflate(float toPercent, float duration, TweenEase easer, HashMap<String, Object> props, int iterationsPerSecond){

        this.toValue = toPercent;
        this.duration = duration;
        this.iterationsPerSecond = iterationsPerSecond;
        this.easer = easer;
        this.applyProperties(props);

        scaleTransform = Transform_Scale.builder().build();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * After Attach
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    protected void afterAttach(){

        float scaleE = (float) Math.pow(toValue, 1.0 / totalIterations);
        scaleTransform.updateValues(scaleE, scaleE, scaleE);
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Execute the tween
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    protected void executeTween_II(float executionPercent){

        attachedTo.applyTransform(scaleTransform);
    }
    @Override
    protected void executeTween_Last(){
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static Tween_InflateBuilder builder() {
        return new Tween_InflateBuilder();
    }
    public static class Tween_InflateBuilder {

        private HashMap<String, Object> props = new HashMap<>();
        private float duration = 5000f;
        protected TweenEase easer;

        private int iterationsPerSecond = 7;

        private float toValue;

        public Tween_Inflate.Tween_InflateBuilder property(String propertyName, Object propertyValue) {
            this.props.put(propertyName, propertyValue);
            return this;
        }
        public Tween_Inflate.Tween_InflateBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public Tween_Inflate.Tween_InflateBuilder easer(TweenEase easer) {
            this.easer = easer;
            return this;
        }
        public Tween_Inflate.Tween_InflateBuilder iterationsPerSecond(int nbr) {
            this.iterationsPerSecond = nbr;
            return this;
        }
        public Tween_Inflate.Tween_InflateBuilder valueTo(float valueTo) {
            this.toValue = valueTo;
            return this;
        }

        public Tween_Inflate build() {
            return new Tween_Inflate(toValue, duration, easer, props, iterationsPerSecond);
        }
    }

}
