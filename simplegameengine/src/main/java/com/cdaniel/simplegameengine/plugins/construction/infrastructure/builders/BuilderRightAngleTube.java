package com.cdaniel.simplegameengine.plugins.construction.infrastructure.builders;

import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.main.AbstractInfraBuilder;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.tubes.Tube_RightTriangle;
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
public class BuilderRightAngleTube extends AbstractInfraBuilder<BuilderRightAngleTube> {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Shape Enum
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public enum TubeFace {
        TRIANGLULAR
    }
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables / Constructs
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    BuilderRightAngleTube(){}

    private float anchor1_X;
    private float anchor1_Z;
    private float anchor2_X;
    private float anchor2_Z;
    private float sideLength;
    private float y;
    private boolean flip = false;

    private TubeFace tubeFace = TubeFace.TRIANGLULAR;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BuilderRightAngleTube faceShape(TubeFace faceShape){
        this.tubeFace = faceShape;
        return this;
    }
    public BuilderRightAngleTube anchor1_X(float anchor1_X){
        this.anchor1_X = anchor1_X;
        return this;
    }
    public BuilderRightAngleTube anchor1_Z(float anchor1_Z){
        this.anchor1_Z = anchor1_Z;
        return this;
    }
    public BuilderRightAngleTube anchor2_X(float anchor2_X){
        this.anchor2_X = anchor2_X;
        return this;
    }
    public BuilderRightAngleTube anchor2_Z(float anchor2_Z){
        this.anchor2_Z = anchor2_Z;
        return this;
    }
    public BuilderRightAngleTube sideLength(float sideLength){
        this.sideLength = sideLength;
        return this;
    }
    public BuilderRightAngleTube y(float y){
        this.y = y;
        return this;
    }
    public BuilderRightAngleTube flip(boolean flip){
        this.flip = flip;
        return this;
    }

    @Override
    public BuilderRightAngleTube returnYourself(){
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

        int contentId = -1;

        if(tubeFace.equals(TubeFace.TRIANGLULAR)) {
            Tube_RightTriangle drawData = new Tube_RightTriangle(sideLength, getTubeDistZ());
            drawData.applyTexturizer(this.texturizer);
            contentId = SGE.contents().add(drawData, properties);
        }
        else{
            return -1;
        }
        super.applyContentProperties(contentId);

        doFinaltransforms(contentId);
        return contentId;
    }
    private void doFinaltransforms(int contentId){

        SimpleVector finalVector = new SimpleVector(anchor1_X, 0f, anchor1_Z, anchor2_X, 0f, anchor2_Z);
        SimpleVector currVector = new SimpleVector(0f, 0f, 0f, 0f, 0f, -1f);

        //Step 1...
        //Rotate the Tube
        float rotationAngle = -1 * Calc_VectorMath.angleBetween(currVector, finalVector);

        if(flip){
            rotationAngle += Constants.PI;
        }

        Transform_Rotate tRotate = Transform_Rotate.builder().rotationAboutY(rotationAngle).build();
        SGE.contents().get(contentId).applyTransform(tRotate);

        //Step 2...
        //Move the 
        Vertex ToCenter = Calc_VectorMath.midpoint(finalVector);
        Transform_Move tMove = Transform_Move.builder().toVertex(ToCenter).build();
        SGE.contents().get(contentId).applyTransform(tMove);

        if(y>0){
            tMove = Transform_Move.builder().toY(y).build();
            SGE.contents().get(contentId).applyTransform(tMove);
        }
    }
    private float getTubeDistZ(){

        return Calc_VertexMath.distanceBetween(new SimpleVertex(anchor1_X, 0f, anchor1_Z), new SimpleVertex(anchor2_X, 0f, anchor2_Z));
    }

}
