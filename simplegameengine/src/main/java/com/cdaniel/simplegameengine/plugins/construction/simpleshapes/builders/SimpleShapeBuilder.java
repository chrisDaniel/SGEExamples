package com.cdaniel.simplegameengine.plugins.construction.simpleshapes.builders;

/**
 * Created by christopher.daniel on 5/7/16.
 */
public class SimpleShapeBuilder{


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Meshes / 3-D
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Builder3dSphere sphere(){
        return new Builder3dSphere();
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Flat 2-D
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Builder2dTriangle triangle_2D(){
        return new Builder2dTriangle();
    }
    public Builder2dCircle_Dashed circle_2D_dashed(){return new Builder2dCircle_Dashed();}
}
