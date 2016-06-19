package com.cdaniel.simplegameengine.utils.converters;

/**
 * Created by christopher.daniel on 5/7/16.
 */
public class Conv_PropertyValues {


    public static float extract_Float(Object propValue){

        try{
            if(propValue == null){
                return 0f;
            }
            if(propValue instanceof Integer){
                Integer intV = (Integer) propValue;
                return (float) intV;
            }
            if(propValue instanceof String) {
                String propString = (String) propValue;
                Float floatV = Float.parseFloat(propString);
                return floatV;
            }

            Float f = (Float) propValue;
            return f;
        }
        catch (Exception e){
            return 0f;
        }
    }
    public static int extract_Integer(Object propValue){

        try{
            if(propValue == null){
                return 0;
            }

            if(propValue instanceof String) {
                String propString = (String) propValue;
                Integer intV = Integer.parseInt(propString);
                return intV;
            }
            if(propValue instanceof Float){
                Float f = (Float) propValue;
                String floatString = Float.toString(f);
                Integer intV = Integer.parseInt(floatString);
            }

            Integer intV = (Integer) propValue;
            return intV;
        }
        catch (Exception e){
            return 0;
        }
    }
    public static String extract_String(Object propValue){

        try{
            if(propValue == null){
                return "";
            }

            if(propValue instanceof Integer){
                Integer i = (Integer) propValue;
                return i.toString();
            }
            if(propValue instanceof Float){
                Float f = (Float) propValue;
                return f.toString();
            }

            String stringV = (String) propValue;
            return stringV;
        }
        catch (Exception e){
            return "";
        }
    }
}
