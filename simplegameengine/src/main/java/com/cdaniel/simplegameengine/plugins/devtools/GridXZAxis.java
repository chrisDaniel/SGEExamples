package com.cdaniel.simplegameengine.plugins.devtools;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/8/16.
 */
public class GridXZAxis implements DrawableData {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Draw Paramaters
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float size;
    private float step;

    private VertexCollection grid;
    private DrawOrderCollection drawGrid;

    GridXZAxis(float size, float step){
        this.size = size;
        this.step = step;

        createData(size, step);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Interface Methods for Draw Data
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public VertexCollection getVertexCollection() {

        return this.grid;
    }

    @Override
    public DrawOrderCollection getDrawOrder() {

        return this.drawGrid;
    }

    @Override
    public int getGlDrawShape(){

        return GL10.GL_LINES;
    }

    @Override
    public VertexCollection getTextureCollection(){

        return new VertexCollection();
    }

    @Override
    public VertexCollection getNormalCollection(){

        return new VertexCollection();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Clone
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public GridXZAxis copy(){

        GridXZAxis copy = new GridXZAxis(size, step);

        if(this.grid != null) {
            copy.grid = this.grid.copy();
        }
        if(this.drawGrid != null) {
            copy.drawGrid = this.drawGrid.copy();
        }

        return copy;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Calculate the Vertex/Draw data
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void createData(float size, float step) {

        grid = new VertexCollection();

        grid.push(new SimpleVertex(0f,   0f, 0f));   // the back side of the X-Axis
        grid.push(new SimpleVertex(size, 0f, 0f));
        grid.push(new SimpleVertex(0f,   0f, 0f));   // the back side of the Z-Axis
        grid.push(new SimpleVertex(0f,   0f, size));

        drawGrid = new DrawOrderCollection(0, grid.getVertexCount()-1);
    }
}
