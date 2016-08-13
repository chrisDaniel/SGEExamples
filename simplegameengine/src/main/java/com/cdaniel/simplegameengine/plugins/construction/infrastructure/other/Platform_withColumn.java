package com.cdaniel.simplegameengine.plugins.construction.infrastructure.other;

import com.cdaniel.simplegameengine.core.DrawableData;
import com.cdaniel.simplegameengine.plugins.construction.infrastructure.main.AbstractInfrastructureDrawData;
import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;
import com.cdaniel.simplegameengine.utils.constructs.SimpleVertex;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/22/16.
 */
public class Platform_withColumn extends AbstractInfrastructureDrawData implements DrawableData {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Draw Paramaters
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private float widthX;
    private float lengthZ;
    private float heightY;

    private final float baseHeightPercent = .1f;
    private final float columnHeightPercent = .85f;
    private final float platformHeightPercent = .05f;
    
    @Override
    public int getGlDrawShape(){

        return GL10.GL_TRIANGLES;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
     *  Construct / Clone
     *
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Platform_withColumn(float widthX, float lengthZ, float heightY){

        this.widthX = widthX;
        this.lengthZ = lengthZ;
        this.heightY = heightY;
    }
    public Platform_withColumn copy(){

        Platform_withColumn copy = new Platform_withColumn(widthX, lengthZ, heightY);
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

        //Drawing 3 Pieces of Structure
        //                                _______ 
        //  A) Platform ... trapezoid     \     /
        //       30% height                -----
        //                                  ---
        //  B) Column ... Rectangle        |   |
        //       60% height                |   |
        //                                  ---
        // 
        //  C) Base ..... trapezoid         ---
        //       10% height                /   \
        //                                 -----

        calculateDrawData_C();
        calculateDrawData_B();
        calculateDrawData_A();
    }
    private void calculateDrawData_A() {


        //data 1...
        //bottom
        float b_baseX = this.widthX * .3f;
        float b_baseZ = this.widthX * .3f;

        float b_xLeft = -1f * b_baseX / 2;
        float b_xRight = b_baseX / 2;
        float b_zNear = b_baseZ / 2;
        float b_zFar = -1f * b_baseZ / 2;

        //data 2...
        //top
        float t_baseX = this.widthX * 1f;
        float t_baseZ = this.widthX * 1f;

        float t_xLeft = -1f * t_baseX / 2;
        float t_xRight = t_baseX / 2;
        float t_zNear = t_baseZ / 2;
        float t_zFar = -1f * t_baseZ / 2;

        //and the height
        float platformHeight = this.heightY * platformHeightPercent;
        float yBottom = this.heightY - platformHeight;
        float yTop = this.heightY;

        //use the helper
        calculateDrawData_AC_Helper(b_xLeft, b_xRight, b_zNear, b_zFar, t_xLeft, t_xRight, t_zNear, t_zFar, yBottom, yTop);
    }
    private void calculateDrawData_B(){
        
        float columnX = this.widthX * .3f;
        float columnZ = this.widthX * .3f;
        float columnHeight = this.heightY * columnHeightPercent;
        
        float xLeft  = -1f * columnX / 2;
        float xRight = columnX / 2;
        float zNear  = columnZ / 2;
        float zFar   = -1f * columnZ / 2;
        float yBottom = this.heightY * baseHeightPercent;
        float yTop    = yBottom + columnHeight;

        Integer drawStart = this.vertices.getVertexCount();

        //Side 1 ...
        //The Left Face
        this.vertices.push(new SimpleVertex( xLeft,  yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xLeft,  yTop,     zNear));
        this.vertices.push(new SimpleVertex( xLeft,  yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xLeft,  yTop,     zFar));

        this.drawOrder.push(0+drawStart);this.drawOrder.push(2+drawStart);this.drawOrder.push(1+drawStart);
        this.drawOrder.push(1+drawStart);this.drawOrder.push(2+drawStart);this.drawOrder.push(3+drawStart);

        this.normalCollection.push(-1, 0, 0);this.normalCollection.push(-1, 0, 0);
        this.normalCollection.push(-1, 0, 0);this.normalCollection.push(-1, 0, 0);


        this.textureCollection.push(0, 1, 0); this.textureCollection.push(0, 0, 0);
        this.textureCollection.push(1, 1, 0); this.textureCollection.push(1, 0, 0);


        //Side 2...
        //the right face
        this.vertices.push(new SimpleVertex( xRight,  yBottom, zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,    zNear));
        this.vertices.push(new SimpleVertex( xRight,  yBottom, zFar));
        this.vertices.push(new SimpleVertex( xRight,  yTop,    zFar));

        this.drawOrder.push(0+4+drawStart);this.drawOrder.push(2+4+drawStart);this.drawOrder.push(1+4+drawStart);
        this.drawOrder.push(1+4+drawStart);this.drawOrder.push(2+4+drawStart);this.drawOrder.push(3+4+drawStart);

        this.normalCollection.push(1, 0, 0);this.normalCollection.push(1, 0, 0);
        this.normalCollection.push(1, 0, 0);this.normalCollection.push(1, 0, 0);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(0, 0, 0);
        this.textureCollection.push(1, 1, 0); this.textureCollection.push(1, 0, 0);
        
        //Side 3...
        //the top
        this.vertices.push(new SimpleVertex( xLeft,   yTop,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,  zNear));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,  zFar));
        this.vertices.push(new SimpleVertex( xRight,  yTop,  zFar));

