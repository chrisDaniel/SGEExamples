package com.cdaniel.simplegameengine.plugins.construction.simpleshapes.builders;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.construction.simpleshapes.main.AbstractShapeBuilder;
import com.cdaniel.simplegameengine.plugins.construction.simpleshapes.threeD.Sphere_Textured;

/**
 * Created by christopher.daniel on 6/5/16.
 */
public class Builder3dSphere extends AbstractShapeBuilder<Builder3dSphere> {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables / Constructs
    *
    * *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    Builder3dSphere(){}

    private float size = 1f;
    private float x;
    private float y;
    private float z;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Builder3dSphere x(float x){
        this.x = x;
        return this;
    }
    public Builder3dSphere y(float y){
        this.y = y;
        return this;
    }
    public Builder3dSphere z(float z){
        this.z = z;
        return this;
    }
    public Builder3dSphere size(float size){
        this.size = size;
        return this;
    }

    @Override
    public Builder3dSphere returnYourself(){
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

        Sphere_Textured drawData = new Sphere_Textured();
        super.applyTextureColorProperty();
        int contentId = SGE.contents().add(drawData, properties);
        super.applyContentProperties(contentId);

        super.moveNewContent(contentId, x, y, z);
        super.sizeNewContent(contentId, size);

        return contentId;
    }
}
