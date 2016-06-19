package com.cdaniel.simplegameengine.plugins.physics.collisionDetection;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.plugins.physics.core.PhysicsWrapper;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VectorMath;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;

/**
 * Created by christopher.daniel on 5/24/16.
 */
public class CollisionDetector_RaySphere implements CollisionDetector {

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //
    // REF: https://en.wikipedia.org/wiki/Line%E2%80%93sphere_intersection
    //
    //Sphere's `shell` can be defined by all points P distance r from its center C
    //
    //  ||P - C|| = r
    //
    //A ray is defined from its starting point O and a normalized Vector D->
    //
    //   O + D-> t
    //
    //
    //So stick the Ray equation into the Sphere equation
    //
    //
    //  ||O + D->t - C|| - r = 0    ...  D-> simply two points the ray goes through
    //
    //
    //With some rearangement we get a Quadratic
    //
    //  ( t^2 ) + 2tb + c = 0
    //
    //  b ~= D-> . (O - C)
    //  c ~= (O - C) . (O - C) - r^2
    //
    //
    // Quadratics have two solutions
    //
    //  t = -b (+|-) squareroot(b^2 - c)
    //
    // And the determinent prescribes 3 scenarios (determinent = b^2-c)
    //
    //  case a) b^2 - c < 0 ... imaginary and miss
    //  case b) b^2 - c = 0 ... sol 1 = sol 2 ... ray hits the sphere in one place
    //  case c) b^2 - c > 0 ... ray hits the sphere in multiple places

    public boolean detectCollisions(Vector ray, PhysicsWrapper wrapper2){

        ray = Calc_VectorMath.normalizeVector(ray);
        float contentRadius = 1f; //wrapper2.getContent().getCurrentVertexCollection_maxCoordinate()

        //SimpleVector oMINUSc = new SimpleVector(ray.getStartVertex(), wrapper2.getContent().getCenter());
        SimpleVector oMINUSc = new SimpleVector(wrapper2.getContent().getCenter(), ray.getStartVertex());

        float b = Calc_VectorMath.dotProduct(ray, oMINUSc);
        float c = Calc_VectorMath.dotProduct(oMINUSc, oMINUSc) - (float) Math.pow(contentRadius,2);

        float determinant = (float) (Math.pow(b, 2)) - c;

        if(determinant < 0){
            return false;
        }
        return true;
    }
}
