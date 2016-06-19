package com.cdaniel.simplegameengine.utils.transformers;

import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;

/**
 * Created by christopher.daniel on 5/5/16.
 */
public class Transform_Move implements TransformGlobalSpace {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Construct and Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private Float toX;
    private Float toY;
    private Float toZ;
    
    Transform_Move(Float toX, Float toY, Float toZ){

        this.toX = toX;
        this.toY = toY;
        this.toZ = toZ;
    }

    public void updateValues(Float toX, Float toY, Float toZ){

        this.toX = toX;
        this.toY = toY;
        this.toZ = toZ;
    }
    public void updateValues(Vertex v) {
        this.toX = v.getX();
        this.toY = v.getY();
        this.toZ = v.getZ();
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Do the Transformation
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void transform(VertexCollection vertices){

        vertices.iterator.reset();
        while(vertices.iterator.next()){

            if(this.toX != null){
                vertices.iterator.transformX(toX);
            }
            if(this.toY != null){
                vertices.iterator.transformY(toY);
            }
            if(this.toZ != null){
                vertices.iterator.transformZ(toZ);
            }
        }
    }
    @Override
    public void transform(Vertex vertex){

        float _toX = (this.toX != null ? this.toX : vertex.getX());
        float _toY = (this.toY != null ? this.toY : vertex.getY());
        float _toZ = (this.toZ != null ? this.toZ : vertex.getZ());

        vertex.transform(_toX, _toY, _toZ);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static Transform_MoveBuilder builder() {
        return new Transform_MoveBuilder();
    }
    public static class Transform_MoveBuilder {

        private Float toX;
        private Float toY;
        private Float toZ;

        public Transform_Move.Transform_MoveBuilder toX(float toX) {
            this.toX = toX;
            return this;
        }
        public Transform_Move.Transform_MoveBuilder toY(float toY) {
            this.toY = toY;
            return this;
        }
        public Transform_Move.Transform_MoveBuilder toZ(float toZ) {
            this.toZ = toZ;
            return this;
        }
        public Transform_Move.Transform_MoveBuilder toVertex(Vertex v) {
            this.toX = v.getX();
            this.toY = v.getY();
            this.toZ = v.getZ();
            return this;
        }

        public Transform_Move build() {
            return new Transform_Move(toX, toY, toZ);
        }
    }
}
