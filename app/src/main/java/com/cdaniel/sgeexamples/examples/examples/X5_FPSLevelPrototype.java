package com.cdaniel.sgeexamples.examples.examples;

import com.cdaniel.sgeexamples.examples.manager.Setup_Textures;
import com.cdaniel.simplegameengine.core.Color;
import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.builders.BuilderTube;
import com.cdaniel.simplegameengine.plugins.construction.texturizer.Texturizer;
import com.cdaniel.simplegameengine.plugins.construction.texturizer.Texturizer_repeatExactly;
import com.cdaniel.simplegameengine.plugins.construction.texturizer.Texturizer_repeatPerTextureDimensions;
import com.cdaniel.simplegameengine.plugins.director.directors_focus.DIR_LookAtVertex;
import com.cdaniel.simplegameengine.plugins.director.directors_focus.DIR_PanUpDown;
import com.cdaniel.simplegameengine.plugins.director.directors_movement.DIR_Dolly;
import com.cdaniel.simplegameengine.plugins.director.directors_movement.DIR_MoveTo;
import com.cdaniel.simplegameengine.plugins.director.directors_movement.DIR_MoveToContent;
import com.cdaniel.simplegameengine.plugins.tween.Tween;
import com.cdaniel.simplegameengine.plugins.tween.tweenclasses.Tween_ContentColor;
import com.cdaniel.simplegameengine.plugins.tween.tweenclasses.Tween_Move;
import com.cdaniel.simplegameengine.utils.constants.Constants;
import com.cdaniel.simplegameengine.utils.constructs.SimpleColor;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by christopher.daniel on 6/2/16.
 */
public class X5_FPSLevelPrototype extends AbstractXample {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Vars
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Integer floor = null;
    private ArrayList<Integer> walls = new ArrayList<>();
    private ArrayList<Integer> platforms = new ArrayList<>();
    private HashMap<Integer, Integer> hooks = new HashMap<>();

    private final float leftX  = -55f;
    private final float rightX =  55f;
    private final float nearZ  =  20f;
    private final float farZ   = -70f;
    private final float height =  260f;

