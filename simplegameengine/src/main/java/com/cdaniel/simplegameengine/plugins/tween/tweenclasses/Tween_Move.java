package com.cdaniel.simplegameengine.plugins.tween.tweenclasses;

import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.tween.Tween;
import com.cdaniel.simplegameengine.plugins.tween.TweenEase;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

import java.util.HashMap;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public class Tween_Move extends AbstractPerFrameTween implements Tween {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private SimpleVertex fromValue;
    private SimpleVertex toValue;

    private float deltaX = 0f;
    private float deltaY = 0f;
    private float deltaZ = 0f;

    private Transform_Move t;

    Tween_Move(Vertex to, float duration, TweenEase easer, HashMap<String, Object> props, int framesBetweenIterations){

        this.toValue = new SimpleVertex(to);
        this.duration = duration;
        this.framesBetweenIterations = framesBetweenIterations;
        this.easer = easer;
        this.applyProperties(props);

        t = Transform_Move.builder().build();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * After Attach
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    protected void afterAttach(){

        this.fromValue = new SimpleVertex(attachedTo.getCenter());
        float fr = SGE.properties().frameRate();
        deltaX = toValue.getX() - fromValue.getX();
        deltaY = toValue.getY() - fromValue.getY();
        deltaZ = toValue.getZ() - fromValue.getZ();
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Execute the tween
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    protected void executeTween_II(float executionPercent){

        SimpleVertex tweenVertex = new SimpleVertex(
                (fromValue.getX() + executionPercent * deltaX),
                (fromValue.getY() + executionPercent * deltaY),
                (fromValue.getZ() + executionPercent * deltaZ) );

        t.updateValues(tweenVertex);
        attachedTo.applyTransform(t);
    }
    @Override
    protected void executeTween_Last(){

        t.updateValues(toValue);
        attachedTo.applyTransform(t);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static Tween_MoveBuilder builder() {
        return new Tween_MoveBuilder();
    }
    public static class Tween_MoveBuilder {
        //todo - make all tween durations default to 0
        private HashMap<String, Object> props = new HashMap<>();
        private float duration = 5000f;
        protected TweenEase easer;

        private int framesBetweenIterations = 1;

        private Vertex toVertex;

        public Tween_Move.Tween_MoveBuilder property(String propertyName, Object propertyValue) {
            this.props.put(propertyName, propertyValue);
            return this;
        }
        public Tween_Move.Tween_MoveBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public Tween_Move.Tween_MoveBuilder easer(TweenEase easer) {
            this.easer = easer;
            return this;
        }
        public Tween_Move.Tween_MoveBuilder framesPerIteration(int nbrFrames) {
            this.framesBetweenIterations = nbrFrames;
            return this;
        }
        public Tween_Move.Tween_MoveBuilder moveTo(Vertex vertexTo) {
            this.toVertex = vertexTo;
            return this;
        }

        public Tween_Move build() {
            return new Tween_Move(toVertex, duration, easer, props, framesBetweenIterations);
        }
    }
}
