package com.cdaniel.simplegameengine.utils.converters;

import com.cdaniel.simplegameengine.utils.assemblers.DrawOrderCollection;
import com.cdaniel.simplegameengine.utils.assemblers.VertexCollection;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;


/**
 * Created by christopher.daniel on 5/4/16.
 */
public class Conv_ToBuffer {

    public static FloatBuffer getFloatBuffer(float[] toBuffer) {

        ByteBuffer bb = ByteBuffer.allocateDirect(toBuffer.length * 4);
        bb.order(ByteOrder.nativeOrder());

        FloatBuffer sb = bb.asFloatBuffer();
        sb.put(toBuffer);
        sb.position(0);

        return sb;
    }
    public static ShortBuffer getShortBuffer(short[] toBuffer) {

        ByteBuffer bb = ByteBuffer.allocateDirect(toBuffer.length * 2);
        bb.order(ByteOrder.nativeOrder());

        ShortBuffer sb = bb.asShortBuffer();
        sb.put(toBuffer);
        sb.position(0);

        return sb;
    }

    public static FloatBuffer bufferFromVertexCollection(VertexCollection vertexCollection){

        return getFloatBuffer(vertexCollection.getCoordinateArray());
    }
    public static FloatBuffer bufferFromTextureCollection(VertexCollection vertexCollection){

        return getFloatBuffer(vertexCollection.getCoordinateArray(true, true, false));
    }
    public static ShortBuffer bufferFromDrawOrderCollection(DrawOrderCollection drawOrderCollection){

        int numberDraws = drawOrderCollection.getDrawOrderCount();
        short[] drawOrderArray = new short[numberDraws];

        int counter = 0;
        for(int o : drawOrderCollection.getDrawOrder()){
            drawOrderArray[counter] = (short) o;
            counter++;
        }

        return getShortBuffer(drawOrderArray);
    }
}
