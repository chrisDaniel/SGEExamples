package com.cdaniel.simplegameengine.plugins.construction.infrastructure.walls;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.main.AbstractInfrastructureDrawData;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class Wall_Rectangular extends AbstractInfrastructureDrawData implements DrawableData {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Draw Paramaters
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float length;
    private float height;
    private float thickness;

    private float xLeft;
    private float xRight;
    private float yBottom;
    private float yTop; 
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
    public Wall_Rectangular(float length, float height, float thickness){

        this.length = length;
        this.height = height;
        this.thickness = thickness;

        if(thickness == 0){thickness = .3f;}

        xLeft  = -thickness / 2f;
        xRight = thickness / 2f;
        yBottom = 0f;
        yTop    = height;
        zNear = length / 2f;
        zFar  = -length / 2f;
    }
    public Wall_Rectangular copy(){

        Wall_Rectangular copy = new Wall_Rectangular(length, height, thickness);
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


        //todo: really seems like the normals are pointed the wrong way on the left/right face but this produces results

        //Drawing 6 faces ... a Rectangle Cube structure

        //Side 1...
        //the left face
        this.vertices.push(new SimpleVertex( xLeft,  yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xLeft,  yTop,     zNear));
        this.vertices.push(new SimpleVertex( xLeft,  yTop,     zFar));
        this.vertices.push(new SimpleVertex( xLeft,  yBottom,  zFar));

        this.drawOrder.push(0);this.drawOrder.push(1);this.drawOrder.push(2);
        this.drawOrder.push(0);this.drawOrder.push(2);this.drawOrder.push(3);

        this.normalCollection.push(1, 0, 0);this.normalCollection.push(1, 0, 0);
        this.normalCollection.push(1, 0, 0);this.normalCollection.push(1, 0, 0);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(0, 0, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(1, 1, 0);

        //Side 2...
        //the right face
        this.vertices.push(new SimpleVertex( xRight,  yBottom, zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,    zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,    zFar));
        this.vertices.push(new SimpleVertex( xRight,  yBottom, zFar));

        this.drawOrder.push(4);this.drawOrder.push(6);this.drawOrder.push(5);
        this.drawOrder.push(7);this.drawOrder.push(6);this.drawOrder.push(4);

        this.normalCollection.push(-1, 0, 0);this.normalCollection.push(-1, 0, 0);
        this.normalCollection.push(-1, 0, 0);this.normalCollection.push(-1, 0, 0);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(0, 0, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(1, 1, 0);


        //Side 3...
        //the top
        /*this.vertices.push(new SimpleVertex( xLeft,   yTop,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,  zNear));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,  zFar));
        this.vertices.push(new SimpleVertex( xRight,  yTop,  zFar));

        this.drawOrder.push(0+8);this.drawOrder.push(2+8);this.drawOrder.push(1+8);
        this.drawOrder.push(1+8);this.drawOrder.push(2+8);this.drawOrder.push(3+8);

        this.normalCollection.push(0, 1, 0);this.normalCollection.push(0, 1, 0);
        this.normalCollection.push(0, 1, 0);this.normalCollection.push(0, 1, 0);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(0, 0, 0);
        this.textureCollection.push(1, 1, 0); this.textureCollection.push(1, 0, 0);

        //Side 4...
        //the near end cap
        this.vertices.push(new SimpleVertex( xLeft,   yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,     zNear));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,     zNear));

        this.drawOrder.push(1+12);this.drawOrder.push(2+12);this.drawOrder.push(0+12);
        this.drawOrder.push(2+12);this.drawOrder.push(3+12);this.drawOrder.push(0+12);

        this.normalCollection.push(0, 0, 1);this.normalCollection.push(0, 0, 1);
        this.normalCollection.push(0, 0, 1);this.normalCollection.push(0, 0, 1);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(0, 0, 0);

        //Side 5...
        //the far end cap
        this.vertices.push(new SimpleVertex( xLeft,   yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xRight,  yTop,     zFar));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,     zFar));

        this.drawOrder.push(2+16);this.drawOrder.push(1+16);this.drawOrder.push(0+16);
        this.drawOrder.push(3+16);this.drawOrder.push(2+16);this.drawOrder.push(0+16);

        this.normalCollection.push(0, 0, -1);this.normalCollection.push(0, 0, -1);
        this.normalCollection.push(0, 0, -1);this.normalCollection.push(0, 0, -1);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(0, 0, 0);
        */
    }
}
