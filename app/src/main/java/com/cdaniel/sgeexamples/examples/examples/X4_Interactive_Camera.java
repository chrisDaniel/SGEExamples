package com.cdaniel.sgeexamples.examples.examples;

import com.cdaniel.sgeexamples.examples.manager.Setup_Textures;
import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.directors_focus.DIR_LookAtVertex;
import com.cdaniel.simplegameengine.plugins.director.directors_movement.DIR_MoveTo;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class X4_Interactive_Camera extends AbstractXample {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Vars
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Integer floor = null;
    private Integer ceiling = null;
    private ArrayList<Integer> mazeWalls = new ArrayList();

    private float wallH = 5.5f;

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * On Frame
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void onFrame() {

        //Part 1...
        //Show How the Walls/Floor Work
        if (SGE.properties().totalFrames() == 2) {

            floor = SGE.construct().infrastructure().floor().leftX(-20f).rightX(20f).nearZ(20f).farZ(-20f).y(0).textureId(Setup_Textures.texture_birchwood).build();

            ArrayList<Vertex> wallSequence = new ArrayList<>();

            //Left Border
            wallSequence.add(new SimpleVertex(-18f, 0f, -18f));
            wallSequence.add(new SimpleVertex(-18f, 0f, -12f));
            wallSequence.add(new SimpleVertex(-18f, 0f, -12f));
            wallSequence.add(new SimpleVertex(-18f, 0f, -6f));
            wallSequence.add(new SimpleVertex(-18f, 0f, -6f));
            wallSequence.add(new SimpleVertex(-18f, 0f, 0f));
            wallSequence.add(new SimpleVertex(-18f, 0f, 0f));
            wallSequence.add(new SimpleVertex(-18f, 0f, 6f));
            wallSequence.add(new SimpleVertex(-18f, 0f, 6f));
            wallSequence.add(new SimpleVertex(-18f, 0f, 12f));
            wallSequence.add(new SimpleVertex(-18f, 0f, 12f));
            wallSequence.add(new SimpleVertex(-18f, 0f, 18f));

            //Right Border
            wallSequence.add(new SimpleVertex(18f, 0f, -18f));
            wallSequence.add(new SimpleVertex(18f, 0f, -12f));
            wallSequence.add(new SimpleVertex(18f, 0f, -12f));
            wallSequence.add(new SimpleVertex(18f, 0f, -6f));
            wallSequence.add(new SimpleVertex(18f, 0f, -6f));
            wallSequence.add(new SimpleVertex(18f, 0f, 0f));
            wallSequence.add(new SimpleVertex(18f, 0f, 0f));
            wallSequence.add(new SimpleVertex(18f, 0f, 6f));
            wallSequence.add(new SimpleVertex(18f, 0f, 6f));
            wallSequence.add(new SimpleVertex(18f, 0f, 12f));
            wallSequence.add(new SimpleVertex(18f, 0f, 12f));
            wallSequence.add(new SimpleVertex(18f, 0f, 18f));

            //Bottom Border
            wallSequence.add(new SimpleVertex(-18f, 0f, 18f));
            wallSequence.add(new SimpleVertex(-12f, 0f, 18f));
            wallSequence.add(new SimpleVertex(-12f, 0f, 18f));
            wallSequence.add(new SimpleVertex(-6f, 0f, 18f));
            wallSequence.add(new SimpleVertex(-6f, 0f, 18f));
            wallSequence.add(new SimpleVertex(0f, 0f, 18f));
            wallSequence.add(new SimpleVertex(0f, 0f, 18f));
            wallSequence.add(new SimpleVertex(6f, 0f, 18f));
            wallSequence.add(new SimpleVertex(6f, 0f, 18f));
            wallSequence.add(new SimpleVertex(12f, 0f, 18f));
            wallSequence.add(new SimpleVertex(12f, 0f, 18f));
            wallSequence.add(new SimpleVertex(18f, 0f, 18f));

            //Far Border
            wallSequence.add(new SimpleVertex(-18f, 0f, -18f));
            wallSequence.add(new SimpleVertex(-12f, 0f, -18f));
            wallSequence.add(new SimpleVertex(-12f, 0f, -18f));
            wallSequence.add(new SimpleVertex(-6f, 0f, -18f));
            wallSequence.add(new SimpleVertex(-6f, 0f, -18f));
            wallSequence.add(new SimpleVertex(0f, 0f, -18f));
            wallSequence.add(new SimpleVertex(0f, 0f, -18f));
            wallSequence.add(new SimpleVertex(6f, 0f, -18f));
            wallSequence.add(new SimpleVertex(6f, 0f, -18f));
            wallSequence.add(new SimpleVertex(12f, 0f, -18f));
            wallSequence.add(new SimpleVertex(12f, 0f, -18f));
            wallSequence.add(new SimpleVertex(18f, 0f, -18f));

            //inner walls
            wallSequence.add(new SimpleVertex(-18f, 0f, -9f));
            wallSequence.add(new SimpleVertex(-13.7f, 0f, -9f));
            wallSequence.add(new SimpleVertex(-10f, 0f, -3f));
            wallSequence.add(new SimpleVertex(-10f, 0f, -18f));
            wallSequence.add(new SimpleVertex(-15f, 0f, -3f));
            wallSequence.add(new SimpleVertex(-10f, 0f, -3f));
            wallSequence.add(new SimpleVertex(-18f, 0f, 5f));
            wallSequence.add(new SimpleVertex(-13.7f, 0f, 5f));
            wallSequence.add(new SimpleVertex(-11f, 0f, 5f));
            wallSequence.add(new SimpleVertex(-7f, 0f, 5f));
            wallSequence.add(new SimpleVertex(-1f, 0f, 5f));
            wallSequence.add(new SimpleVertex(5f, 0f, 5f));
            wallSequence.add(new SimpleVertex(-7f, 0f, 5f));
            wallSequence.add(new SimpleVertex(-1f, 0f, 14f));

            wallSequence.add(new SimpleVertex(-4f, 0f, 1f));
            wallSequence.add(new SimpleVertex(-4f, 0f, -6f));
            wallSequence.add(new SimpleVertex(-4f, 0f, -10f));
            wallSequence.add(new SimpleVertex(-4f, 0f, -14f));

            List<Integer> seqWalls = SGE.construct().infrastructure().wall_sequence().height(wallH).thickness(1).textureId(Setup_Textures.texture_orangeShine)
                    .pushSequence(wallSequence).build();
            mazeWalls.addAll(seqWalls);
        }

        if (SGE.properties().totalFrames() == 5) {

            SGE.director().killAllDirectors();
            SGE.director().queueDirector(DIR_MoveTo.builder().duration(3).toX(16f).toY(1.7f).toZ(-16).build());
            SGE.director().queueDirector(DIR_LookAtVertex.builder().toY(1.7f).build());

            ceiling = SGE.construct().infrastructure().floor().leftX(-20f).rightX(20f).nearZ(20f).farZ(-20f).y(wallH).textureId(Setup_Textures.texture_metalPanel).build();
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