package com.cdaniel.simplegameengine.plugins.construction.infrastructure.builders;

import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.main.AbstractInfraBuilder;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.other.Platform_withColumn;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class BuilderPlatformWithColumn extends AbstractInfraBuilder<BuilderPlatformWithColumn> {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables / Constructs
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    BuilderPlatformWithColumn(){}

    private float widthX;
    private float lengthZ;
    private float heightY;
    private float y;

    private Vertex centerXZ;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BuilderPlatformWithColumn widthX(float widthX){
        this.widthX = widthX;
        return this;
    }
    public BuilderPlatformWithColumn lengthZ(float lengthZ){
        this.lengthZ = lengthZ;
        return this;
    }
    public BuilderPlatformWithColumn heightY(float heightY){
        this.heightY = heightY;
        return this;
    }
    public BuilderPlatformWithColumn y(float y){
        this.y = y;
        return this;
    }
    public BuilderPlatformWithColumn xzPosition(Vertex centerXZ){
        this.centerXZ = centerXZ;
        return this;
    }

    @Override
    public BuilderPlatformWithColumn returnYourself(){
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

        Platform_withColumn drawData = new Platform_withColumn(widthX, lengthZ, heightY);
        drawData.applyTexturizer(this.texturizer);
        int contentId = SGE.contents().add(drawData, properties);
        super.applyContentProperties(contentId);

        doFinaltransforms(contentId);
        return contentId;
    }
    private void doFinaltransforms(int contentId){

        //Step 1...
        //Move the floor to a position of Y
        float colCenterX  = this.centerXZ.getX();
        float colCenterZ  = this.centerXZ.getZ();
        float colCenterY  = y; //(this.heightY / 2f) + y;

        Transform_Move tMove = Transform_Move.builder().toX(colCenterX).toY(colCenterY).toZ(colCenterZ).build();

        SGE.contents().get(contentId).applyTransform(tMove);
    }
}
