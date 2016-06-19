package com.cdaniel.simplegameengine.plugins.construction.simpleshapes.twoD;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.constants.Constants;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

/**
 * Created by christopher.daniel on 5/4/16.
 */
public class Circle_DashedLines extends AbstractShape implements DrawableData {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     * Additional Draw Paramaters
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float divisions = 20f;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     *  Construct / Clone
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Circle_DashedLines(){

    }
    public Circle_DashedLines copy(){

        Circle_DashedLines copy = new Circle_DashedLines();
        setCopiesData(copy);
        return copy;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Calculate the SGEContentWrapper Data
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void calculateDrawData() {

        this.vertices = new VertexCollection();
        this.drawOrder = new DrawOrderCollection();

        float PI         = Constants.PI;
        float theta      = 0;
        float thetaDelta = PI / divisions;

        for(theta = 0; theta<PI*2; theta += thetaDelta) {

            float thisX = size * (float) Math.cos(theta);
            float thisY = 0f;
            float thisZ = size * (float) Math.sin(theta);

            SimpleVertex v = new SimpleVertex(thisX, thisY, thisZ);
            this.vertices.push(v);
            this.drawOrder.push_onePlusPrior();
        }
    }
}
