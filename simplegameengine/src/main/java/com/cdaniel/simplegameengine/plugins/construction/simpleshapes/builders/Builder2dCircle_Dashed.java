package com.cdaniel.simplegameengine.plugins.construction.simpleshapes.builders;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.construction.simpleshapes.main.AbstractShapeBuilder;
import com.cdaniel.simplegameengine.plugins.construction.simpleshapes.twoD.Circle_DashedLines;

/**
 * Created by christopher.daniel on 6/5/16.
 */
public class Builder2dCircle_Dashed extends AbstractShapeBuilder<Builder2dCircle_Dashed> {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables / Constructs
    *
    * *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    Builder2dCircle_Dashed(){}

    private float size = 1f;
    private float x;
    private float y;
    private float z;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Builder2dCircle_Dashed x(float x){
        this.x = x;
        return this;
    }
    public Builder2dCircle_Dashed y(float y){
        this.y = y;
        return this;
    }
    public Builder2dCircle_Dashed z(float z){
        this.z = z;
        return this;
    }
    public Builder2dCircle_Dashed size(float size){
        this.size = size;
        return this;
    }

    @Override
    public Builder2dCircle_Dashed returnYourself(){
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

        Circle_DashedLines drawData = new Circle_DashedLines();
        super.applyTextureColorProperty();
        int contentId = SGE.contents().add(drawData, properties);
        super.applyContentProperties(contentId);

        super.moveNewContent(contentId, x, y, z);
        super.sizeNewContent(contentId, size);

        return contentId;
    }
}
