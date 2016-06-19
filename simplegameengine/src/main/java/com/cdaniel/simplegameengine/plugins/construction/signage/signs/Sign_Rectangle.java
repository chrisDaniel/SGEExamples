package com.cdaniel.simplegameengine.plugins.construction.signage.signs;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.plugins.construction.signage.main.AbstractSignDrawData;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

/**
 * Created by christopher.daniel on 5/2/16.
 */
public class Sign_Rectangle extends AbstractSignDrawData implements DrawableData {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     * Draw Paramaters
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float Xwidth;
    private float Yheight;


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     *  Construct / Clone
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Sign_Rectangle(float Xwidth, float Yheight){

        this.Xwidth = Xwidth;
        this.Yheight = Yheight;
    }
    public Sign_Rectangle copy(){

        Sign_Rectangle copy = new Sign_Rectangle(Xwidth, Yheight);
        setCopiesData(copy);
        return copy;
    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Calculate the SGEContentWrapper Data
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void calculateDrawData() {

        float xLeft   = -1 * this.Xwidth / 2f;
        float xRight  =  1 * this.Xwidth / 2f;
        float yBottom = -1 * this.Yheight / 2f;
        float yTop    =  1 * this.Yheight / 2f;

        this.vertices = new VertexCollection();
        this.drawOrder = new DrawOrderCollection();
        this.textureCollection = new VertexCollection();
        this.normalCollection = new VertexCollection();

        this.vertices.push(new SimpleVertex(xLeft,  yBottom, 0));
        this.vertices.push(new SimpleVertex(xRight, yBottom, 0));
        this.vertices.push(new SimpleVertex(xRight, yTop,  0));
        this.vertices.push(new SimpleVertex(xLeft,  yTop,  0));

        this.drawOrder.push(0); this.drawOrder.push(1); this.drawOrder.push(2);
        this.drawOrder.push(0); this.drawOrder.push(2); this.drawOrder.push(3);

        this.normalCollection.push(0, 0, 1); this.normalCollection.push(0, 0, 1);
        this.normalCollection.push(0, 0, 1); this.normalCollection.push(0, 0, 1);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(0, 0, 0);
    }
}
