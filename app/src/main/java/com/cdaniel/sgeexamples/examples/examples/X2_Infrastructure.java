package com.cdaniel.sgeexamples.examples.examples;

import com.cdaniel.sgeexamples.examples.manager.Setup_Textures;
import com.cdaniel.simplegameengine.core.Color;
import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.directors_focus.DIR_PanLeftRight;
import com.cdaniel.simplegameengine.plugins.director.directors_focus.DIR_StillShot;
import com.cdaniel.simplegameengine.plugins.director.directors_movement.DIR_MoveTo;
import com.cdaniel.simplegameengine.plugins.director.directors_strategy.DIR_Orbit;
import com.cdaniel.simplegameengine.utils.constants.Constants;
import com.cdaniel.simplegameengine.utils.constructs.SimpleColor;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class X2_Infrastructure extends AbstractXample {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Vars
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Integer floor = null;
    private Integer wall = null;
    private ArrayList<Integer> mazeWalls = new ArrayList();

    private float wallH = 4.7f;

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * On Frame
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void onFrame() {

        //Part 1...
        //Show How the Walls/Floor Work
        if(SGE.properties().totalFrames() == 1){
            SGE.camera().changeDepthFocus(1f, 30f);
        }
        if (SGE.properties().totalFrames() == 2) {
            floor = SGE.construct().infrastructure().floor().leftX(-21f).rightX(21f).nearZ(21f).farZ(-21f).y(0).color(new SimpleColor(Color.CYAN)).build();
        }
        if (SGE.properties().totalFrames() == 3) {
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(5).toX(0).toY(28).toZ(60).build());
        }
        if (SGE.properties().totalFrames() == 50) {
            wall = SGE.construct().infrastructure().wall().startX(-3f).endX(3f).startZ(0f).endZ(0f).height(8f).thickness(1).color(new SimpleColor(Color.RED)).build();
        }
        if(SGE.properties().totalFrames() == 150){
            SGE.contents().remove(floor);
            SGE.contents().remove(wall);
        }
        if(SGE.properties().totalFrames() == 151){

            floor = SGE.construct().infrastructure().floor().leftX(-20f).rightX(20f).nearZ(20f).farZ(-20f).y(0).textureId(Setup_Textures.texture_birchwood).build();
            wall = SGE.construct().infrastructure().wall().startX(-3f).endX(3f).startZ(0f).endZ(0f).height(8f).thickness(1).textureId(Setup_Textures.texture_brickwall).build();
        }
        if(SGE.properties().totalFrames() == 275) {
            SGE.contents().remove(wall);
        }

        //Part 2...
        //Start Adding the Walls 1 by 1
        if(SGE.properties().totalFrames() == 300) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                                .startX(-18f).endX(-18f).startZ(-9f).endZ(-18f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 330) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                               .startX(-18f).endX(-13.7f).startZ(-9f).endZ(-9f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 360) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-10f).endX(-10f).startZ(-3f).endZ(-18f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 390) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-15f).endX(-10f).startZ(-3f).endZ(-3f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 420) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-18f).endX(-18f).startZ(-9f).endZ(18f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 450) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-18f).endX(-13.7f).startZ(5f).endZ(5f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 480) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-11f).endX(-7f).startZ(5f).endZ(5f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 510) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-1f).endX(5f).startZ(5f).endZ(5f).build();
            mazeWalls.add(w);
        }
        //angled wall ... no problem
        if(SGE.properties().totalFrames() == 540) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-7f).endX(-1f).startZ(5f).endZ(14f).build();
            mazeWalls.add(w);
        }
        if(SGE.properties().totalFrames() == 570) {
            int w = SGE.construct().infrastructure().wall().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                    .startX(-1f).endX(6f).startZ(14f).endZ(14f).build();
            mazeWalls.add(w);
        }

        //lets start moving around
        if(SGE.properties().totalFrames() == 425) {
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(15).toX(0).toY(64).toZ(3).build());
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(5).toX(0).toY(28).toZ(60).build());
            SGE.director().queueDirector(DIR_Orbit.builder().duration(8500).secondsPerRevolution(15).build());
        }


        //Part 3...
        //Add a Wall Sequence
        if(SGE.properties().totalFrames() == 630){

            ArrayList<Vertex> wallSequence = new ArrayList<>();

            //border
            wallSequence.add(new SimpleVertex(-10f, 0f, -18f));
            wallSequence.add(new SimpleVertex(  6f, 0f, -18f));
            wallSequence.add(new SimpleVertex(  6f, 0f, -18f));
            wallSequence.add(new SimpleVertex( 18f, 0f, -18f));

            wallSequence.add(new SimpleVertex( 18f, 0f, -18f));
            wallSequence.add(new SimpleVertex( 18f, 0f, -12f));
            wallSequence.add(new SimpleVertex( 18f, 0f, -12f));
            wallSequence.add(new SimpleVertex( 18f, 0f,  -6f));
            wallSequence.add(new SimpleVertex( 18f, 0f,  -6f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   0f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   0f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   6f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   6f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   12f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   12f));
            wallSequence.add(new SimpleVertex( 18f, 0f,   18f));

            wallSequence.add(new SimpleVertex( 18f, 0f,  18f));
            wallSequence.add(new SimpleVertex(-18f, 0f,  18f));

            //some more inner walls
            wallSequence.add(new SimpleVertex( -4f, 0f,   1f));
            wallSequence.add(new SimpleVertex( -4f, 0f,  -6f));
            wallSequence.add(new SimpleVertex( -4f, 0f,  -10f));
            wallSequence.add(new SimpleVertex( -4f, 0f,  -14f));

            List<Integer> seqWalls = SGE.construct().infrastructure().wall_sequence().height(wallH).thickness(1).textureId(Setup_Textures.texture_brickwall)
                                        .pushSequence(wallSequence).build();
            mazeWalls.addAll(seqWalls);
        }


        //Part 4...
        //Dance and Play
        if(SGE.properties().totalFrames() == 720){

            Map<String, Object> props = new HashMap<>();
            props.put(SGE.CONTENT_TEXTURE_ID, Setup_Textures.texture_cartoonBrickwall);

            for(int mazeWallId : mazeWalls){
                SGE.contents().get(mazeWallId).applyProperties(props);
            }
        }
        if(SGE.properties().totalFrames() == 1000){

            Map<String, Object> props = new HashMap<>();
            props.put(SGE.CONTENT_TEXTURE_ID, Setup_Textures.texture_orangeShine);

            for(int mazeWallId : mazeWalls){
                SGE.contents().get(mazeWallId).applyProperties(props);
            }
        }
        if(SGE.properties().totalFrames() == 1200){

            Map<String, Object> props = new HashMap<>();
            props.put(SGE.CONTENT_TEXTURE_ID, Setup_Textures.texture_metalPanel);

            SGE.contents().get(floor).applyProperties(props);
        }
        if(SGE.properties().totalFrames() == 1400){

            SGE.director().killAllDirectors();
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(5).toX(0).toY(28).toZ(60).build());
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(7).toX(0).toY(64).toZ(19).build());
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(3).toX(16f).toY(1.7f).toZ(-16).build());
            SGE.director().queueDirector(DIR_StillShot.builder().duration(3).build());
            SGE.director().queueDirector(DIR_PanLeftRight.builder().duration(4.8f).left(Constants.PI/3.2f).build());
            SGE.director().queueDirector(DIR_PanLeftRight.builder().duration(8.5f).right(Constants.PI/1.9f).build());
        }
        if(SGE.properties().totalFrames() == 1600){

            Map<String, Object> props = new HashMap<>();
            props.put(SGE.CONTENT_TEXTURE_ID, Setup_Textures.texture_birchwood);

            SGE.contents().get(floor).applyProperties(props);
        }
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Handle User Actions
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void handle_fingerSwipe(float dx, float dy){
        //dont react
    }
}
