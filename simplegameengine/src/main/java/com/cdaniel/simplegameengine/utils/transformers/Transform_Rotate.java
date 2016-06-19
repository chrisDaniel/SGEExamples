package com.cdaniel.simplegameengine.utils.transformers;

import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VertexMath;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

/**
 * Created by christopher.daniel on 5/5/16.
 */
public class Transform_Rotate implements TransformLocalSpace{


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     * Construct and Variables
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float rotationAboutY;

    Transform_Rotate(float rotationAboutY){

        this.rotationAboutY = rotationAboutY;
    }

    public void updateValues(float rotationAboutY){

        this.rotationAboutY = rotationAboutY;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     * Do the Transformation
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void transform(VertexCollection vc){

        vc.iterator.reset();

        while(vc.iterator.next()){
            SimpleVertex rotated = Calc_VertexMath.rotate_aboutYAxis(vc.iterator.copyVertex(), rotationAboutY);
            vc.iterator.transform(rotated.getX(), rotated.getY(), rotated.getZ());
        }
    }

    @Override
    public void transform(Vertex vertex){

        SimpleVertex rotated = Calc_VertexMath.rotate_aboutYAxis(vertex, rotationAboutY);
        vertex.transform(rotated.getX(), rotated.getY(), rotated.getZ());
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static Transform_RotateBuilder builder() {
        return new Transform_RotateBuilder();
    }
    public static class Transform_RotateBuilder {

        private float rotationAboutY;

        public Transform_Rotate.Transform_RotateBuilder rotationAboutY(float rotationAboutY) {
            this.rotationAboutY = rotationAboutY;
            return this;
        }

        public Transform_Rotate build() {
            return new Transform_Rotate(rotationAboutY);
        }
    }
}
