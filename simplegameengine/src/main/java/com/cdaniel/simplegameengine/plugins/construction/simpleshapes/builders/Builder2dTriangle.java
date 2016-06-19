package com.cdaniel.simplegameengine.plugins.construction.simpleshapes.builders;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.construction.simpleshapes.main.AbstractShapeBuilder;
import com.cdaniel.simplegameengine.plugins.construction.simpleshapes.twoD.Triangle;

/**
 * Created by christopher.daniel on 6/5/16.
 */
public class Builder2dTriangle extends AbstractShapeBuilder<Builder2dTriangle> {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables / Constructs
    *
    * *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    Builder2dTriangle(){}

    private float size = 1f;
    private float x;
    private float y;
    private float z;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Builder2dTriangle x(float x){
        this.x = x;
        return this;
    }
    public Builder2dTriangle y(float y){
        this.y = y;
        return this;
    }
    public Builder2dTriangle z(float z){
        this.z = z;
        return this;
    }
    public Builder2dTriangle size(float size){
        this.size = size;
        return this;
    }

    @Override
    public Builder2dTriangle returnYourself(){
        return this;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Build
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public int build() {

        startNewProperties();
        applyTextureColorProperty();

        Triangle drawData = new Triangle();
        super.applyTextureColorProperty();
        int contentId = SGE.contents().add(drawData, properties);
        super.applyContentProperties(contentId);

        super.moveNewContent(contentId, x, y, z);
        super.sizeNewContent(contentId, size);

        return contentId;
    }
}
