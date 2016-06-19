package com.cdaniel.simplegameengine.plugins.construction.simpleshapes.main;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.plugins.construction.texturizer.Texturizable;
import com.cdaniel.simplegameengine.plugins.construction.texturizer.Texturizer;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;

/**
 * Created by christopher.daniel on 5/4/16.
 */
abstract public class AbstractShapeDrawData implements DrawableData, Texturizable {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Draw Paramaters
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected VertexCollection vertices;
    protected DrawOrderCollection drawOrder;
    protected VertexCollection textureCollection;
    protected VertexCollection normalCollection;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Interface Methods for Draw Data
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public VertexCollection getVertexCollection() {

        if(this.vertices == null) {
            calculateDrawData();
        }
        return this.vertices.copy();
    }

    @Override
    public DrawOrderCollection getDrawOrder() {

        if(this.drawOrder == null){
            calculateDrawData();
        }
        return this.drawOrder.copy();
    }

    @Override
    public VertexCollection getTextureCollection(){

        if(this.textureCollection == null){
            calculateDrawData();
        }
        if(this.textureCollection == null){
            return new VertexCollection();
        }
        return this.textureCollection;
    }

    @Override
    public VertexCollection getNormalCollection(){

        if(this.normalCollection == null){
            this.normalCollection = new VertexCollection();
        }
        return this.normalCollection;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Abstract Methods
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public abstract void calculateDrawData();


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Clone
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    protected void setCopiesData(AbstractShapeDrawData shape){

        if(this.vertices != null) {
            shape.vertices = this.vertices.copy();
        }
        if(this.drawOrder != null) {
            shape.drawOrder = this.drawOrder.copy();
        }
        if(this.textureCollection != null){
            shape.textureCollection = this.textureCollection.copy();
        }
        if(this.normalCollection != null){
            shape.normalCollection = this.normalCollection.copy();
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Texturizable
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void applyTexturizer(Texturizer t){
        t.texturize(this.getTextureCollection());
    }
}
