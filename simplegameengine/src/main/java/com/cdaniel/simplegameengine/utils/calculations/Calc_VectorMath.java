package com.cdaniel.simplegameengine.utils.calculations;

import com.cdaniel.simplegameengine.core.Vector;
import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

/**
 * Created by christopher.daniel on 4/18/16.
 */
public class Calc_VectorMath {

    /************************************************** /
     *Basic Plus Minus Scale
     /***************************************************/
    public static Vector moveVector(Vector v1, float xDelta, float yDelta, float zDelta){

        return new SimpleVector(v1.getSx() + xDelta, v1.getSy() + yDelta, v1.getSz() + zDelta,
                                v1.getEx() + xDelta, v1.getEy() + yDelta, v1.getEz() + zDelta);
    }
    public static Vector addVectors(Vector v1, Vector v2){

        Vertex sV1 = standardizeVectorAsVertex(v1);
        Vertex sV2 = standardizeVectorAsVertex(v2);

        Vertex e = Calc_VertexMath.add(sV1, sV2);

        return new SimpleVector(v1.getSx(), v1.getSy(), v1.getSz(),
                                e.getX() + v1.getSx(), e.getY() + v1.getSy(), e.getZ()+v1.getSz());
    }
    public static Vector subtractVectors(Vector v1, Vector v2){

        Vertex sV1 = standardizeVectorAsVertex(v1);
        Vertex sV2 = standardizeVectorAsVertex(v2);

        Vertex e = Calc_VertexMath.subtract(sV1, sV2);

        return new SimpleVector(v1.getSx(), v1.getSy(), v1.getSz(),
                e.getX()+v1.getSx(), e.getY()+v1.getSy(), e.getZ()+v1.getSz());
    }

    public static Vector scaleVector(Vector v, float scale){
        return new SimpleVector(v.getSx()*scale, v.getSy()*scale, v.getSz()*scale, v.getEx()*scale, v.getEy()*scale, v.getEz()*scale);
    }

    public static Vector invertVector(Vector v){
        return new SimpleVector(v.getEx(), v.getEy(), v.getEz(), v.getSx(), v.getSy(), v.getSy());
    }
    /************************************************** /
     *Root Vector At Origin ... Standard Position Vector
     *
     * For a Vector of Vertices V1->V2 ... move V1 to the origin
     *
     /***************************************************/
    public static Vertex standardizeVectorAsVertex(Vector v){
        return Calc_VertexMath.subtract(v.getEndVertex(), v.getStartVertex());
    }
    public static Vector standardizeVector(Vector v){
        Vertex eStandard = Calc_VertexMath.subtract(v.getEndVertex(), v.getStartVertex());
        SimpleVector vStandard = new SimpleVector(0f, 0f, 0f, eStandard.getX(), eStandard.getY(), eStandard.getZ());
        return vStandard;
    }


    /************************************************** /
     * Midpoint
     *
     * For a Vector of Vertices V1->V2 ... the vertex equa-distance from V1 and V2 and parallel to V1->V2
     *
     /***************************************************/
    public static Vertex midpoint(Vector v){
        Vertex v1 = standardizeVectorAsVertex(v);
        Vertex vMid = Calc_VertexMath.scale(v1, .5f);
        vMid = Calc_VertexMath.add(v.getStartVertex(), vMid);
        return vMid;
    }


    /************************************************** /
     *Normal
     *
     * For two vectors a and b, then normal is the vector which is parallel to both a and b
     *
     * Learn Here:
     * https://en.wikipedia.org/wiki/Normal_(geometry)
    /***************************************************/

    /**
     * Given three vertices representing a plane ... calculate the normal for the vectors which originate at v3
     * @param  v1 - Vertex
     * @param  v2 - Vertex
     * @param  v3 - Vertex
     * @return Vertex
     */
    public static SimpleVertex CALC_NORMAL(Vertex v1, Vertex v2, Vertex v3) {

        Vertex Ov1 = Calc_VertexMath.subtract(v1, v3);
        Vertex Ov2 = Calc_VertexMath.subtract(v2, v3);
        return CALC_NORMAL(Ov1, Ov2);
    }

    /**
     * Given three vertices representing a plane ... calculate the normal for the vectors which originate at the origin
     * @param  v1 - Vertex
     * @param  v2 - Vertex
     * @return Vertex
     */
    public static SimpleVertex CALC_NORMAL(Vertex v1, Vertex v2){

        float nX = (v1.getX() * v2.getY() - v1.getZ() * v2.getY());
        float nY = (v1.getZ() * v2.getX() - v1.getX() * v2.getZ());
        float nZ = (v1.getX() * v2.getY() - v1.getY() * v2.getX());

        return new SimpleVertex(nX, nY, nZ);
    }


