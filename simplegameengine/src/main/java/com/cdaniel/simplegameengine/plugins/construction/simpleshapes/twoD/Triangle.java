package com.cdaniel.simplegameengine.plugins.construction.simpleshapes.twoD;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/2/16.
 */
public class Triangle extends AbstractShape implements DrawableData {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     * Draw Paramaters
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public int getGlDrawShape(){

        return GL10.GL_TRIANGLES;
    }



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     *  Construct / Clone
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Triangle(){

    }
    public Triangle copy(){

        Triangle copy = new Triangle();
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

        this.vertices.push(new SimpleVertex(-1, 0, 0));
        this.vertices.push(new SimpleVertex( 1, 0, 0));
        this.vertices.push(new SimpleVertex( 0, 1, 0));

        this.drawOrder.push_onePlusPrior();
        this.drawOrder.push_onePlusPrior();
        this.drawOrder.push_onePlusPrior();
    }
}
