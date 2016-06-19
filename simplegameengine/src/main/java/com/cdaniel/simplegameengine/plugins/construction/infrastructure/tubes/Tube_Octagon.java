package com.cdaniel.simplegameengine.plugins.construction.infrastructure.tubes;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.core.Vertex;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.main.AbstractInfrastructureDrawData;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.calculations.Calc_VertexMath;
import com.cdaniel.simplegameengine.utils.constants.Constants;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class Tube_Octagon extends AbstractInfrastructureDrawData implements DrawableData {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Draw Paramaters
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float radius;
    private float height;

    @Override
    public int getGlDrawShape(){

        return GL10.GL_TRIANGLES;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     *  Construct / Clone
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Tube_Octagon(float radius, float height){

        this.radius = radius;
        this.height = height;
    }
    public Tube_Octagon copy(){

        Tube_Octagon copy = new Tube_Octagon(radius, height);
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
        this.textureCollection = new VertexCollection();
        this.normalCollection = new VertexCollection();

        //Drawing 8 faces ... each a rectangle whose y bottom is 0 and y top is height
        //                ... shape of a octagon with first edge at (radius, 0, 0) -> (radius, height, 0) build clockwise

        Vertex vRoot = new SimpleVertex(0, 0, -radius);
        Vertex nRoot = new SimpleVertex(0, 0, -1);

        Vertex v1     = Calc_VertexMath.rotate_aboutYAxis(vRoot, Constants.PI/8);
        Vertex v1_top = new SimpleVertex(v1.getX(), height, v1.getZ());
        Vertex v2     = Calc_VertexMath.rotate_aboutYAxis(v1, -1* Constants.PI/4);
        Vertex v2_top = new SimpleVertex(v2.getX(), height, v2.getZ());

        Vertex n1 = Calc_VertexMath.rotate_aboutYAxis(nRoot, Constants.PI/8);
        Vertex n2 = Calc_VertexMath.rotate_aboutYAxis(n1, -1* Constants.PI/4);

        float alpha = 0;
        for(int iteration=0; iteration<8; iteration++) {

            alpha += -1f * Constants.PI/4;

            this.vertices.push(Calc_VertexMath.rotate_aboutYAxis(v1, alpha));
            this.vertices.push(Calc_VertexMath.rotate_aboutYAxis(v2, alpha));
            this.vertices.push(Calc_VertexMath.rotate_aboutYAxis(v2_top, alpha));
            this.vertices.push(Calc_VertexMath.rotate_aboutYAxis(v1_top, alpha));

            int currCt = iteration * 4;
            this.drawOrder.push(currCt + 0);
            this.drawOrder.push(currCt + 3);
            this.drawOrder.push(currCt + 2);
            this.drawOrder.push(currCt + 0);
            this.drawOrder.push(currCt + 2);
            this.drawOrder.push(currCt + 1);

            this.textureCollection.push(0, 1, 0);
            this.textureCollection.push(1, 1, 0);
            this.textureCollection.push(1, 0, 0);
            this.textureCollection.push(0, 0, 0);

            this.normalCollection.push(Calc_VertexMath.rotate_aboutYAxis(n1, alpha));
            this.normalCollection.push(Calc_VertexMath.rotate_aboutYAxis(n2, alpha));
            this.normalCollection.push(Calc_VertexMath.rotate_aboutYAxis(n2, alpha));
            this.normalCollection.push(Calc_VertexMath.rotate_aboutYAxis(n1, alpha));
        }
    }
}
