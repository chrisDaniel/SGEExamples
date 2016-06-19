package com.cdaniel.simplegameengine.plugins.tween.tweenclasses;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.engine.SGEContentWrapper;
import com.cdaniel.simplegameengine.plugins.tween.Tween;
import com.cdaniel.simplegameengine.plugins.tween.TweenEase;
import com.cdaniel.simplegameengine.utils.constants.Constants;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

import java.util.HashMap;

/**
 * Created by christopher.daniel on 5/8/16.
 */
public class Tween_Orbit extends AbstractPerFrameTween implements Tween {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private SGEContentWrapper anchorContent;
    private float thruRadians;
    private float orbitRadius;

    private Transform_Move t;

    private Tween_Orbit(int anchorContentId, float thruRadians, float orbitRadius, float duration,
                       TweenEase easer, HashMap<String, Object> props, int framesBetweenIterations){

        anchorContent = SGE.contents().get(anchorContentId);

        this.thruRadians = thruRadians;
        this.duration = duration;
        this.orbitRadius = orbitRadius;
        this.easer = easer;
        this.framesBetweenIterations = framesBetweenIterations;

        this.applyProperties(props);

        t = Transform_Move.builder().build();
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

        float radians = thruRadians * executionPercent;

        float orbitX = (float) Math.cos(radians) * orbitRadius + anchorContent.getCenter().getX();
        float orbitZ = (float) Math.sin(radians) * orbitRadius + anchorContent.getCenter().getZ();
        float orbitY = anchorContent.getCenter().getY();

        t.updateValues(new SimpleVertex(orbitX, orbitY, orbitZ));
        attachedTo.applyTransform(t);
    }
    @Override
    protected void executeTween_Last(){
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static Tween_OrbitBuilder builder() {
        return new Tween_OrbitBuilder();
    }
    public static class Tween_OrbitBuilder {

        private HashMap<String, Object> props = new HashMap<>();
        private float duration = 5000f;
        protected TweenEase easer;

        private int framesBetweenIterations = 1;

        private int anchorContentId;
        private float thruRadians = 2*Constants.PI;
        private float orbitRadius;

        public Tween_Orbit.Tween_OrbitBuilder property(String propertyName, Object propertyValue) {
            this.props.put(propertyName, propertyValue);
            return this;
        }
        public Tween_Orbit.Tween_OrbitBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public Tween_Orbit.Tween_OrbitBuilder easer(TweenEase easer) {
            this.easer = easer;
            return this;
        }
        public Tween_Orbit.Tween_OrbitBuilder framesPerIteration(int nbrFrames) {
            this.framesBetweenIterations = nbrFrames;
            return this;
        }
        public Tween_Orbit.Tween_OrbitBuilder anchorContentId(int anchorContentId) {
            this.anchorContentId = anchorContentId;
            return this;
        }
        public Tween_Orbit.Tween_OrbitBuilder thruRadians(float thruAngle) {
            this.thruRadians = thruAngle;
            return this;
        }
        public Tween_Orbit.Tween_OrbitBuilder orbitRadius(float orbitRadius) {
            this.orbitRadius = orbitRadius;
            return this;
        }

        public Tween_Orbit build() {
            return new Tween_Orbit(anchorContentId, thruRadians, orbitRadius, duration, easer, props, framesBetweenIterations);
        }
    }
}
