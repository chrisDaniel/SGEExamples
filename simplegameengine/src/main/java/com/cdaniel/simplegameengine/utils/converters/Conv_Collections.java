package com.cdaniel.simplegameengine.utils.converters;

import java.util.List;

/**
 * Created by christopher.daniel on 4/14/16.
 */
public class Conv_Collections {

    public static float[] arrayListToarray(List<Float> list){

        float[] array = new float[list.size()];

        for(int i =0; i<list.size(); i++){
            array[i] = list.get(i);
        }

        return array;
    }
    public static short[] arrayListToArray_short(List<Short> shorts){
        short[] array = new short[shorts.size()];

        for(int i =0; i<shorts.size(); i++){
            array[i] = shorts.get(i);
        }

        return array;
    }
}
