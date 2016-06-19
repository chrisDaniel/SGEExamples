package com.cdaniel.simplegameengine.plugins.tween.visualeffects;

import com.cdaniel.simplegameengine.core.Color;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.engine.SGEContentWrapper;
import com.cdaniel.simplegameengine.plugins.tween.Tween;
import com.cdaniel.simplegameengine.plugins.tween.TweenEase;
import com.cdaniel.simplegameengine.plugins.tween.TweenEffect;
import com.cdaniel.simplegameengine.plugins.tween.easers.Ease_Quadratic;
import com.cdaniel.simplegameengine.plugins.tween.easers.Ease_Root;
import com.cdaniel.simplegameengine.plugins.tween.tweenclasses.Tween_Move;
import com.cdaniel.simplegameengine.utils.constructs.SimpleColor;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import java.util.HashMap;

/**
 * Created by christopher.daniel on 5/15/16.
 */
public class Explode implements TweenEffect {



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private SimpleVertex ecv;
    private HashMap<Integer, Tween> tweens;

    private HashMap<String, Object> props;

    private int nbrParticles;
    private float particleSize;
    private float duration;
    private float range;
    private SimpleColor color;

    Explode(int nbrParticles, float particleSize, float range, float duration, Color color, HashMap<String, Object> props){

        this.nbrParticles = 20;
        this.particleSize = .2f;
        this.range = 50f;
        this.duration = 1;
        this.color = new SimpleColor(color);
    }
    public void attach(SGEContentWrapper contentWrapper){

        this.ecv = new SimpleVertex(contentWrapper.getCenter());
        this.createParticles();
        this.createTweens();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Build the 'Particles' and Their Tweens
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void createParticles() {

        tweens = new HashMap<>();

        for (int i=0; i<nbrParticles; i++) {

            int nextId = SGE.construct().shapes().triangle_2D().x(ecv.getX()).y(ecv.getY()).z(ecv.getZ()).size(particleSize).color(color).build();
            tweens.put(nextId, null);
        }
    }

    private void createTweens(){

        for (Integer particleId : tweens.keySet()){

            float randx = ecv.getX() + ( (float) negPos() * ((float) Math.random() / 1) * range);
            float randy = ecv.getY() + ( (float) negPos() * ((float) Math.random() / 1) * range);
            float randz = ecv.getZ() + ( (float) -1 * ((float) Math.random() / 1) * range);

            TweenEase easer = null;
            if (particleId % 3 == 0) {
                easer = new Ease_Quadratic(2);
            }
            else if (particleId % 3 == 1) {
                easer = new Ease_Root(2);
            }

            Tween_Move tween = Tween_Move.builder().moveTo(new SimpleVertex(randx, randy, randz)).duration(duration).easer(easer)
                                    .property(Tween.TWEEN_LOOP, Tween.TWEEN_LOOP_None)
                                    .property(Tween.TWEEN_REVERSE, Tween.TWEEN_REVERSE_No)
                                    .property(Tween.TWEEN_COMPLETE_ACT, Tween.TWEEN_COMPLETE_ACT_remove)
                                    .build();

            tweens.put(particleId, tween);
        }
    }

    @Override
    public void playEffect(){

        for (Integer particleId : tweens.keySet()){
            SGE.tweening().applyTween(particleId, tweens.get(particleId));
        }
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Private Helpers
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private int negPos(){
        if((Math.random() * 10) < 5){
            return 1;
        }
        return -1;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static ExplodeBuilder builder() {
        return new ExplodeBuilder();
    }
    public static class ExplodeBuilder {

        private HashMap<String, Object> props;

        private int nbrParticles = 20;
        private float particleSize = .3f;
        private float duration = .8f;
        private float range = 40f;
        private SimpleColor color = new SimpleColor(Color.GREEN);
        
        public Explode.ExplodeBuilder property(String propertyName, Object propertyValue) {
            this.props.put(propertyName, propertyValue);
            return this;
        }
        public Explode.ExplodeBuilder duration(float duration) {
            this.duration = duration;
            return this;
        }
        public Explode.ExplodeBuilder nbrParticles(int nbrParticles) {
            this.nbrParticles = nbrParticles;
            return this;
        }
        public Explode.ExplodeBuilder particleSize(float particleSize) {
            this.particleSize = particleSize;
            return this;
        }
        public Explode.ExplodeBuilder range(float range) {
            this.range = range;
            return this;
        }
        public Explode.ExplodeBuilder color(Color color) {
            this.color = new SimpleColor(color);
            return this;
        }

        public Explode build() {
            return new Explode(nbrParticles, particleSize, range, duration, color, props);
        }
    }
}
