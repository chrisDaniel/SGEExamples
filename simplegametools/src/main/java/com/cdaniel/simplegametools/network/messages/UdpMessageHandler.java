package com.cdaniel.simplegametools.network.messages;

/**
 * Created by christopher.daniel on 8/14/16.
 */
public interface UdpMessageHandler {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Requirements to Handle Messages
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    void handleMessage(UdpMessageIn messageIn);
}
