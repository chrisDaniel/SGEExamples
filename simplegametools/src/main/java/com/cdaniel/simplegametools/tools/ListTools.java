package com.cdaniel.simplegametools.tools;

import java.util.List;

/**
 * Created by christopher.daniel on 8/13/16.
 */
public class ListTools {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Evaluators
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public static boolean isEmpty(List list){

        if(list == null){
            return true;
        }
        if(list.size() == 0){
            return true;
        }
        return false;
    }
}
