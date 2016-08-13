package com.cdaniel.simplegametools.objectextractor;

import com.cdaniel.simplegametools.tools.CastTools;
import com.cdaniel.simplegametools.tools.StringTools;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by christopher.daniel on 8/13/16.
 */
class Unpacker_Wavefront implements Unpacker {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constants/Vars
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private final String LINE_VERTEX   = "v ";
    private final String LINE_NORMAL   = "vn";
    private final String LINE_TEXTURE  = "vt";
    private final String LINE_DRAW     = "f ";

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Unpack Interface
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public ObjectExtract unpack(String data) throws ObjectExtractException{

        try{
            return unpackIt(data);
        }
        catch(UnpackException e){
            throw e.toObjectExtractException();
        }
        catch(Exception e){
            throw new ObjectExtractException(e.getMessage());
        }
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * The Unpack
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private ObjectExtract unpackIt(String data){

        ObjectExtract extract = new ObjectExtract();

        List<String> dataLines = StringTools.stringToLines(data);

        for(String l : dataLines){

            unpackIt_handleLine(l, extract);
        }

        return extract;
    }
    private void unpackIt_handleLine(String l, ObjectExtract extract){

        if(StringTools.isEmpty(l)){
            return;
        }

        String linePrefix = l.substring(0,2);

        switch (linePrefix) {

            case LINE_VERTEX:
                unpackIt_handleVertexLine(l, extract);
                break;

            case LINE_NORMAL:
                unpackIt_handleNormalLine(l, extract);
                break;

            case LINE_TEXTURE:
                unpackIt_handleTextureLine(l, extract);
                break;

            case LINE_DRAW:
                unpackIt_handleDrawLine(l, extract);
                break;
        }
    }
    private void unpackIt_handleVertexLine(String l, ObjectExtract extract){

        String params[] = l.split(" ");

        float x = CastTools.extract_Float(params[1]);
        float y = CastTools.extract_Float(params[2]);
        float z = CastTools.extract_Float(params[3]);

        extract.addVertex(x, y, z);
    }
    private void unpackIt_handleNormalLine(String l, ObjectExtract extract){

        String params[] = l.split(" ");

        float x = CastTools.extract_Float(params[1]);
        float y = CastTools.extract_Float(params[2]);
        float z = CastTools.extract_Float(params[3]);

        extract.addNormal(x, y, z);
    }
    private void unpackIt_handleTextureLine(String l, ObjectExtract extract){

        String params[] = l.split(" ");

        float x = CastTools.extract_Float(params[1]);
        float y = CastTools.extract_Float(params[2]);
        float z = CastTools.extract_Float(params[3]);

        extract.addTexture(x, y, z);
    }
    private void unpackIt_handleDrawLine(String l, ObjectExtract extract){

        String params[] = l.split(" ");

        if(params.length > 4){
            throw new UnpackException("Faces must be Triangular - Your data gives " + (params.length-1) + " vertices per face");
        }

        String d1Params[] = params[1].split(Pattern.quote("/"));
        String d2Params[] = params[2].split(Pattern.quote("/"));
        String d3Params[] = params[3].split(Pattern.quote("/"));


        int dv1 = CastTools.extract_Integer(d1Params[0]);
        int dv2 = CastTools.extract_Integer(d2Params[0]);
        int dv3 = CastTools.extract_Integer(d3Params[0]);

        extract.addDrawData(dv1, dv2, dv3);
    }




    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Exception
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private class UnpackException extends RuntimeException{

        public UnpackException(String message){
            super(message);
        }

        private ObjectExtractException toObjectExtractException(){
            return new ObjectExtractException(this.getMessage());
        }
    }
}
