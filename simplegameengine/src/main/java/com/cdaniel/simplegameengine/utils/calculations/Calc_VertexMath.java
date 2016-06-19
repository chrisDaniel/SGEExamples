package com.cdaniel.simplegameengine.utils.calculations;

import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

/**
 * Created by christopher.daniel on 5/11/16.
 */
public class Calc_VertexMath {

    /***************************************************/
    /*Simple Math                                      */
    /***************************************************/
    public static SimpleVertex scale(Vertex v, float scale){

        return new SimpleVertex(v.getX()*scale, v.getY()*scale, v.getZ()*scale);
    }
    public static void inPlace_scale(Vertex v, float scale){

        v.transform(v.getX()*scale, v.getY()*scale, v.getZ()*scale);
    }
    public static SimpleVertex subtract(Vertex v1, Vertex v2){

        SimpleVertex v = new SimpleVertex(v1.getX() - v2.getX(), v1.getY() - v2.getY(), v1.getZ() - v2.getZ());
        return v;
    }
    public static SimpleVertex add(Vertex v1, Vertex v2){

        SimpleVertex v = new SimpleVertex(v1.getX() + v2.getX(), v1.getY() + v2.getY(), v1.getZ() + v2.getZ());
        return v;
    }

    /***************************************************/
    /*Distances                                        */
    /***************************************************/
    public static float distanceBetween(Vertex v1, Vertex v2){

        double xComp = Math.pow((v1.getX()-v2.getX()), 2);
        double yComp = Math.pow((v1.getY()-v2.getY()), 2);
        double zComp = Math.pow((v1.getZ()-v2.getZ()), 2);

        float dist = (float) Math.sqrt(xComp + yComp + zComp);
        return dist;

    }
    public static float distanceFromOrigin(Vertex v1){

        double xComp = Math.pow(v1.getX(), 2);
        double yComp = Math.pow(v1.getY(), 2);
        double zComp = Math.pow(v1.getZ(), 2);

        float dist = (float) Math.sqrt(xComp + yComp + zComp);
        return dist;
    }
    public static float distanceFromOrigin(float x, float y, float z){

        double xComp = Math.pow(x, 2);
        double yComp = Math.pow(y, 2);
        double zComp = Math.pow(z, 2);

        float dist = (float) Math.sqrt(xComp + yComp + zComp);
        return dist;
    }


    /***************************************************/
    /*Rotation                                         */
    /***************************************************/
    public static SimpleVertex rotate_aboutXAxis(Vertex v, double rotationAngle) {

        float rotatedX = v.getX();
        float rotatedY = (v.getY() * (float) Math.cos(rotationAngle)) - (v.getZ() * (float) Math.sin(rotationAngle));
        float rotatedZ = (v.getY() * (float) Math.sin(rotationAngle)) + (v.getZ() * (float) Math.cos(rotationAngle));

        return new SimpleVertex(rotatedX, rotatedY, rotatedZ);
    }
    public static SimpleVertex rotate_aboutYAxis(Vertex v, double rotationAngle){

        float rotatedX = (v.getX() * (float) Math.cos(rotationAngle)) + ( v.getZ() * (float) Math.sin(rotationAngle));
        float rotatedY =  v.getY();
        float rotatedZ = (-1f * v.getX() * (float) Math.sin(rotationAngle)) + ( v.getZ() * (float) Math.cos(rotationAngle));

        return new SimpleVertex(rotatedX, rotatedY, rotatedZ);
    }
    public static SimpleVertex rotate_aboutZAxis(Vertex v, double rotationAngle){

        float rotatedX = (v.getX() * (float) Math.cos(rotationAngle)) - ( v.getY() * (float) Math.sin(rotationAngle));
        float rotatedY = (v.getX() * (float) Math.sin(rotationAngle)) + ( v.getY() * (float) Math.cos(rotationAngle));
        float rotatedZ =  v.getZ();

        return new SimpleVertex(rotatedX, rotatedY, rotatedZ);
    }

}
