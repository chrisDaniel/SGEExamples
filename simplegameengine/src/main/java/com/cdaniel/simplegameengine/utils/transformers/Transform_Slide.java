package com.cdaniel.simplegameengine.utils.transformers;

import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;

/**
 * Created by christopher.daniel on 5/5/16.
 */
public class Transform_Slide implements TransformGlobalSpace {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float deltaX;
    private float deltaY;
    private float deltaZ;

    Transform_Slide(float deltaX, float deltaY, float deltaZ){

        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
    }
    public void updateValues(float deltaX, float deltaY, float deltaZ){

        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.deltaZ = deltaZ;
    }



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Do the Transformation
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void transform(VertexCollection vc){

        vc.iterator.reset();
        while(vc.iterator.next()){
            vc.iterator.transform(vc.iterator.x + deltaX, vc.iterator.y + deltaY, vc.iterator.z + deltaZ);
        }
    }
    @Override
    public void transform(Vertex vertex){

        vertex.transform(deltaX + vertex.getX(),
                         deltaY + vertex.getY(),
                         deltaZ + vertex.getZ());
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static Transform_SlideBuilder builder() {
        return new Transform_SlideBuilder();
    }
    public static class Transform_SlideBuilder {

        private float deltaX;
        private float deltaY;
        private float deltaZ;

        public Transform_Slide.Transform_SlideBuilder deltaX(float deltaX) {
            this.deltaX = deltaX;
            return this;
        }
        public Transform_Slide.Transform_SlideBuilder deltaY(float deltaY) {
            this.deltaY = deltaY;
            return this;
        }
        public Transform_Slide.Transform_SlideBuilder deltaZ(float deltaZ) {
            this.deltaZ = deltaZ;
            return this;
        }

        public Transform_Slide build() {
            return new Transform_Slide(deltaX, deltaY, deltaZ);
        }
    }
}
