package com.cdaniel.simplegameengine.plugins.construction.simpleshapes.twoD;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.plugins.construction.simpleshapes.main.AbstractShapeDrawData;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/4/16.
 */
abstract public class AbstractShape extends AbstractShapeDrawData implements DrawableData {


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Draw Paramaters
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected float size = 1f;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Override Abstract Shape
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public int getGlDrawShape(){

        return GL10.GL_LINES;
    }

    @Override
    public VertexCollection getTextureCollection(){

        return textureCollection;
    }
}
