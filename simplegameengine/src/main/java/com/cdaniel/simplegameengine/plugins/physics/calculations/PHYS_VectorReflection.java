package com.cdaniel.simplegameengine.plugins.physics.calculations;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VectorMath;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

/**
 * Created by christopher.daniel on 5/23/16.
 */
public class PHYS_VectorReflection {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Reflect the Vector
    *             |
    *      . v -> |                    Vector to reflect is v->
    *         .   |
    *           . |
    *     _ _ _ _ . _ _ _ n ->         Normal of Surface of Reflection is n->
    *           . |
    *         .   |
    *      .      |                    Reflection r->
    *       r ->  |
    *
    *
    *  Formula:
    *
    *   r = d − 2(v⋅n)n   .... where n is normalized
    *
    *  To Calculate
    *   v must be standardized
    *   n must be normalized (mag = 1)
    *   r will be standardized and if v was not standardized to start ... we need to de-standardize
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    public static Vector reflectVector(Vector v, Vector normal){

        Vector n = Calc_VectorMath.standardizeVector(normal);
        n = Calc_VectorMath.normalizeVector(n);

        float vDOTn = 2 * Calc_VectorMath.dotProduct(v, n);
        Vector reflecter = Calc_VectorMath.scaleVector(n, vDOTn);

        SimpleVector r = (SimpleVector) Calc_VectorMath.subtractVectors(v, reflecter);
        return r;
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Apply Velocity (vector) to a point (x,y,s) meters
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static SimpleVertex applyVelocity(Vertex pos, Vector velocity, float seconds){

        float deltaX = (seconds * (velocity.getEx() - velocity.getSx()));
        float deltaY = (seconds * (velocity.getEy() - velocity.getSy()));
        float deltaZ = (seconds * (velocity.getEz() - velocity.getSz()));

        SimpleVertex newPosition = new SimpleVertex(pos.getX()+deltaX, pos.getY()+deltaY, pos.getZ()+deltaZ);
        return newPosition;
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Apply Acceleration (vector) to a point (x,y,z) meters, over a time in seconds
    *
    * PHYSPROP_Gravity is a constant accelaration of 9.8 (m/s2) in the -y direction
    *
    *  s=0 |   .  --> m=0
    *  s=1 |   .  --> m=(9.8 * 1^2)/2 ~ 4.9
    *      |
    *  s=2 |   .  --> m=(9.8 * 2^2)/2 ~ 20
    *
    *
    * Position over time is velocity   ... first derivative  (m/s)
    * Velocity change over time is acc ... second derivative (m/s^2)
    *
    * So Single Integration shows a change in velocity
    * integral(9.8) = [9.8 * s] + velo`
    *
    * And double Integration shows a change in acceleration
    * integral(integral)(9.8) = [(9.8 * s^2)/2] + pos`
    *
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static SimpleVertex applyAcceleration(Vertex pos, Vector accel, float seconds){

        float accX = accel.getEx() - accel.getSx();
        float deltaX = (float) ((accX * Math.pow(seconds, 2) / 2f));

        float accY = accel.getEy() - accel.getSy();
        float deltaY = (float) ((accY * Math.pow(seconds, 2) / 2f));

        float accZ = accel.getEz() - accel.getSz();
        float deltaZ = (float) ((accZ * Math.pow(seconds, 2) / 2f));

        return new SimpleVertex(pos.getX()+deltaX, pos.getY()+deltaY, pos.getZ()+deltaZ);
    }
    public static SimpleVector applyAcceleration(Vector velocity, Vector accel, float seconds){

        float accX = accel.getEx() - accel.getSx();
        float deltaX = (accX * seconds);

        float accY = accel.getEy() - accel.getSy();
        float deltaY = (accY * seconds);

        float accZ = accel.getEz() - accel.getSz();
        float deltaZ = (accZ * seconds);

        return new SimpleVector(velocity.getSx(), velocity.getSy(), velocity.getSz(),
                                velocity.getEx()+deltaX, velocity.getEy()+deltaY, velocity.getEz()+deltaZ);
    }
}