    /************************************************** /
     * DOT PRODUCT
     *
     * For two vectors a and b, then dot product is useful for determining the angle between these two vectors.
     * It is the magnitude(a) * magnitude(b) * cosine(anglebetween)
     *
     * It can simply be calculated by
     * (a.x * b.x) + (a.y * b.y) + (a.z * b.z)
     *
     * Learn Here:
     * https://en.wikipedia.org/wiki/Normal_(geometry)
     /***************************************************/

    /**
     * Given two Vertices which can represent vectors who start at origin  ... calculate the dot product between v1 and v2
     * @param  v1 - Vertex
     * @param  v2 - Vertex
     * @return Vertex
     */
    public static float dotProduct(Vector v1, Vector v2) {

        Vertex endV1 = standardizeVectorAsVertex(v1);
        Vertex endV2 = standardizeVectorAsVertex(v2);

        float dP = (endV1.getX() * endV2.getX()) +
                   (endV1.getY() * endV2.getY()) +
                   (endV1.getZ() * endV2.getZ());

        return dP;
    }


    /************************************************** /
     * Angle Between Vectors
     *
     * The angle between vectors (a) can be calculated by the formula
     *
     *          v1 (dotproduct) v2
     * Cos a =  ------------------
     *          Mag(v1) x Mag(v2)
     *
     /***************************************************/
    public static float angleBetween(Vector v1, Vector v2){

        float dp = dotProduct(v1, v2);
        float m1 = magnitude(v1);
        float m2 = magnitude(v2);

        double cosA = dp / (m1 * m2);

        if(cosA > 1){cosA = 1;}
        if(cosA < -1){cosA = -1;}

        float angle = (float) Math.acos(cosA);

        return angle;
    }

    /************************************************** /
     * Magnitude of Vector
     *
     * The size of the vector defined by a start vertex and end vertex
     *
     * Learn Here:
     * https://en.wikipedia.org/wiki/Magnitude_(mathematics)
     /***************************************************/
    public static float magnitude(Vector v){

        return Calc_VertexMath.distanceBetween(v.getStartVertex(), v.getEndVertex());
    }


    /************************************************** /
     * Resize A Vector
     *
     * A vector V1 -> V2. Apply the resize method to create
     * a new vector V1 -> V3 such that it has magnitude *newMagnitude* and is parallel to V1 -> V2
     *
     * A unit vector (normalized) vector would be
     * a new vector v1 -> v3 such that it has magnitude 1 and is parallel to V1->v2
     /***************************************************/
    public static Vector normalizeVector(Vector v) {

        return resizeVector(v, 1);
    }
    public static Vector resizeVector(Vector v, float newMagnitude) {

        //move the vector's end vertex to the origin and call it v3
        SimpleVertex v3 = Calc_VertexMath.subtract(v.getEndVertex(), v.getStartVertex());

        //transform v3 into a normalized unit vector
        float M = Calc_VertexMath.distanceFromOrigin(v3);
        v3.transform((v3.getX() / M), (v3.getY() / M), (v3.getZ() / M));

        //multiply v3 by the newMagnitude
        Calc_VertexMath.inPlace_scale(v3, newMagnitude);

        //move the vector's back to where it started (the original start vertex)
        v3 = Calc_VertexMath.add(v3, v.getStartVertex());

        //return the new vector
        return new SimpleVector(v.getStartVertex(), v3);
    }
    public static Vector increaseVectorSize(Vector v, float byValue){

        float currentM = magnitude(v);
        float newM = currentM + byValue;
        return resizeVector(v, newM);
    }


    /************************************************** /
     * Rotate A Vector
     *
     * A vector V1 -> V2. Keep V1 fixxed and rotate V2 through Alpha
     /***************************************************/
    public static Vector rotateAboutY(Vector v, double alpha){

        Vertex v3 = standardizeVectorAsVertex(v);
        v3 = Calc_VertexMath.rotate_aboutYAxis(v3, alpha);
        v3 = Calc_VertexMath.add(v3, v.getStartVertex());

        return new SimpleVector(v.getStartVertex(), v3);
    }
    public static Vector rotateAboutZ(Vector v, double alpha){

        Vertex v3 = standardizeVectorAsVertex(v);
        v3 = Calc_VertexMath.rotate_aboutZAxis(v3, alpha);
        v3 = Calc_VertexMath.add(v3, v.getStartVertex());

        return new SimpleVector(v.getStartVertex(), v3);
    }
}
