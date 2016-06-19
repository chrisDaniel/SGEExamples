package com.cdaniel.simplegameengine.plugins.tween.tweenclasses;

import com.cdaniel.simplegameengine.core.Color;
import com.cdaniel.simplegameengine.plugins.tween.Tween;
import com.cdaniel.simplegameengine.plugins.tween.TweenEase;
import com.cdaniel.simplegameengine.utils.constructs.SimpleColor;

import java.util.HashMap;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public class Tween_ContentColor extends AbstractPerFrameTween implements Tween {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Color fromValue;
    private Color toValue;

    private float deltaR = 0f;
    private float deltaG = 0f;
    private float deltaB = 0f;
    private float deltaA = 0f;
    
    private SimpleColor transformColor = new SimpleColor();
    
    Tween_ContentColor(Color toColor, float duration, TweenEase easer, HashMap<String, Object> props, int framesBetweenIterations){

        this.toValue = toColor;
        this.duration = duration;
        this.framesBetweenIterations = framesBetweenIterations;
        this.easer = easer;
        this.applyProperties(props);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * After Attach
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    protected void afterAttach(){

        this.fromValue = new SimpleColor(attachedTo.getColor());

        deltaR = toValue.getRed()   - fromValue.getRed();
        deltaG = toValue.getGreen() - fromValue.getBlue();
        deltaB = toValue.getBlue()  - fromValue.getGreen();
        deltaA = toValue.getAlpha() - fromValue.getAlpha();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Execute the tween
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    protected void executeTween_II(float executionPercent){

        transformColor.setRed(fromValue.getRed() + executionPercent * deltaR);
        transformColor.setGreen(fromValue.getGreen() + executionPercent * deltaG);
        transformColor.setBlue(fromValue.getBlue() + executionPercent * deltaB);
        transformColor.setAlpha(fromValue.getAlpha() + executionPercent * deltaA);
        
        attachedTo.setColor(transformColor);
    }
    @Override
    protected void executeTween_Last(){

        attachedTo.setColor(this.toValue);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static Tween_ContentColorBuilder builder() {
        return new Tween_ContentColorBuilder();
    }
    public static class Tween_ContentColorBuilder {

        private HashMap<String, Object> props = new HashMap<>();
        private float duration = 10f;
        protected TweenEase easer;

        private int framesBetweenIterations = 1;

        private Color toColor;

        public Tween_ContentColor.Tween_ContentColorBuilder property(String propertyName, Object propertyValue) {
            this.props.put(propertyName, propertyValue);
            return this;
        }
        public Tween_ContentColor.Tween_ContentColorBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public Tween_ContentColor.Tween_ContentColorBuilder easer(TweenEase easer) {
            this.easer = easer;
            return this;
        }
        public Tween_ContentColor.Tween_ContentColorBuilder framesPerIteration(int nbrFrames) {
            this.framesBetweenIterations = nbrFrames;
            return this;
        }
        public Tween_ContentColor.Tween_ContentColorBuilder toColor(Color toColor) {
            this.toColor = toColor;
            return this;
        }

        public Tween_ContentColor build() {
            return new Tween_ContentColor(toColor, duration, easer, props, framesBetweenIterations);
        }
    }
}
