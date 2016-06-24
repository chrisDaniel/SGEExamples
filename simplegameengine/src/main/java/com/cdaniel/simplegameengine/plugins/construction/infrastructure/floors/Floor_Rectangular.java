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
        //the top
        this.vertices.push(new SimpleVertex( xLeft,   yTop,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,  zFar));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,  zFar));

        this.drawOrder.push(0);this.drawOrder.push(2);this.drawOrder.push(3);
        this.drawOrder.push(0);this.drawOrder.push(1);this.drawOrder.push(2);

        this.normalCollection.push(0, 1, 0);this.normalCollection.push(0, 1, 0);
        this.normalCollection.push(0, 1, 0);this.normalCollection.push(0, 1, 0);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 0, 0);
        this.textureCollection.push(1, 1, 0); this.textureCollection.push(0, 0, 0);

        //Side 2...
        //the bottom
        this.vertices.push(new SimpleVertex( xLeft,   yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xLeft,   yBottom,  zFar));

        this.drawOrder.push(4);this.drawOrder.push(6);this.drawOrder.push(7);
        this.drawOrder.push(4);this.drawOrder.push(5);this.drawOrder.push(6);

        this.normalCollection.push(0, -1, 0);this.normalCollection.push(0, -1, 0);
        this.normalCollection.push(0, -1, 0);this.normalCollection.push(0, -1, 0);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 0, 0);
        this.textureCollection.push(1, 1, 0); this.textureCollection.push(0, 0, 0);

        //Side 3...
        //the left face
        this.vertices.push(new SimpleVertex( xLeft,  yTop,     zNear));
        this.vertices.push(new SimpleVertex( xLeft,  yTop,     zFar));
        this.vertices.push(new SimpleVertex( xLeft,  yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xLeft,  yBottom,  zNear));

        this.drawOrder.push(8);this.drawOrder.push(9);this.drawOrder.push(10);
        this.drawOrder.push(10);this.drawOrder.push(11);this.drawOrder.push(8);

        this.normalCollection.push(-1, 0, 0);this.normalCollection.push(-1, 0, 0);
        this.normalCollection.push(-1, 0, 0);this.normalCollection.push(-1, 0, 0);

        this.textureCollection.push(1, 0, 0); this.textureCollection.push(0, 0, 0);
        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);

        //Side 4...
        //the right face
        this.vertices.push(new SimpleVertex( xRight,  yTop,     zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,     zFar));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zNear));

        this.drawOrder.push(14);this.drawOrder.push(13);this.drawOrder.push(12);
        this.drawOrder.push(12);this.drawOrder.push(15);this.drawOrder.push(14);

        this.normalCollection.push(1, 0, 0);this.normalCollection.push(1, 0, 0);
        this.normalCollection.push(1, 0, 0);this.normalCollection.push(1, 0, 0);

        this.textureCollection.push(0, 0, 0); this.textureCollection.push(1, 0, 0);
        this.textureCollection.push(1, 1, 0); this.textureCollection.push(0, 1, 0);


        //Side 5...
        //the near face
        this.vertices.push(new SimpleVertex( xLeft,   yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,     zNear));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,     zNear));

        this.drawOrder.push(16);this.drawOrder.push(17);this.drawOrder.push(18);
        this.drawOrder.push(18);this.drawOrder.push(19);this.drawOrder.push(16);

        this.normalCollection.push(0, 0, 1);this.normalCollection.push(0, 0, 1);
        this.normalCollection.push(0, 0, 1);this.normalCollection.push(0, 0, 1);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(0, 0, 0);

        //Side 6...
        //the far face
        this.vertices.push(new SimpleVertex( xLeft,   yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xRight,  yTop,     zFar));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,     zFar));

        this.drawOrder.push(20);this.drawOrder.push(23);this.drawOrder.push(22);
        this.drawOrder.push(22);this.drawOrder.push(21);this.drawOrder.push(20);

        this.normalCollection.push(0, 0, -1);this.normalCollection.push(0, 0, -1);
        this.normalCollection.push(0, 0, -1);this.normalCollection.push(0, 0, -1);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(0, 0, 0);
    }
}
