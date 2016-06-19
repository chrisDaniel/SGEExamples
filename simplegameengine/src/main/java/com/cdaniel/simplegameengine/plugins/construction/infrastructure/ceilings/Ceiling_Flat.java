package com.cdaniel.simplegameengine.plugins.construction.infrastructure.ceilings;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.main.AbstractInfrastructureDrawData;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/2/16.
 */
public class Ceiling_Flat extends AbstractInfrastructureDrawData implements DrawableData {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Draw Paramaters
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float leftX;
    private float rightX;
    private float farZ;
    private float nearZ;

    @Override
    public int getGlDrawShape(){

        return GL10.GL_TRIANGLES;
    }



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     *  Construct / Clone
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Ceiling_Flat(float leftX, float rightX, float nearZ, float farZ){

        this.leftX = leftX;
        this.rightX = rightX;
        this.nearZ = nearZ;
        this.farZ = farZ;
    }
    public Ceiling_Flat copy(){

        Ceiling_Flat copy = new Ceiling_Flat(leftX, rightX, nearZ, farZ);
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

        this.vertices.push(new SimpleVertex( leftX,  0,  farZ));
        this.vertices.push(new SimpleVertex( rightX, 0,  farZ));
        this.vertices.push(new SimpleVertex( leftX,  0,  nearZ));
        this.vertices.push(new SimpleVertex( rightX, 0,  nearZ));

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
        this.normalCollection.push(0, -1, 0);this.normalCollection.push(0, -1, 0);
        this.normalCollection.push(0, -1, 0);this.normalCollection.push(0, -1, 0);

        //Textures
        this.textureCollection.push(0, 0, 0);
        this.textureCollection.push(1, 0, 0);
        this.textureCollection.push(0, 1, 0);
        this.textureCollection.push(1, 1, 0);
    }
}
