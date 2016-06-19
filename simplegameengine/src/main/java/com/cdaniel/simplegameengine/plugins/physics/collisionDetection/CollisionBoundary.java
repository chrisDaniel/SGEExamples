package com.cdaniel.simplegameengine.plugins.physics.collisionDetection;

import com.cdaniel.simplegameengine.plugins.physics.core.PhysicsWrapper;

/**
 * Created by christopher.daniel on 5/23/16.
 */
public interface CollisionBoundary {

    boolean detectCollision(PhysicsWrapper i, PhysicsWrapper l);
}
