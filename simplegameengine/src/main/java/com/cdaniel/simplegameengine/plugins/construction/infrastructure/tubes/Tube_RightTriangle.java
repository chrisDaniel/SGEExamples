package com.cdaniel.simplegameengine.plugins.construction.infrastructure.tubes;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.main.AbstractInfrastructureDrawData;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class Tube_RightTriangle extends AbstractInfrastructureDrawData implements DrawableData {

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
    private float yBottom;
    private float yTop;

    @Override
    public int getGlDrawShape(){

        return GL10.GL_TRIANGLES;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     *  Construct / Clone
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Tube_RightTriangle(float widthX, float lengthZ){

        this.widthX = widthX;
        this.lengthZ = lengthZ;

        xLeft  = -widthX / 2f;
        xRight = widthX / 2f;
        zNear = lengthZ / 2f;
        zFar  = -lengthZ / 2f;
        yBottom = xLeft;
        yTop = xRight;
    }
    public Tube_RightTriangle copy(){

        Tube_RightTriangle copy = new Tube_RightTriangle(widthX, lengthZ);
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
        //longways - the left vertical face (rectangle)
        this.vertices.push(new SimpleVertex( xLeft,  yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xLeft,  yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xLeft,  yTop,     zFar));
        this.vertices.push(new SimpleVertex( xLeft,  yTop,     zNear));

        this.normalCollection.push(-1, 0, 0);this.normalCollection.push(-1, 0, 0);
        this.normalCollection.push(-1, 0, 0);this.normalCollection.push(-1, 0, 0);

        this.drawOrder.push(0);this.drawOrder.push(3);this.drawOrder.push(2);
        this.drawOrder.push(2);this.drawOrder.push(1);this.drawOrder.push(0);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(0, 0, 0);

        //Side 2...
        //longways - the bottom face (rectangle)
        this.vertices.push(new SimpleVertex( xLeft,  yBottom, zNear));
        this.vertices.push(new SimpleVertex( xRight, yBottom, zNear));
        this.vertices.push(new SimpleVertex( xRight, yBottom, zFar));
        this.vertices.push(new SimpleVertex( xLeft,  yBottom, zFar));

        this.drawOrder.push(0+4);this.drawOrder.push(3+4);this.drawOrder.push(2+4);
        this.drawOrder.push(2+4);this.drawOrder.push(1+4);this.drawOrder.push(0+4);

        this.normalCollection.push(0, -1, 0);this.normalCollection.push(0, -1, 0);
        this.normalCollection.push(0, -1, 0);this.normalCollection.push(0, -1, 0);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(0, 0, 0);


        //Side 3...
        //longways - the incline face (rectangle)
        this.vertices.push(new SimpleVertex( xLeft,  yTop,    zNear));
        this.vertices.push(new SimpleVertex( xRight, yBottom, zNear));
        this.vertices.push(new SimpleVertex( xRight, yBottom, zFar));
        this.vertices.push(new SimpleVertex( xLeft,  yTop,    zFar));

        this.drawOrder.push(0+8);this.drawOrder.push(1+8);this.drawOrder.push(2+8);
        this.drawOrder.push(2+8);this.drawOrder.push(3+8);this.drawOrder.push(0+8);

        this.normalCollection.push(1, 1, 0);this.normalCollection.push(1, 1, 0);
        this.normalCollection.push(1, 1, 0);this.normalCollection.push(1, 1, 0);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(0, 0, 0);
        
        
        //Side 4...
        //the near cap (triangle)
        this.vertices.push(new SimpleVertex( xLeft,   yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,     zNear));

        this.drawOrder.push(0+12);this.drawOrder.push(1+12);this.drawOrder.push(2+12);

        this.normalCollection.push(0, 0, 1);this.normalCollection.push(0, 0, 1);
        this.normalCollection.push(0, 0, 1);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(0, 0, 0);

        //Side 5...
        //the far cap (triangle)
        this.vertices.push(new SimpleVertex( xLeft,   yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,     zFar));

        this.drawOrder.push(0+15);this.drawOrder.push(2+15);this.drawOrder.push(1+15);

        this.normalCollection.push(0, 0, -1);this.normalCollection.push(0, 0, -1);
        this.normalCollection.push(0, 0, -1);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(0, 0, 0);
    }
}
