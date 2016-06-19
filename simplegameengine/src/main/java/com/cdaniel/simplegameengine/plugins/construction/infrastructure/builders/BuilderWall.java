package com.cdaniel.simplegameengine.plugins.construction.infrastructure.builders;

import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.main.AbstractInfraBuilder;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.walls.Wall_Rectangular;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VectorMath;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VertexMath;
import com.cdaniel.simplegameengine.utils.constants.Constants;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Rotate;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class BuilderWall extends AbstractInfraBuilder<BuilderWall> {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables / Constructs
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    BuilderWall(){}

    private float startX;
    private float startZ;
    private float endX;
    private float endZ;

    private float thickness;
    private float y;
    private float height;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BuilderWall startX(float startX){
        this.startX = startX;
        return this;
    }
    public BuilderWall startZ(float startZ){
        this.startZ = startZ;
        return this;
    }
    public BuilderWall endX(float endX){
        this.endX = endX;
        return this;
    }
    public BuilderWall endZ(float endZ){
        this.endZ = endZ;
        return this;
    }
    public BuilderWall height(float height){
        this.height = height;
        return this;
    }
    public BuilderWall thickness(float thickness){
        this.thickness = thickness;
        return this;
    }
    public BuilderWall y(float y){
        this.y = y;
        return this;
    }

    @Override
    public BuilderWall returnYourself(){
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

        Wall_Rectangular drawData = new Wall_Rectangular(getWallLength(), height, thickness);
        drawData.applyTexturizer(this.texturizer);
        int contentId = SGE.contents().add(drawData, properties);
        super.applyContentProperties(contentId);

        doFinaltransforms(contentId);
        return contentId;
    }
    private void doFinaltransforms(int contentId) {

        SimpleVector finalWallVector = new SimpleVector(startX, 0f, startZ, endX, 0f, endZ);
        SimpleVector currWallVector = new SimpleVector(0f, 0f, 0f, 0f, 0f, -1f);

        //Step 1...
        //Rotate the wall
        if (startX == endX){

            //no rotation
        }
        else if(startZ == endZ) {

            //90 degree rotation
            Transform_Rotate tRotate = Transform_Rotate.builder().rotationAboutY(Constants.PI / 2).build();
            SGE.contents().get(contentId).applyTransform(tRotate);
        }
        else{

            //general rotation
            float rotationAngle = -1 * Calc_VectorMath.angleBetween(currWallVector, finalWallVector);
            Transform_Rotate tRotate = Transform_Rotate.builder().rotationAboutY(rotationAngle).build();
            SGE.contents().get(contentId).applyTransform(tRotate);
        }

        //Step 2...
        //Move the wall
        Vertex wallToCenter = Calc_VectorMath.midpoint(finalWallVector);
        Transform_Move tMove = Transform_Move.builder().toVertex(wallToCenter).build();
        SGE.contents().get(contentId).applyTransform(tMove);

        if(y>0){
            tMove = Transform_Move.builder().toY(y).build();
            SGE.contents().get(contentId).applyTransform(tMove);
        }
    }
    private float getWallLength(){

        return Calc_VertexMath.distanceBetween(new SimpleVertex(startX, 0f, startZ), new SimpleVertex(endX, 0f, endZ));
    }

}
