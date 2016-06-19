package com.cdaniel.simplegameengine.plugins.construction.signage.builders;

import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.engine.SGE;
import com.cdaniel.simplegameengine.engine.SGEContentWrapper;
import com.cdaniel.simplegameengine.plugins.construction.signage.main.AbstractSignBuilder;
import com.cdaniel.simplegameengine.plugins.construction.signage.signs.Sign_Rectangle;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VectorMath;
import com.cdaniel.simplegameengine.utils.constants.Constants;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVector;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Move;
import com.cdaniel.simplegameengine.utils.transformers.Transform_Rotate;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class BuilderRectangleSign extends AbstractSignBuilder<BuilderRectangleSign> {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables / Constructs
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    BuilderRectangleSign(){}

    private float width;
    private float height;
    private Vertex center;
    private Vertex normalPointer;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Builder Properties
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public BuilderRectangleSign width(float width){
        this.width = width;
        return this;
    }
    public BuilderRectangleSign height(float height){
        this.height = height;
        return this;
    }
    public BuilderRectangleSign center(Vertex center){
        this.center = center;
        return this;
    }public BuilderRectangleSign normalPointer(Vertex normalPointer){
        this.normalPointer = normalPointer;
        return this;
    }

    @Override
    public BuilderRectangleSign returnYourself(){
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

        Sign_Rectangle drawData = new Sign_Rectangle(width, height);
        drawData.applyTexturizer(this.texturizer);
        int contentId = SGE.contents().add(drawData, properties);
        super.applyContentProperties(contentId);

        doFinaltransforms(contentId);
        return contentId;
    }
    private void doFinaltransforms(int contentId) {

        //Step 2...
        //Rotate the Sign
        SimpleVector initialNormalV   = new SimpleVector(0f, 0f, 0f, 0f, 0f, 1f);
        SimpleVector requestedNormalV = new SimpleVector(0f, 0f, 0f, normalPointer.getX(), normalPointer.getY(), normalPointer.getZ());
        requestedNormalV = (SimpleVector) Calc_VectorMath.normalizeVector(requestedNormalV);

        float angleBetweenNorms = Calc_VectorMath.angleBetween(initialNormalV, requestedNormalV);

        if(Math.abs(angleBetweenNorms) > .01f) {

            float rotationAngle = angleBetweenNorms;

            float angleToRequested = Calc_VectorMath.angleBetween(new SimpleVector(0f, 0f, 0f, 1f, 0f, 0f), requestedNormalV);
            if (angleToRequested > Constants.PI / 2) {
                rotationAngle = -1f * rotationAngle;
            }

            Transform_Rotate tRotate = Transform_Rotate.builder().rotationAboutY(rotationAngle).build();
            SGEContentWrapper w = SGE.contents().get(contentId);
            SGE.contents().get(contentId).applyTransform(tRotate);
        }

        //Step 2...
        //Move the Sign
        Transform_Move tMove = Transform_Move.builder().toVertex(center).build();
        SGE.contents().get(contentId).applyTransform(tMove);
    }
}
