package com.cdaniel.simplegameengine.core;

import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;


/**
 * Created by christopher.daniel on 5/4/16.
 */

/**
 * Describes transformation classes which transform Vertices (move, rotate, resize)
 *
 * Vertex transforms are a special subset of Vector transforms. These actual could be considerd transforms
 * that act upon vectors whose origins are always zero and end points are said vertex.
 *
 * @author      Christopher Daniel
 * @created       2016-05-04
 */
public interface Transform {

    /**
     * Transform each Vertex in a VertexCollection.
     * <p>
     * Convenience method....each vertex in the collection will simple be passed through this classes'
     * transform(vertex) method.
     * @param  vertices a vertex collection
     */
    void transform(VertexCollection vertices);


    /**
     * Transform a Vertex
     * @param  vertex - vertex to transform
     */
    void transform(Vertex vertex);

}
