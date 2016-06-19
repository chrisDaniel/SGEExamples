package com.cdaniel.simplegameengine.plugins.physics.physicalProperties;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.plugins.physics.collisionDetection.CollisionBoundary;

/**
 * Created by christopher.daniel on 5/24/16.
 */
public class PhysicsWrapperProperties {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Global Id
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Vector currentVelocity;
    private float weight;


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Global Id
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private CollisionBoundary collisionBoundary;
    private float elasticity;
}
