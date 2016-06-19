package com.cdaniel.simplegameengine.plugins.construction.infrastructure.builders;

import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.floors.Floor_Flat;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.floors.Floor_Rectangular;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.main.AbstractInfraBuilder;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class BuilderFloor extends AbstractInfraBuilder<BuilderFloor> {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables / Constructs
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    BuilderFloor(){}

    private float leftX;
    private float rightX;
    private float farZ;
    private float nearZ;
    private float y;

    private Float thickness;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BuilderFloor leftX(float leftX){
        this.leftX = leftX;
        return this;
    }
    public BuilderFloor rightX(float rightX){
        this.rightX = rightX;
        return this;
    }
    public BuilderFloor nearZ(float nearZ){
        this.nearZ = nearZ;
        return this;
    }
    public BuilderFloor farZ(float farZ){
        this.farZ = farZ;
        return this;
    }
    public BuilderFloor y(float y){
        this.y = y;
        return this;
    }
    public BuilderFloor thickness(float thickness){
        this.thickness = thickness;
        return this;
    }

    @Override
    public BuilderFloor returnYourself(){
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

        //What to draw?
        //Case A -> No Thickness ... use a flat floor
        //Case B -> Thick floor ... use a rectangular floor
        int contentId = -1;

        if(this.thickness == null) {
            Floor_Flat drawData = new Floor_Flat(getFloorDistX(), getFloorDistZ());
            drawData.applyTexturizer(this.texturizer);
            contentId = SGE.contents().add(drawData, properties);
        }
        else {
            Floor_Rectangular drawData = new Floor_Rectangular(getFloorDistX(), getFloorDistZ(), thickness);
            drawData.applyTexturizer(this.texturizer);
            contentId = SGE.contents().add(drawData, properties);
        }

        super.applyContentProperties(contentId);
        doFinaltransforms(contentId);
        return contentId;
    }
    private void doFinaltransforms(int contentId){

        Transform_Move tMove = Transform_Move.builder()
                .toX(getFloorCenterX()).toY(getFloorCenterY()).toZ(getFloorCenterZ()).build();
        SGE.contents().get(contentId).applyTransform(tMove);
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Calc Helpers
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float getFloorDistX(){
        return this.rightX - this.leftX;
    }
    private float getFloorCenterX(){
        return ( this.leftX + (getFloorDistX() / 2f) );
    }
    private float getFloorDistZ(){
        return this.nearZ - this.farZ;
    }
    private float getFloorCenterZ(){
        return ( this.nearZ - (getFloorDistZ() / 2f) );
    }
    private float getFloorCenterY(){

        if(thickness==null){
            return 0;
        }
        return ( this.y - (this.thickness / 2f) );
    }

}
