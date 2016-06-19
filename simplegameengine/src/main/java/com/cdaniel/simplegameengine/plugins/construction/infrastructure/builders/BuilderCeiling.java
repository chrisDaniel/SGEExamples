package com.cdaniel.simplegameengine.plugins.construction.infrastructure.builders;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.ceilings.Ceiling_Flat;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.main.AbstractInfraBuilder;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class BuilderCeiling extends AbstractInfraBuilder<BuilderCeiling> {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables / Constructs
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    BuilderCeiling(){}

    private float leftX;
    private float rightX;
    private float farZ;
    private float nearZ;
    private float y;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BuilderCeiling leftX(float leftX){
        this.leftX = leftX;
        return this;
    }
    public BuilderCeiling rightX(float rightX){
        this.rightX = rightX;
        return this;
    }
    public BuilderCeiling nearZ(float nearZ){
        this.nearZ = nearZ;
        return this;
    }
    public BuilderCeiling farZ(float farZ){
        this.farZ = farZ;
        return this;
    }
    public BuilderCeiling y(float y){
        this.y = y;
        return this;
    }

    @Override
    public BuilderCeiling returnYourself(){
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

        Ceiling_Flat drawData = new Ceiling_Flat(leftX, rightX, nearZ, farZ);
        drawData.applyTexturizer(this.texturizer);
        int contentId = SGE.contents().add(drawData, properties);
        super.applyContentProperties(contentId);

        doFinaltransforms(contentId);
        return contentId;
    }
    private void doFinaltransforms(int contentId){

        //Step 1...
        //Move the Ceiling to a position of Y
        Transform_Move tMove = Transform_Move.builder().toY(y).build();
        SGE.contents().get(contentId).applyTransform(tMove);
    }
}