    private Color fogColor = new SimpleColor(.3f, .3f, .3f, 1f);

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * On Frame
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void onFrame() {

        if (SGE.properties().totalFrames() == 1) {
            SGE.otherGL().setClearColor(fogColor);
            SGE.otherGL().setCulling(false);
            SGE.otherGL().setFogEnabled(true);
            SGE.otherGL().setFogColor(fogColor);
            SGE.otherGL().setFogDensity(.9f);
            SGE.otherGL().setFogStartAt(SGE.camera().getFrustFar()*.5f);
            SGE.otherGL().setFogEndAt(SGE.camera().getFrustFar());
        }

        if (SGE.properties().totalFrames() == 1) {
            buildLevel();
        }
        if (SGE.properties().totalFrames() == 2) {
            buildColumns();
        }
        if (SGE.properties().totalFrames() == 3) {
            buildHooks();
        }
        if(SGE.properties().totalFrames() == 5){

            SGE.director().killAllDirectors();
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(3).toX(-1f).toY(height/2f).toZ(19).build());
            SGE.director().queueDirector(DIR_LookAtVertex.builder().toY(height/2f).build());
        }
    }

    private void buildLevel(){

        //Floor
        floor = SGE.construct().infrastructure().floor()
                .leftX(leftX).rightX(rightX).nearZ(nearZ).farZ(farZ).y(0)
                .texturizer(new Texturizer_repeatPerTextureDimensions(Setup_Textures.tile_black, 3f, 3f, rightX - leftX, nearZ-farZ))
                .build();

        //Walls
        Texturizer t_W1 = new Texturizer_repeatPerTextureDimensions(Setup_Textures.tile_blueMetal, 5f, 5f, nearZ-farZ, height);
        Texturizer t_W2 = new Texturizer_repeatExactly(Setup_Textures.tile_black, 1, 3);
        

        float midpoint = height/2f;
        walls.add(SGE.construct().infrastructure().wall().startX(leftX).endX(leftX).startZ(nearZ).endZ(farZ).y(0f).height(height).thickness(.01f)
                .texturizer(t_W1).materialEmmissive(.7f,.7f,1f,.7f).build());
        walls.add(SGE.construct().infrastructure().wall().startX(leftX+.01f).endX(leftX+.01f).startZ(nearZ).endZ(farZ).y(midpoint+15.5f).height(7f).thickness(.08f)
                .texturizer(t_W2).build());
        walls.add(SGE.construct().infrastructure().wall().startX(leftX+.02f).endX(leftX+.02f).startZ(nearZ).endZ(farZ).y(midpoint+14.5f).height(1f)
                .color(new SimpleColor(Color.HA_BLUEDEEP)).build());
        walls.add(SGE.construct().infrastructure().wall().startX(leftX+.02f).endX(leftX+.02f).startZ(nearZ).endZ(farZ).y(midpoint+22.5f).height(1f)
                .color(new SimpleColor(Color.HA_BLUEDEEP)).build());

        walls.add(SGE.construct().infrastructure().wall().startX(leftX).endX(rightX).startZ(farZ).endZ(farZ).y(0f).height(height).thickness(.01f)
                .texturizer(t_W1).materialEmmissive(.7f,.7f,1f,.7f).build());

        walls.add(SGE.construct().infrastructure().wall().startX(rightX).endX(rightX).startZ(nearZ).endZ(farZ).y(0f).height(height).thickness(.08f)
                .texturizer(t_W1).materialEmmissive(.7f,.7f,1f,.7f).build());
        walls.add(SGE.construct().infrastructure().wall().startX(rightX-.01f).endX(rightX-.01f).startZ(nearZ).endZ(farZ).y(midpoint+15.5f).height(7f).thickness(.08f)
                .texturizer(t_W2).build());
        walls.add(SGE.construct().infrastructure().wall().startX(rightX-.02f).endX(rightX-.02f).startZ(nearZ).endZ(farZ).y(midpoint+14.5f).height(1f)
                .color(new SimpleColor(Color.HA_BLUEDEEP)).build());
        walls.add(SGE.construct().infrastructure().wall().startX(rightX-.02f).endX(rightX-.02f).startZ(nearZ).endZ(farZ).y(midpoint+22.25f).height(1f)
                .color(new SimpleColor(Color.HA_BLUEDEEP)).build());


        walls.add(SGE.construct().infrastructure().wall().startX(leftX).endX(rightX).startZ(nearZ).endZ(nearZ).y(0f).height(height)
                .texturizer(t_W1).materialEmmissive(.7f,.7f,1f,.7f).build());

        addWallSignage();
    }
    private void addWallSignage(){

        float yMin = height/2f + 16f;

        //left wall
        for(int i = 0; i<12; i++) {

            float randY = 6.5f * (float) Math.random();
            float myY = yMin + randY;

            float randD = 6f + 8f * (float) Math.random();

            Color c = new SimpleColor(Color.WHITE);
            if(i%3==1){
                c = new SimpleColor(Color.RED);
            }
            if(i%7==1){
                c = new SimpleColor(Color.GREEN);
            }

            int contId = SGE.construct().signs().rectangleSign()
                    .width(.3f).height(.3f).center(new SimpleVertex(leftX + .06f, myY, nearZ))
                    .normalPointer(new SimpleVertex(1f, 0f, 0f))
                    .color(c).build();

            Tween_Move mover = Tween_Move.builder().duration(randD)
                    .moveTo(new SimpleVertex(leftX + .06f, myY, farZ))
                    .property(Tween.TWEEN_LOOP, Tween.TWEEN_LOOP_Repeat_Infinite)
                    .build();
            SGE.tweening().applyTween(contId, mover);
        }

        //right wall
        for(int i = 0; i<12; i++) {

            float randY = 6.5f * (float) Math.random();
            float myY = yMin + randY;

            float randD = 9f + 10f * (float) Math.random();

            Color c = new SimpleColor(Color.WHITE);
            if(i%3==1){
                c = new SimpleColor(Color.RED);
            }
            if(i%7==1){
                c = new SimpleColor(Color.GREEN);
            }

            int contId = SGE.construct().signs().rectangleSign()
                    .width(.3f).height(.3f).center(new SimpleVertex(rightX - .06f, myY, nearZ))
                    .normalPointer(new SimpleVertex(-1f, 0f, 0f))
                    .color(c).build();

            Tween_Move mover = Tween_Move.builder().duration(randD)
                    .moveTo(new SimpleVertex(rightX - .06f, myY, farZ))
                    .property(Tween.TWEEN_LOOP, Tween.TWEEN_LOOP_Repeat_Infinite)
                    .build();
            SGE.tweening().applyTween(contId, mover);
        }


    }
    private void buildColumns() {

        buildAColumns(   -10f,  0f,   0f,  8f,   height );
        buildAColumns(    10f,  0f,   0f,  8f,   height );
    }
    private void buildAColumns(float x, float y, float z, float platformSize, float height){

        float currHeight = 0f;
        int stackCount = 0;

        while(currHeight < height){

            stackCount++;

            if(stackCount%5 == 4) {

                float nextStep = currHeight + 1.25f;

                int oTube = SGE.construct().infrastructure().tube()
                        .radius(platformSize / 2f)
                        .fromCenter(new SimpleVertex(x, currHeight, z))
                        .toCenter(new SimpleVertex(x, nextStep, z))
                        .tubeType(BuilderTube.TubeType.Octagon)
                        .color(new SimpleColor(Color.YELLOW))
                        .materialEmmissive(.8f, .8f, .8f, .9f)
                        .build();
                platforms.add(oTube);

                currHeight = nextStep;
            }
            else{

                float nextStep = currHeight + 5f;

                int oTube = SGE.construct().infrastructure().tube()
                        .radius(platformSize / 2f)
                        .fromCenter(new SimpleVertex(x, currHeight, z))
                        .toCenter(new SimpleVertex(x, nextStep, z))
                        .tubeType(BuilderTube.TubeType.Octagon)
                        .textureId(Setup_Textures.texture_metalPanel)
                        //.materialEmmissive(.5f, .5f, .5f, .9f)
                        .build();
                platforms.add(oTube);

                currHeight = nextStep;
            }
        }

        addColumnPlatform();
    }

    private void addColumnPlatform(){

        float midPoint = height / 2f;
        SGE.construct().infrastructure().floor().thickness(1f).y(midPoint-3f)
                .leftX(0).rightX(7f).nearZ(1.5f).farZ(-1.5f).color(new SimpleColor(Color.WHITE)).build();

        SGE.construct().infrastructure().floor().thickness(1f).y(midPoint-3f)
                .leftX(-7f).rightX(0f).nearZ(1.5f).farZ(-1.5f).color(new SimpleColor(Color.WHITE)).build();
    }

    private void buildHooks(){

        buildHangingHook(   0f,  0f,   12f );
        buildHangingHook(   6f,  6f,   23f );
        buildHangingHook(   6f,  -6f,  9f  );
        buildHangingHook( -12f,  -3f,  17f );
        buildHangingHook( -25f,  -25f, 17f );
        buildHangingHook(  20f,  25f,  10f );
        buildHangingHook(  20f,  -9f,  15f );
    }
    private void buildHangingHook(float x, float z, float hookY){

        hookY = hookY + height/2f + .5f;
        Vertex center = new SimpleVertex(x, hookY, z);

        float ropeHeight = height - hookY;
        SGE.construct().infrastructure().column_square().widthX(.05f).lengthZ(.05f).heightY(ropeHeight).y(hookY)
                                     .xzPosition(center)
                                     .textureId(Setup_Textures.texture_rope)
                                     .build();

        int hook = SGE.construct().shapes().sphere().size(.3f).x(x).y(hookY).z(z).color(new SimpleColor(Color.GRAY)).build();
        hooks.put(hook, hook);

        SGE.physics().activator().contentId(hook).activate();
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Handle Interaction
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private int lookingAtHook;
    private void handleDxDy(){

        int _lookingAt = SGE.physics().performLookTest();

        if(_lookingAt != lookingAtHook){
            handleDxDy_highLight(lookingAtHook, _lookingAt);
            lookingAtHook = _lookingAt;
        }
    }
    private void handleDxDy_highLight(int removingFrom, int addingTo) {

        if(removingFrom > 0){
            SGE.tweening().killTweenForContent(removingFrom);
            SGE.tweening().applyTween(removingFrom, Tween_ContentColor.builder().toColor(new SimpleColor(Color.GRAY)).duration(1.5f).build());
        }
        if(addingTo > 0){
            SGE.tweening().killTweenForContent(addingTo);
            SGE.tweening().applyTween(addingTo, Tween_ContentColor.builder().toColor(new SimpleColor(Color.GREEN)).duration(1.5f).build());
        }
    }
    private void handleClick(){

        if(lookingAtHook <= 0){
            return;
        }

        float distAway = SGE.devTools().calc_distanceFromCamera(lookingAtHook);
        if(distAway >= 25){
           // return;
        }

        SGE.director().queueDirector(DIR_MoveToContent.builder().contentId(lookingAtHook).duration(2f).build());
        SGE.director().queueDirector(DIR_LookAtVertex.builder().toY(-2f).duration(1f).build());
        SGE.director().queueDirector(DIR_Dolly.builder().dolly(2f).duration(1f).build());
        SGE.director().queueDirector(DIR_PanUpDown.builder().up(Constants.PI/2.2f).duration(1f).build());
    }
}
