package com.cdaniel.simplegametools.objectextractor;

/**
 * Created by christopher.daniel on 8/13/16.
 */
interface Unpacker {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Unpack Interface
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    ObjectExtract unpack(String data) throws ObjectExtractException;
}
