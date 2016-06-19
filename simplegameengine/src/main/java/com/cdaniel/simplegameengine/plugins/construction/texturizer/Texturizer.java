package com.cdaniel.simplegameengine.plugins.construction.texturizer;

import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;

/**
 * Created by christopher.daniel on 6/4/16.
 */
public interface Texturizer {


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~/
    * Texturizer
    *
    *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    int getTextureId();

    void texturize(VertexCollection vc);
}
