package com.cdaniel.sgeexamples.examples.examples;

import com.cdaniel.sgeexamples.examples.manager.Setup_Textures;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.director.directors_focus.DIR_LookAtVertex;
import com.cdaniel.simplegameengine.plugins.director.directors_movement.DIR_MoveTo;
import com.cdaniel.simplegameengine.plugins.physics.constants.ColliderType;
import com.cdaniel.simplegameengine.plugins.physics.physicalProperties.PHYSPROP_Pendulum;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

/**
 * Created by christopher.daniel on 5/23/16.
 */
public class X3_PhysicsPendulum extends AbstractXample {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Vars
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Integer shape1;
    
    private final float leftX  = -18f;
    private final float rightX = 18f;
    private final float nearZ  = 31f;
    private final float farZ   = -31f;

    private final float height = 20f;

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * On Frame
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void onFrame() {

        SGE.devTools().logging_includeContentDetail(true);

        //Set up a Simple Environment
        if (SGE.properties().totalFrames() == 1) {
            SGE.director().queueDirector(DIR_MoveTo.builder().toX(0f).toZ(30f).toY(16).duration(5).build());
            SGE.director().queueDirector(DIR_LookAtVertex.builder().toY(4f).duration(2).build());
        }
        if (SGE.properties().totalFrames() == 2) {

        }
        if (SGE.properties().totalFrames() == 10) {
            shape1 = SGE.construct().shapes().sphere().x(2f).y(2.5f).textureId(Setup_Textures.texture1).build();
        }

        //Enable Physics on the Shapes
        if (SGE.properties().totalFrames() == 170) {
            SGE.physics().activator().contentId(shape1).mass(1000).elasticity(.9f).colliderType(ColliderType.Initiator).activate();
        }

        //Apply Pendulum Motion
        if (SGE.properties().totalFrames() == 180) {
            SGE.physics().applyPhysicalProperty(shape1, new PHYSPROP_Pendulum(new SimpleVertex(0f, 8f, 0f)));
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
