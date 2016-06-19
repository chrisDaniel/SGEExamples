package com.cdaniel.simplegameengine.engine;

import com.cdaniel.simplegameengine.utils.constants.Constants;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by christopher.daniel on 5/7/16.
 */
public class SGEDraw implements SGEPipeline {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Constructor
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    SGEDraw(){

    }



    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Draw Execution
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void draw(GL10 gl){

        for(SGEContentWrapper w : SGE.contents().getVisibleContent()){

            if(w.getTextureId() > 0 && w.getTextureBuffer() != null){
                drawTextured(gl, w);
            }
            else if(w.getNormalBuffer() != null){
                drawColored(gl, w);
            }
            else{
                drawBasic(gl, w);
            }
        }
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Private Draw Facilitators
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private void drawBasic(GL10 gl, SGEContentWrapper wrapper){

        // Step 1...
        // Since this shape uses vertex arrays, enable them
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);

        // Step 2...
        // draw the shape and color
        gl.glColor4f(wrapper.getColor().getRed(), wrapper.getColor().getGreen(),
                wrapper.getColor().getBlue(), wrapper.getColor().getAlpha());


        gl.glVertexPointer(
                Constants.GL_COORDS_PER_VERTEX_3,
                GL10.GL_FLOAT,
                0,
                wrapper.getVertexBuffer());

        gl.glDrawElements(
                wrapper.getGlDrawShape(),
                wrapper.getDrawSize(),
                GL10.GL_UNSIGNED_SHORT,
                wrapper.getDrawBuffer());


        // Step 3...
        // Disable vertex array drawing to avoid conflicts with shapes that don't use it
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
    private void drawColored(GL10 gl, SGEContentWrapper wrapper){

        // Step 1...
        // Since this shape uses vertex arrays, enable them
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);

        // Step 2...
        // draw the shape and color
        SGE.textures().clearDrawTexture(gl);
        SGE.lighting().setMaterialProperties(gl, wrapper);
        gl.glColor4f(wrapper.getColor().getRed(), wrapper.getColor().getGreen(),
                wrapper.getColor().getBlue(), wrapper.getColor().getAlpha());


        gl.glVertexPointer(
                Constants.GL_COORDS_PER_VERTEX_3,
                GL10.GL_FLOAT,
                0,
                wrapper.getVertexBuffer());

        gl.glNormalPointer(
                GL10.GL_FLOAT,
                0,
                wrapper.getNormalBuffer());

        gl.glDrawElements(
                wrapper.getGlDrawShape(),
                wrapper.getDrawSize(),
                GL10.GL_UNSIGNED_SHORT,
                wrapper.getDrawBuffer());


        // Step 3...
        // Disable vertex array drawing to avoid conflicts with shapes that don't use it
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
    }
    private void drawColoredPerVertex(GL10 gl, SGEContentWrapper wrapper){

        // Step 1...
        // Since this shape uses vertex arrays, enable them
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);

        // Step 2...
        // draw the shape and color

        SGE.lighting().setMaterialProperties(gl, wrapper);
        gl.glColor4f(wrapper.getColor().getRed(), wrapper.getColor().getGreen(),
                wrapper.getColor().getBlue(), wrapper.getColor().getAlpha());


        gl.glVertexPointer(
                Constants.GL_COORDS_PER_VERTEX_3,
                GL10.GL_FLOAT,
                0,
                wrapper.getVertexBuffer());

        gl.glColorPointer(
                4,
                GL10.GL_FLOAT,
                0,
                wrapper.getColorBuffer());

        gl.glNormalPointer(
                GL10.GL_FLOAT,
                0,
                wrapper.getNormalBuffer());

        gl.glDrawElements(
                wrapper.getGlDrawShape(),
                wrapper.getDrawSize(),
                GL10.GL_UNSIGNED_SHORT,
                wrapper.getDrawBuffer());


        // Step 3...
        // Disable vertex array drawing to avoid conflicts with shapes that don't use it
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
    }
    private void drawTextured(GL10 gl, SGEContentWrapper wrapper) {

        // Step 1...
        // Since this shape uses vertex arrays, enable them
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

        // Step 2...
        // draw the shape
        SGE.lighting().setMaterialProperties(gl, wrapper);
        SGE.textures().setDrawTexture(wrapper.getTextureId(), gl);
        gl.glColor4f(1f, 1f, 1f, 1f);

        gl.glVertexPointer(
                Constants.GL_COORDS_PER_VERTEX_3,
                GL10.GL_FLOAT,
                0,
                wrapper.getVertexBuffer());

       gl.glNormalPointer(
              GL10.GL_FLOAT,
              0,
              wrapper.getNormalBuffer());

        gl.glTexCoordPointer(
                Constants.GL_COORDS_PER_VERTEX_2,
                GL10.GL_FLOAT,
                0,
                wrapper.getTextureBuffer());

        gl.glDrawElements(
                wrapper.getGlDrawShape(),
                wrapper.getDrawSize(),
                GL10.GL_UNSIGNED_SHORT,
                wrapper.getDrawBuffer());

        // Step 3...
        // Disable vertex array drawing to avoid conflicts with shapes that don't use it
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Pipeline Contract
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private boolean contentFreezeInPlace = false;

    void beforeDraw(GL10 gl){}

    public void afterDraw(GL10 gl){}

    @Override
    public void contentFreeze(boolean isFrozen){
        contentFreezeInPlace = isFrozen;
    }
    @Override
    public void pause(boolean isPaused){
    }
    @Override
    public void deleteEverything(){
    }
}
