package com.cdaniel.simplegameengine.plugins.construction.simpleshapes.twoD;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.constants.Constants;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

/**
 * Created by christopher.daniel on 5/2/16.
 */
public class Circle_Filled extends AbstractShape implements DrawableData {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     * Additional Draw Paramaters
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float divisions = 20f;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     *  Construct / Clone
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Circle_Filled(){

    }
    public Circle_Filled copy(){

        Circle_Filled copy = new Circle_Filled();
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

            float nextX = size * (float) Math.cos(theta + thetaDelta);
            float nextY = 0f;
            float nextZ = size * (float) Math.sin(theta);

            SimpleVertex v_this = new SimpleVertex(thisX, thisY, thisZ);
            SimpleVertex v_next = new SimpleVertex(nextX, nextY, nextZ);
            SimpleVertex v_o    = new SimpleVertex(0, 0, 0);

            this.vertices.push(v_this);
            this.vertices.push(v_next);
            this.vertices.push(v_o);

            this.drawOrder.push_onePlusPrior();
            this.drawOrder.push_onePlusPrior();
            this.drawOrder.push_onePlusPrior();
        }
    }
}
