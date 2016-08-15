package com.cdaniel.simplegameengine.plugins.construction.models.builders;

import com.cdaniel.simplegameengine.plugins.construction.models.drawdata.Data_Cube;
import com.cdaniel.simplegameengine.plugins.construction.models.drawdata.Data_Sphere;

import java.io.BufferedReader;

/**
 * Created by christopher.daniel on 8/13/16.
 */
public class ModelBuilder {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * Core Data Models
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static WavefrontDataBuilder cube(){return new WavefrontDataBuilder(Data_Cube.MODEL_CUBE);}
    public static WavefrontDataBuilder sphere(){return new WavefrontDataBuilder(Data_Sphere.MODEL_SPHERE);}

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * User Data Models
     *
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static WavefrontDataBuilder custom(String wavefrontModelData){return new WavefrontDataBuilder(wavefrontModelData);}
    public static WavefrontDataBuilder custom(BufferedReader modelReader){return new WavefrontDataBuilder(modelReader);}
}
