package com.cdaniel.simplegameengine.plugins.construction.simpleshapes.threeD;


import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VertexMath;
import com.cdaniel.simplegameengine.utils.constants.Constants;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

/**
 * Created by christopher.daniel on 4/16/16.
 */
public class Sphere_Textured extends AbstractMesh implements DrawableData {


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     * Draw Paramaters
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float divisions = 11f;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    *  Construct / Clone
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Sphere_Textured(){

    }
    public Sphere_Textured copy(){

        Sphere_Textured copy = new Sphere_Textured();
        setCopiesData(copy);
        return copy;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Calculate the SGEContentWrapper Data
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void calculateDrawData(){

        this.vertices          = new VertexCollection();
        this.drawOrder         = new DrawOrderCollection();
        this.textureCollection = new VertexCollection();
        this.normalCollection  = new VertexCollection();

        float PI         = Constants.PI;
        float thetaDelta = PI / divisions;

        //Strategy:
        //Build this thing up in rings
        //So for each ring navigate the circumfrence
        for(float theta = -PI/2; theta < (PI/2 - .002); theta += thetaDelta) {

            float thetaPrime = theta + thetaDelta;

            //calculate this starting coordinates for this ring layer
            //bottom zyz      -> angle f
            //top zyz (prime) -> angle f + rotation
            float thisX      = size * (float) Math.cos(theta);
            float thisXPrime = size * (float) Math.cos(thetaPrime);
            float thisY      = size * (float) Math.sin(theta);
            float thisYPrime = size * (float) Math.sin(thetaPrime);
            float thisZ      = 0f;
            float thisZPrime = 0f;

            SimpleVertex ringBottomStart = new SimpleVertex(thisX, thisY, thisZ);
            SimpleVertex ringTopStart = new SimpleVertex(thisXPrime, thisYPrime, thisZPrime);

            for(float rotationAngle = 0f; rotationAngle <= 2*PI; rotationAngle += thetaDelta){

                SimpleVertex rotatedBottom = Calc_VertexMath.rotate_aboutYAxis(ringBottomStart, rotationAngle);
                SimpleVertex rotatedTop    = Calc_VertexMath.rotate_aboutYAxis(ringTopStart, rotationAngle);

                this.vertices.push(rotatedBottom);
                this.normalCollection.push(rotatedBottom);
                this.vertices.push(rotatedTop);
                this.normalCollection.push(rotatedTop);

                //draw coordinates / normals...
                //
                // case : not the last one
                // triangle 1 -->  /| ... curr bottom, curr top , next bottom
                // triangle 2 -->  |/ ... curr top, next top, next bottom

                if( rotationAngle+thetaDelta < 2*PI) {

                    drawOrder.push(this.vertices.getVertexCount() - 2); //curr bottom
                    drawOrder.push(this.vertices.getVertexCount() - 1); //curr top
                    drawOrder.push(this.vertices.getVertexCount() - 0); //next bottom

                    drawOrder.push(this.vertices.getVertexCount() - 1); //curr top
                    drawOrder.push(this.vertices.getVertexCount() + 1); //next top
                    drawOrder.push(this.vertices.getVertexCount() + 0); //next bottom
                }

                //texture values
                //project the current coords onto two dimensional image which is normalized as 1x1
                float textX =  rotationAngle / (2*PI);          if(textX > 1f) {textX = 1f;}
                float textY =  1f - (theta+PI/2f) / PI;         if(textY < 0f) {textY = 0f;}
                float textXP = rotationAngle / (2*PI);          if(textXP > 1f){textXP = 1f;}
                float textYP = 1f - (thetaPrime+PI/2f) / PI;    if(textYP < 0f){textYP = 0f;}

                this.textureCollection.push(new SimpleVertex(textX, textY, 0));
                this.textureCollection.push(new SimpleVertex(textXP, textYP, 0));
            }
        }
    }
}
