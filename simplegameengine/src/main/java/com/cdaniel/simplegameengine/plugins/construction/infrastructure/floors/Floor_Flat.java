package com.cdaniel.simplegameengine.plugins.construction.infrastructure.floors;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.main.AbstractInfrastructureDrawData;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/2/16.
 */
public class Floor_Flat extends AbstractInfrastructureDrawData implements DrawableData {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Draw Paramaters
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float widthX;
    private float lengthZ;

    private float xLeft;
    private float xRight;
    private float zNear;
    private float zFar;

    @Override
    public int getGlDrawShape(){

        return GL10.GL_TRIANGLES;
    }



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     *  Construct / Clone
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Floor_Flat(float widthX, float lengthZ){

        this.widthX = widthX;
        this.lengthZ = lengthZ;

        xLeft   = -widthX / 2f;
        xRight  = widthX / 2f;
        zNear = lengthZ / 2f;
        zFar  = -lengthZ / 2f;
    }
    public Floor_Flat copy(){

        Floor_Flat copy = new Floor_Flat(widthX, lengthZ);
        setCopiesData(copy);
        return copy;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Calculate the SGEContentWrapper Data
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void calculateDrawData() {

        this.vertices = new VertexCollection();
        this.drawOrder = new DrawOrderCollection();
        this.textureCollection = new VertexCollection();
        this.normalCollection = new VertexCollection();

        this.vertices.push(new SimpleVertex(xLeft,  0, zFar));
        this.vertices.push(new SimpleVertex(xRight, 0, zFar));
        this.vertices.push(new SimpleVertex(xLeft,  0, zNear));
        this.vertices.push(new SimpleVertex(xRight, 0, zNear));

        //draw with two triangles
        //#1 ...  |/
        //#2 ...  /|
        this.drawOrder.push(0);
        this.drawOrder.push(2);
        this.drawOrder.push(1);

        this.drawOrder.push(1);
        this.drawOrder.push(2);
        this.drawOrder.push(3);

        //Normals
        //Simply unit vectors pointing straight up
        this.normalCollection.push(0, 1, 0);this.normalCollection.push(0, 1, 0);
        this.normalCollection.push(0, 1, 0);this.normalCollection.push(0, 1, 0);

        //Textures
        this.textureCollection.push(0, 0, 0);
        this.textureCollection.push(1, 0, 0);
        this.textureCollection.push(0, 1, 0);
        this.textureCollection.push(1, 1, 0);
    }
}
