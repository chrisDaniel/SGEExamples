package com.cdaniel.simplegameengine.plugins.tween.tweenclasses;

import com.cdaniel.simplegameengine.plugins.tween.Tween;
import com.cdaniel.simplegameengine.plugins.tween.TweenEase;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Scale;

import java.util.HashMap;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public class Tween_Scale extends AbstractPerSecondTween implements Tween {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float toXPercent;
    private float toYPercent;
    private float toZPercent;
    Transform_Scale scaleTransform;

    Tween_Scale(float toXPercent, float toYPercent, float toZPercent, float duration, TweenEase easer, HashMap<String, Object> props, int iterationsPerSecond){

        this.toXPercent = toXPercent;
        this.toYPercent = toYPercent;
        this.toZPercent = toZPercent;
        
        this.duration = duration;
        
        this.iterationsPerSecond = iterationsPerSecond;
        this.easer = easer;
        this.applyProperties(props);

        scaleTransform = Transform_Scale.builder().build();

        throw new RuntimeException("not ready");
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * After Attach
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    protected void afterAttach(){

        float scaleX = (float) Math.pow(toXPercent, 1.0 / totalIterations);
        float scaleY = (float) Math.pow(toYPercent, 1.0 / totalIterations);
        float scaleZ = (float) Math.pow(toZPercent, 1.0 / totalIterations);
        
        scaleTransform.updateValues(scaleX, scaleY, scaleZ);
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
        private float duration = 10f;
        protected TweenEase easer;

        private int iterationsPerSecond = 7;

        private float toXValue = 1f;
        private float toYValue = 1f;
        private float toZValue = 1f;

        public Tween_Scale.Tween_InflateBuilder property(String propertyName, Object propertyValue) {
            this.props.put(propertyName, propertyValue);
            return this;
        }
        public Tween_Scale.Tween_InflateBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public Tween_Scale.Tween_InflateBuilder easer(TweenEase easer) {
            this.easer = easer;
            return this;
        }
        public Tween_Scale.Tween_InflateBuilder iterationsPerSecond(int nbr) {
            this.iterationsPerSecond = nbr;
            return this;
        }
        public Tween_Scale.Tween_InflateBuilder valueXTo(float valueToX) {
            this.toXValue = valueToX;
            return this;
        }
        public Tween_Scale.Tween_InflateBuilder valueYTo(float valueToY) {
            this.toYValue = valueToY;
            return this;
        }
        public Tween_Scale.Tween_InflateBuilder valueZTo(float valueToZ) {
            this.toZValue = valueToZ;
            return this;
        }

        public Tween_Scale build() {
            return new Tween_Scale(toXValue, toYValue, toZValue, duration, easer, props, iterationsPerSecond);
        }
    }

}
