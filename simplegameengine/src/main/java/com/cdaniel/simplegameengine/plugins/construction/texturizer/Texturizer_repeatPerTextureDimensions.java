package com.cdaniel.simplegameengine.plugins.construction.texturizer;

import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;

/**
 * Created by christopher.daniel on 6/4/16.
 */
public class Texturizer_repeatPerTextureDimensions  implements Texturizer {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private final int textureId;

    private final float textureWidth;
    private final float textureHeight;
    private final float contentWidth;
    private final float contentHeight;

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Constructor
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Texturizer_repeatPerTextureDimensions(int textureId, float textureWidth, float textureHeight, float contentWidth, float contentHeight){
        this.textureId = textureId;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.contentWidth = contentWidth;
        this.contentHeight = contentHeight;
    }

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Texturizer
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public int getTextureId(){
        return textureId;
    }
    @Override
    public void texturize(VertexCollection vc){

        double widthRatio = Math.ceil(contentWidth / textureWidth);
        double heightRatio = Math.ceil(contentHeight / textureHeight);

        vc.iterator.reset();
        while(vc.iterator.next()){
            vc.iterator.transformX(vc.iterator.x * (float) widthRatio);
            vc.iterator.transformY(vc.iterator.y * (float) heightRatio);
        }
    }
}
