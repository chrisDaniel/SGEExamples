package com.cdaniel.simplegametools.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christopher.daniel on 8/13/16.
 */
public class StringTools {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Evaluators
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isEmpty(String string){
        if(string == null){
            return true;
        }
        if(string.length() == 0){
            return true;
        }
        return false;
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Translators
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static List<String> stringToLines(String string){

        if(isEmpty(string)){
            return  new ArrayList<>();
        }

        List<String> list = new ArrayList<>();
        String lines[] = string.split("\\r?\\n");

        for(String l : lines){
            list.add(l);
        }
        return list;
    }
}
