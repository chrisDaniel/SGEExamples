package com.cdaniel.simplegametools.tools;

import java.util.Map;

/**
 * Created by christopher.daniel on 8/13/16.
 */
public class MapTools {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Evaluators
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isEmpty(Map map){

        if(map == null){
            return true;
        }
        if(map.size() == 0){
            return true;
        }
        return false;
    }
}
