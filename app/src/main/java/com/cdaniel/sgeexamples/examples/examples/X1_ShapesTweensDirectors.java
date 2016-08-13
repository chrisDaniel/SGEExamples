package com.cdaniel.sgeexamples.examples.examples;

import com.cdaniel.sgeexamples.examples.manager.Setup_Textures;
import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.directors_movement.DIR_MoveTo;
import com.cdaniel.simplegameengine.plugins.director.directors_movement.DIR_MoveToContent;
import com.cdaniel.simplegameengine.plugins.director.directors_strategy.DIR_FollowBehind;
import com.cdaniel.simplegameengine.plugins.director.directors_strategy.DIR_Orbit;
import com.cdaniel.simplegameengine.plugins.tween.Tween;
import com.cdaniel.simplegameengine.plugins.tween.easers.Ease_Quadratic;
import com.cdaniel.simplegameengine.plugins.tween.easers.Ease_Root;
import com.cdaniel.simplegameengine.plugins.tween.tweenclasses.Tween_Inflate;
import com.cdaniel.simplegameengine.plugins.tween.tweenclasses.Tween_Move;
import com.cdaniel.simplegameengine.plugins.tween.tweenclasses.Tween_Orbit;
import com.cdaniel.simplegameengine.plugins.tween.tweenclasses.Tween_Rotate;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class X1_ShapesTweensDirectors extends AbstractXample {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Variables
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Integer shape1 = null;
    private Integer shape2 = null;
    private Integer shape3 = null;
    private Integer shape4 = null;

    private Integer tween;


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * On Frame
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void onFrame() {

        if(SGE.properties().totalFrames() == 1){
            SGE.camera().changeDepthFocus(1f, 60f);
        }
        if (SGE.properties().totalFrames() == 2) {
            shape1 = SGE.construct().shapes().sphere().size(1.6f).textureId(Setup_Textures.texture1).build();
        }
        if(SGE.properties().totalFrames() == 14){
            Tween_Rotate rotate = Tween_Rotate.builder().framesPerIteration(1)
                    .property(Tween.TWEEN_LOOP, Tween.TWEEN_LOOP_Repeat_Infinite).property(Tween.TWEEN_REVERSE, Tween.TWEEN_REVERSE_No)
                    .build();
            SGE.tweening().applyTween(shape1, rotate);

        }
        if (SGE.properties().totalFrames() == 32) {
            shape2 = SGE.construct().shapes().sphere().size(.7f).x(5f).textureId(Setup_Textures.texture2).build();
        }
        if(SGE.properties().totalFrames() == 40){
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(5).toX(-18f).toY(13f).toZ(-3f).build());
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(5).toX(-5f).toY(10f).toZ(-26f).build());
        }
        if(SGE.properties().totalFrames() == 200){
            Tween_Orbit orbit = Tween_Orbit.builder().orbitRadius(5).anchorContentId(shape1).duration(8)
                    .property(Tween.TWEEN_LOOP, Tween.TWEEN_LOOP_Repeat_Infinite).property(Tween.TWEEN_REVERSE, Tween.TWEEN_REVERSE_No)
                    .build();
            SGE.tweening().applyTween(shape2, orbit);
        }
        if(SGE.properties().totalFrames() == 252){
            Tween_Move tween = Tween_Move.builder().duration(4).easer(new Ease_Quadratic(2)).moveTo(new SimpleVertex(4, 4, 4))
                    .property(Tween.TWEEN_LOOP, Tween.TWEEN_LOOP_Repeat_Infinite).property(Tween.TWEEN_REVERSE, Tween.TWEEN_REVERSE_Yes)
                    .build();
            this.tween = SGE.tweening().applyTween(shape1, tween);
        }
        if(SGE.properties().totalFrames() == 800){
            SGE.tweening().killTween(tween);
            shape3 = SGE.construct().shapes().sphere().textureId(Setup_Textures.texture3)
                    .materialEmmissive(new float[]{.9f, .9f, .9f, .9f}).build();
            shape4 = SGE.construct().shapes().sphere().x(25f).textureId(Setup_Textures.texture4).build();
        }
        if(SGE.properties().totalFrames() == 802){
            Tween_Inflate inflate = Tween_Inflate.builder().duration(6).easer(new Ease_Root(2)).valueTo(3f).build();
            SGE.tweening().applyTween(shape3, inflate);
        }
        if(SGE.properties().totalFrames() == 803){
            Tween_Orbit orbit1 = Tween_Orbit.builder().orbitRadius(9).anchorContentId(shape3).duration(13).framesPerIteration(1)
                    .property(Tween.TWEEN_LOOP, Tween.TWEEN_LOOP_Repeat_Infinite).property(Tween.TWEEN_REVERSE, Tween.TWEEN_REVERSE_No)
                    .build();
            SGE.tweening().applyTween(shape1, orbit1);

            Tween_Orbit orbit_4 = Tween_Orbit.builder().orbitRadius(25).anchorContentId(shape3).duration(89)
                    .property(Tween.TWEEN_LOOP, Tween.TWEEN_LOOP_Repeat_Infinite).property(Tween.TWEEN_REVERSE, Tween.TWEEN_REVERSE_No)
                    .build();
            SGE.tweening().applyTween(shape4, orbit_4);
        }
        if(SGE.properties().totalFrames() == 804) {
            Tween_Rotate rotate = Tween_Rotate.builder().property(Tween.TWEEN_LOOP, Tween.TWEEN_LOOP_Repeat_Infinite).property(Tween.TWEEN_REVERSE, Tween.TWEEN_REVERSE_No).build();
            SGE.tweening().applyTween(shape4, rotate);
        }
        if(SGE.properties().totalFrames() == 805) {

            Tween_Rotate rotate = Tween_Rotate.builder().property(Tween.TWEEN_LOOP, Tween.TWEEN_LOOP_Repeat_Infinite).property(Tween.TWEEN_REVERSE, Tween.TWEEN_REVERSE_No).build();
            SGE.tweening().applyTween(shape3, rotate);
        }
        if(SGE.properties().totalFrames() == 806){
            Tween_Move tween = Tween_Move.builder().duration(4).easer(new Ease_Quadratic(2)).moveTo(new SimpleVertex(3, -2, 1)).build();
            this.tween = SGE.tweening().applyTween(shape3, tween);
        }
        if(SGE.properties().totalFrames() == 900){
            SimpleVertex jupiterCenter = new SimpleVertex(SGE.contents().get(shape4).getCenter());
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(7).toX(jupiterCenter.getX()).toY(jupiterCenter.getY()).toZ(jupiterCenter.getZ()).build());
        }
        if(SGE.properties().totalFrames() == 1350){
            Tween_Move tween = Tween_Move.builder().duration(4).easer(new Ease_Quadratic(2)).moveTo(new SimpleVertex(-6, 4, -4))
                    .property(Tween.TWEEN_LOOP, Tween.TWEEN_LOOP_Repeat_Infinite).property(Tween.TWEEN_REVERSE, Tween.TWEEN_REVERSE_Yes)
                    .build();
            this.tween = SGE.tweening().applyTween(shape3, tween);

            SimpleVertex jupiterCenter = new SimpleVertex(SGE.contents().get(shape4).getCenter());
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(14).toX(jupiterCenter.getX()).toY(jupiterCenter.getY()).toZ(jupiterCenter.getZ()).build());
        }
        if(SGE.properties().totalFrames() == 1699){
            SGE.tweening().killTween(tween);
        }
        if(SGE.properties().totalFrames() == 1999) {
            SGE.director().queueDirector(DIR_Orbit.builder().duration(8).secondsPerRevolution(12).build());
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(5).toX(1).toY(20).toZ(-1).build());
            SGE.director().queueDirector(DIR_MoveToContent.builder().contentId(shape4).duration(5).build());

            //DIR_FollowBehind followEarth = DIR_FollowBehind.builder().contentIdToFollow(shape2).offsetX(2f).trailDistance(25).build();
            //SGE.director().queueDirector(followEarth);
        }
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * User Controls
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void onFingerSlide(float dx, float dy) {
        //doNothing;
    }

    @Override
    public void onJoystickControl(Vector joyVector) {
        //doNothing
    }
}
