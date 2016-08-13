package com.cdaniel.simplegametools.objectextractor;

/**
 * Created by christopher.daniel on 8/13/16.
 */
public class ObjectExtractor {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Enum / Vars
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public enum ObjectType{
        WAVEFRONT
    };

    private ObjectType ot;
    private Unpacker u;

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ObjectExtractor(ObjectType objectType){

        this.setObjectType(objectType);
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Extract Function
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setObjectType(ObjectType objectType){

        if(objectType == ot){
            return;
        }
        this.ot = objectType;

        if(objectType == ObjectType.WAVEFRONT){
            u = new Unpacker_Wavefront();
        }
    }
    public ObjectExtract extract(String objectdata) throws ObjectExtractException{

        try {
            return u.unpack(objectdata);
        }
        catch(ObjectExtractException e){
            throw e;
        }
    }
}
