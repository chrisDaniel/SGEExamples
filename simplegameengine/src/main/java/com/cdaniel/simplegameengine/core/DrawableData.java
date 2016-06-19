package com.cdaniel.simplegameengine.core;

/**
 * Created by christopher.daniel on 5/4/16.
 */

import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;

/**
 * Describes a "DrawableData" object in drawGL
 *
 * @author      Christopher Daniel
 * @created       2016-05-04
 */
public interface DrawableData{

    /**
     * The Vertices that represent the current state of this object
     * @return VertexCollection
     */
    VertexCollection getVertexCollection();

    /**
     * The Draw Order represents the order in which OpenGL will draw the vertices
     * @return FloatBuffer
     */
    DrawOrderCollection getDrawOrder();

    /**
     * The Draw Order represents the order in which OpenGL will draw the vertices
     * @return FloatBuffer
     */
    int getGlDrawShape();

    /**
     * The Texture Vertices How an object projects a texture onto itself (its vertices coorespond with x,y on the Texture)
     * @return FloatBuffer
     */
    VertexCollection getTextureCollection();

    /**
     * The Normals for this Drawable Data ... for TextureSupport
     * @return FloatBuffer
     */
    VertexCollection getNormalCollection();

    /**
     * Returns an exact copy of This DrawableData Object
     * @return DrawableData Object
     */
    DrawableData copy();
}
