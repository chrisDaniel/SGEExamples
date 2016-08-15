package com.cdaniel.simplegametools.objectextractor;

import java.io.BufferedReader;

/**
 * Created by christopher.daniel on 8/13/16.
 */
interface Unpacker {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Unpack Interface
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    ObjectExtract unpack(String data) throws ObjectExtractException;
    ObjectExtract unpack(BufferedReader reader) throws ObjectExtractException;
}
