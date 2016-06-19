package com.cdaniel.simplegameengine.plugins.physics.core;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.physics.collisionDetection.CollisionDetector_RaySphere;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;

import java.util.Collection;

/**
 * Created by christopher.daniel on 6/3/16.
 */
public class PhysicsLookTest {

    CollisionDetector_RaySphere raySphereTest = new CollisionDetector_RaySphere();

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Engine
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    PhysicsWrapper performLookAtTest(Collection<PhysicsWrapper> wrappers){

        if(wrappers == null || wrappers.size() == 0){
            return null;
        }

        SimpleVector lookVector = new SimpleVector(SGE.camera().getEyePosition(), SGE.camera().getLookingAt());

        for(PhysicsWrapper w : wrappers) {
            boolean lookingAt = raySphereTest.detectCollisions(lookVector, w);
            if(lookingAt){
                return w;
            }
        }

        return null;
    }
}
