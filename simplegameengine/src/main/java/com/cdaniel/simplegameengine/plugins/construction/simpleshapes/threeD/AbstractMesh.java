package com.cdaniel.simplegameengine.plugins.construction.simpleshapes.threeD;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.plugins.construction.simpleshapes.main.AbstractShapeDrawData;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/5/16.
 */
abstract public class AbstractMesh extends AbstractShapeDrawData implements DrawableData {

    
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

        return GL10.GL_TRIANGLES;
    }

}
