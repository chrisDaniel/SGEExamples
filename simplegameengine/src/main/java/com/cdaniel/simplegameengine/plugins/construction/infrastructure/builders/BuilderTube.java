package com.cdaniel.simplegameengine.plugins.construction.infrastructure.builders;

import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.main.AbstractInfraBuilder;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.tubes.Tube_Octagon;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class BuilderTube extends AbstractInfraBuilder<BuilderTube> {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Shape Enum
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public enum TubeType {
        Octagon
    }
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables / Constructs
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    BuilderTube(){}

    private float radius;
    private Vertex from;
    private Vertex to;

    private TubeType tubeType = TubeType.Octagon;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BuilderTube tubeType(TubeType type){
        this.tubeType = type;
        return this;
    }
    public BuilderTube radius(float radius){
        this.radius = radius;
        return this;
    }
    public BuilderTube fromCenter(Vertex fromCenter){
        this.from = fromCenter;
        return this;
    }
    public BuilderTube toCenter(Vertex toCenter){
        this.to = toCenter;
        return this;
    }

    @Override
    public BuilderTube returnYourself(){
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

        if(tubeType.equals(TubeType.Octagon)) {

            float height = this.to.getY() - this.from.getY();

            Tube_Octagon drawData = new Tube_Octagon(radius, height);
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

        //SimpleVector finalVector = new SimpleVector(anchor1_X, 0f, anchor1_Z, anchor2_X, 0f, anchor2_Z);
        //SimpleVector currVector = new SimpleVector(0f, 0f, 0f, 0f, 0f, -1f);

        //Step 1...
        //Rotate the Tube
        /*float rotationAngle = -1 * Calc_VectorMath.angleBetween(currVector, finalVector);

        if(flip){
            rotationAngle += Constants.PI;
        }

        Transform_Rotate tRotate = Transform_Rotate.builder().rotationAboutY(rotationAngle).build();
        SGE.contents().get(contentId).applyTransform(tRotate);
        */

        //Step 2...
        //Move the Tube
        Transform_Move tMove = Transform_Move.builder().toVertex(from).build();
        SGE.contents().get(contentId).applyTransform(tMove);
    }
}
