package com.cdaniel.simplegameengine.plugins.physics.collisionDetection;

import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.plugins.physics.core.PhysicsWrapper;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VertexMath;

/**
 * Created by christopher.daniel on 5/23/16.
 */
public class CollisionBound_Sphere implements CollisionBoundary {

    public boolean detectCollision(PhysicsWrapper i, PhysicsWrapper l){

        Vertex iCenter = i.getContent().getCenter();
        Vertex lCenter = l.getContent().getCenter();

        float distanceBetween = Calc_VertexMath.distanceBetween(iCenter, lCenter);

        // check 1
        // evaluate if even possible
        float farPointi = i.getContent().getCurrentVertexCollection_maxCoordinate();
        float farPointl = l.getContent().getCurrentVertexCollection_maxCoordinate();

        if( (farPointi + farPointl) < distanceBetween){
            return false;
        }

        // finally .... no collision
        return false;

    }
}
