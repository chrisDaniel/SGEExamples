package com.cdaniel.simplegameengine.plugins.construction.infrastructure.floors;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.main.AbstractInfrastructureDrawData;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class Floor_Rectangular extends AbstractInfrastructureDrawData implements DrawableData {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Draw Paramaters
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float thickness;

    private float xLeft;
    private float xRight;
    private float yBottom;
    private float yTop;
    private float zNear;
    private float zFar;

    private float widthX;
    private float lengthZ;

    @Override
    public int getGlDrawShape(){

        return GL10.GL_TRIANGLES;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     *  Construct / Clone
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Floor_Rectangular(float widthX, float lengthZ, float thickness){

        this.thickness = thickness;
        this.widthX = widthX;
        this.lengthZ = lengthZ;

        xLeft   = -widthX / 2f;
        xRight  = widthX / 2f;
        yBottom  = -thickness / 2f;
        yTop = thickness / 2f;
        zNear = lengthZ / 2f;
        zFar  = -lengthZ / 2f;
    }
    public Floor_Rectangular copy(){

        Floor_Rectangular copy = new Floor_Rectangular(widthX, lengthZ, thickness);
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

        //Drawing 6 faces ... a Rectangle Cube structure

        //Side 1...
        //the left face
        this.vertices.push(new SimpleVertex( xLeft,  yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xLeft,  yTop,     zNear));
        this.vertices.push(new SimpleVertex( xLeft,  yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xLeft,  yTop,     zFar));

        this.drawOrder.push(0);this.drawOrder.push(2);this.drawOrder.push(1);
        this.drawOrder.push(1);this.drawOrder.push(2);this.drawOrder.push(3);

        this.normalCollection.push(-1, 0, 0);
        this.normalCollection.push(-1, 0, 0);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(0, 0, 0);
        this.textureCollection.push(1, 1, 0); this.textureCollection.push(1, 0, 0);

        //Side 2...
        //the right face
        this.vertices.push(new SimpleVertex( xRight,  yBottom, zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,    zNear));
        this.vertices.push(new SimpleVertex( xRight,  yBottom, zFar));
        this.vertices.push(new SimpleVertex( xRight,  yTop,    zFar));

        this.drawOrder.push(0+4);this.drawOrder.push(2+4);this.drawOrder.push(1+4);
        this.drawOrder.push(1+4);this.drawOrder.push(2+4);this.drawOrder.push(3+4);

        this.normalCollection.push(1, 0, 0);
        this.normalCollection.push(1, 0, 0);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(0, 0, 0);
        this.textureCollection.push(1, 1, 0); this.textureCollection.push(1, 0, 0);


        //Side 3...
        //the top
        this.vertices.push(new SimpleVertex( xLeft,   yTop,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,  zNear));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,  zFar));
        this.vertices.push(new SimpleVertex( xRight,  yTop,  zFar));

        this.drawOrder.push(0+8);this.drawOrder.push(2+8);this.drawOrder.push(1+8);
        this.drawOrder.push(1+8);this.drawOrder.push(2+8);this.drawOrder.push(3+8);

        this.normalCollection.push(0, 1, 0);
        this.normalCollection.push(0, 1, 0);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(0, 0, 0);
        this.textureCollection.push(1, 1, 0); this.textureCollection.push(1, 0, 0);

        //Side 4...
        //the near face
        this.vertices.push(new SimpleVertex( xLeft,   yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,     zNear));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,     zNear));

        this.drawOrder.push(1+12);this.drawOrder.push(2+12);this.drawOrder.push(0+12);
        this.drawOrder.push(2+12);this.drawOrder.push(3+12);this.drawOrder.push(0+12);

        this.normalCollection.push(0, 0, 1);
        this.normalCollection.push(0, 0, 1);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(0, 0, 0);

        //Side 5...
        //the far face
        this.vertices.push(new SimpleVertex( xLeft,   yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xRight,  yTop,     zFar));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,     zFar));

        this.drawOrder.push(2+16);this.drawOrder.push(1+16);this.drawOrder.push(0+16);
        this.drawOrder.push(3+16);this.drawOrder.push(2+16);this.drawOrder.push(0+16);

        this.normalCollection.push(0, 0, -1);
        this.normalCollection.push(0, 0, -1);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(0, 0, 0);
    }
}