        this.drawOrder.push(0+8+drawStart);this.drawOrder.push(2+8+drawStart);this.drawOrder.push(1+8+drawStart);
        this.drawOrder.push(1+8+drawStart);this.drawOrder.push(2+8+drawStart);this.drawOrder.push(3+8+drawStart);

        this.normalCollection.push(0, 1, 0);this.normalCollection.push(0, 1, 0);
        this.normalCollection.push(0, 1, 0);this.normalCollection.push(0, 1, 0);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(0, 0, 0);
        this.textureCollection.push(1, 1, 0); this.textureCollection.push(1, 0, 0);

        //Side 4...
        //the near face
        this.vertices.push(new SimpleVertex( xLeft,   yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zNear));
        this.vertices.push(new SimpleVertex( xRight,  yTop,     zNear));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,     zNear));

        this.drawOrder.push(1+12+drawStart);this.drawOrder.push(2+12+drawStart);this.drawOrder.push(0+12+drawStart);
        this.drawOrder.push(2+12+drawStart);this.drawOrder.push(3+12+drawStart);this.drawOrder.push(0+12+drawStart);

        this.normalCollection.push(0, 0, 1);this.normalCollection.push(0, 0, 1);
        this.normalCollection.push(0, 0, 1);this.normalCollection.push(0, 0, 1);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(0, 0, 0);

        //Side 5...
        //the far face
        this.vertices.push(new SimpleVertex( xLeft,   yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xRight,  yBottom,  zFar));
        this.vertices.push(new SimpleVertex( xRight,  yTop,     zFar));
        this.vertices.push(new SimpleVertex( xLeft,   yTop,     zFar));

        this.drawOrder.push(2+16+drawStart);this.drawOrder.push(1+16+drawStart);this.drawOrder.push(0+16+drawStart);
        this.drawOrder.push(3+16+drawStart);this.drawOrder.push(2+16+drawStart);this.drawOrder.push(0+16+drawStart);

        this.normalCollection.push(0, 0, -1);this.normalCollection.push(0, 0, -1);
        this.normalCollection.push(0, 0, -1);this.normalCollection.push(0, 0, -1);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(1, 0, 0); this.textureCollection.push(0, 0, 0);
    }
    private void calculateDrawData_C() {

        //data 1...
        //bottom
        float b_baseX = this.widthX * .7f;
        float b_baseZ = this.widthX * .7f;

        float b_xLeft = -1f * b_baseX / 2;
        float b_xRight = b_baseX / 2;
        float b_zNear = b_baseZ / 2;
        float b_zFar = -1f * b_baseZ / 2;

        //data 2...
        //top
        float t_baseX = this.widthX * .3f;
        float t_baseZ = this.widthX * .3f;

        float t_xLeft = -1f * t_baseX / 2;
        float t_xRight = t_baseX / 2;
        float t_zNear = t_baseZ / 2;
        float t_zFar = -1f * t_baseZ / 2;

        //and the height
        float baseHeight = this.heightY * baseHeightPercent;
        float yBottom = 0f;
        float yTop = baseHeight;

        //use the helper
        calculateDrawData_AC_Helper(b_xLeft, b_xRight, b_zNear, b_zFar, t_xLeft, t_xRight, t_zNear, t_zFar, yBottom, yTop);
    }

    private void calculateDrawData_AC_Helper(float b_xLeft, float b_xRight,
                                             float b_zNear, float b_zFar,
                                             float t_xLeft, float t_xRight,
                                             float t_zNear, float t_zFar,
                                             float yBottom, float yTop ) {


        Integer drawStartV = this.vertices.getVertexCount();

        //piece 1...
        //vertices

        //bottom vertices
        this.vertices.push(new SimpleVertex( b_xLeft,   yBottom,  b_zNear));
        this.vertices.push(new SimpleVertex( b_xRight,  yBottom,  b_zNear));
        this.vertices.push(new SimpleVertex( b_xRight,  yBottom,  b_zFar));
        this.vertices.push(new SimpleVertex( b_xLeft,   yBottom,  b_zFar));

        this.normalCollection.push(-1, 0, 1);this.normalCollection.push(1, 0, 1);
        this.normalCollection.push( 1, 0, -1);this.normalCollection.push(-1, 0, -1);

        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);
        this.textureCollection.push(0, 1, 0); this.textureCollection.push(1, 1, 0);


        //top vertices
        this.vertices.push(new SimpleVertex( t_xLeft,   yTop,  t_zNear));
        this.vertices.push(new SimpleVertex( t_xRight,  yTop,  t_zNear));
        this.vertices.push(new SimpleVertex( t_xRight,  yTop,  t_zFar));
        this.vertices.push(new SimpleVertex( t_xLeft,   yTop,  t_zFar));

        this.normalCollection.push(-1, 1, 1);this.normalCollection.push(1, 1, 1);
        this.normalCollection.push( 1, 1, -1);this.normalCollection.push(-1, 1, -1);

        this.textureCollection.push(0, 0, 0); this.textureCollection.push(1, 0, 0);
        this.textureCollection.push(0, 0, 0); this.textureCollection.push(1, 0, 0);

        //a midpoint on each one to make triangles
        this.vertices.push(new SimpleVertex( (b_xLeft+b_xRight)/2,  yBottom,  b_zNear));
        this.vertices.push(new SimpleVertex( b_xRight,              yBottom,  (b_zNear+b_zFar) / 2) );
        this.vertices.push(new SimpleVertex( (b_xLeft+b_xRight)/2,  yBottom,  b_zFar));
        this.vertices.push(new SimpleVertex( b_xLeft,               yBottom,  (b_zNear+b_zFar) / 2) );

        this.normalCollection.push(0, 0, 1);this.normalCollection.push(1, 0, 0);
        this.normalCollection.push(0, 0, -1);this.normalCollection.push(-1, 0, 0);
        this.textureCollection.push(.5f, 1, 0); this.textureCollection.push(.5f, 1, 0);
        this.textureCollection.push(.5f, 1, 0); this.textureCollection.push(.5f, 1, 0);



        //piece 2...
        //draw order

        //top face
        this.drawOrder.push(4+drawStartV);this.drawOrder.push(6+drawStartV);this.drawOrder.push(7+drawStartV);
        this.drawOrder.push(5+drawStartV);this.drawOrder.push(6+drawStartV);this.drawOrder.push(4+drawStartV);

        //front face
        this.drawOrder.push(0+drawStartV);this.drawOrder.push(8+drawStartV);this.drawOrder.push(4+drawStartV);
        this.drawOrder.push(1+drawStartV);this.drawOrder.push(5+drawStartV);this.drawOrder.push(8+drawStartV);
        this.drawOrder.push(5+drawStartV);this.drawOrder.push(4+drawStartV);this.drawOrder.push(8+drawStartV);

        //right side
        this.drawOrder.push(1+drawStartV);this.drawOrder.push(9+drawStartV);this.drawOrder.push(5+drawStartV);
        this.drawOrder.push(2+drawStartV);this.drawOrder.push(6+drawStartV);this.drawOrder.push(9+drawStartV);
        this.drawOrder.push(6+drawStartV);this.drawOrder.push(5+drawStartV);this.drawOrder.push(9+drawStartV);

        //back face
        this.drawOrder.push(2+drawStartV);this.drawOrder.push(10+drawStartV);this.drawOrder.push(6+drawStartV);
        this.drawOrder.push(3+drawStartV);this.drawOrder.push(7+drawStartV);this.drawOrder.push(10+drawStartV);
        this.drawOrder.push(7+drawStartV);this.drawOrder.push(6+drawStartV);this.drawOrder.push(10+drawStartV);

        //left side
        this.drawOrder.push(3+drawStartV);this.drawOrder.push(11+drawStartV);this.drawOrder.push(7+drawStartV);
        this.drawOrder.push(4+drawStartV);this.drawOrder.push(0+drawStartV);this.drawOrder.push(11+drawStartV);
        this.drawOrder.push(4+drawStartV);this.drawOrder.push(7+drawStartV);this.drawOrder.push(11+drawStartV);
    }
}
