package com.cdaniel.simplegameengine.utils.transformers;

import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;

/**
 * Created by christopher.daniel on 5/5/16.
 */
public class Transform_Scale implements TransformLocalSpace {


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float xScale;
    private float yScale;
    private float zScale;

    Transform_Scale(float xScale, float yScale, float zScale){

        this.xScale = xScale;
        this.yScale = yScale;
        this.zScale = zScale;
    }

    public void updateValues(float xScale, float yScale, float zScale){

        this.xScale = xScale;
        this.yScale = yScale;
        this.zScale = zScale;
    }



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Do the Transformation
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void transform(VertexCollection vc){

        vc.iterator.reset();
        while(vc.iterator.next()){
            vc.iterator.transform(vc.iterator.x * xScale, vc.iterator.y * yScale, vc.iterator.z * zScale);
        }
    }

    @Override
    public void transform(Vertex vertex){

        vertex.transform(xScale * vertex.getX(), yScale * vertex.getY(), zScale * vertex.getZ());
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static Transform_ScaleBuilder builder() {
        return new Transform_ScaleBuilder();
    }
    public static class Transform_ScaleBuilder {

        private float xScale;
        private float yScale;
        private float zScale;

        public Transform_Scale.Transform_ScaleBuilder xScale(float xScale) {
            this.xScale = xScale;
            return this;
        }
        public Transform_Scale.Transform_ScaleBuilder yScale(float yScale) {
            this.yScale = yScale;
            return this;
        }
        public Transform_Scale.Transform_ScaleBuilder zScale(float zScale) {
            this.zScale = zScale;
            return this;
        }
        public Transform_Scale.Transform_ScaleBuilder scale(float scale) {
            this.xScale = scale;
            this.yScale = scale;
            this.zScale = scale;
            return this;
        }
        public Transform_Scale build() {
            return new Transform_Scale(xScale, yScale, zScale);
        }
    }
}
