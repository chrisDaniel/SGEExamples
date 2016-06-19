package com.cdaniel.simplegameengine.plugins.construction.texturizer;

import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;

/**
 * Created by christopher.daniel on 6/4/16.
 */
public class Texturizer_repeatExactly implements Texturizer {

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Variables
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private final int textureId;

    private final float repeatX;
    private final float repeatY;
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Constructor
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public Texturizer_repeatExactly(int textureId, int repeatX, int repeatY) {
        this.textureId = textureId;
        this.repeatX = (float) repeatX;
        this.repeatY = (float) repeatY;
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

        vc.iterator.reset();
        while(vc.iterator.next()){
            vc.iterator.transformX(vc.iterator.x * repeatX);
            vc.iterator.transformY(vc.iterator.y * repeatY);
        }
    }
}
