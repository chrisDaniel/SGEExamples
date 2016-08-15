package com.cdaniel.simplegametools.network;

import com.cdaniel.simplegametools.network.messages.UdpMessageHandler;
import com.cdaniel.simplegametools.network.messages.UdpMessageIn;
import com.cdaniel.simplegametools.network.messages.UdpMessageOut;
import com.cdaniel.simplegametools.network.udpserver.UdpServer;
import com.cdaniel.simplegametools.network.udpserver.UdpServerUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by christopher.daniel on 8/14/16.
 */
@Ignore
public class UpdServerTest {


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Variables
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private String remoteHostName   = "localhost";
    private int    remotePort       = 4444;
    private int    listenPort       = 4444;


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Before / After Tests
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Before
    public void setup(){
        try{Thread.sleep(200);} catch (Exception e){}
    }
    @After
    public void after(){

    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Tests....
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Test
    public void udpServer_testSendRecieve(){

        UdpServer server = new UdpServer(remoteHostName, "127.0.0.1", remotePort, listenPort);
        server.start();
        sendRecieveTester(server, "message1");

        server.kill();
    }

    @Test
    public void udpServer_testPauseResume(){

        UdpServer server = new UdpServer(remoteHostName, "127.0.0.1", remotePort, listenPort);
        server.start();
        server.pauseServer();
        server.resumeServer();
        sendRecieveTester(server, "message");

        server.kill();
    }

    @Test
    public void udpServer_testKill_portCheck(){

        assertTrue(UdpServerUtils.isPortAvailable(listenPort));

        UdpServer server = new UdpServer(remoteHostName, "127.0.0.1", remotePort, listenPort);

        server.start();
        assertFalse(UdpServerUtils.isPortAvailable(listenPort));
        server.kill();

        try{Thread.sleep(200);} catch (Exception e){}
        assertTrue(UdpServerUtils.isPortAvailable(listenPort));
    }

    @Test
    public void udpServer_testKill_resendCheck(){

        UdpServer server = new UdpServer(remoteHostName, "127.0.0.1", remotePort, listenPort);
        server.start();

        try{Thread.sleep(200);} catch (Exception e){}
        server.kill();
        try{Thread.sleep(200);} catch (Exception e){}

        UdpServer server2 = new UdpServer(remoteHostName, "127.0.0.1", remotePort, listenPort);
        server2.start();
        sendRecieveTester(server2, "message2");
        server2.kill();
    }

    @Test
    public void udpServer_multipleServersFail(){

        UdpServer server  = null;
        UdpServer server2 = null;


        boolean server2Fails = false;
        try{
            server  = new UdpServer(remoteHostName, "127.0.0.1", remotePort, listenPort);
            server.start();
            server2 = new UdpServer(remoteHostName, "127.0.0.1", remotePort, listenPort);
            server2.start();
        }
        catch (Exception e){
            server2Fails = true;
        }
        finally {
            if(server != null) server.kill();
            if(server2 != null) server2.kill();
        }

        assertTrue(server2Fails);
    }
    @Test
    @Ignore
    public void udpServer_multipleServersPass(){

        UdpServer server  = null;
        UdpServer server2 = null;


        boolean server2Fails = false;
        try{
            server  = new UdpServer(remoteHostName, "127.0.0.1", remotePort, listenPort);
            server.start();
            server2 = new UdpServer(remoteHostName, "127.0.0.1", remotePort, listenPort+9);
            server2.start();
        }
        catch (Exception e){
            server2Fails = true;
        }
        finally {
            if(server != null) server.kill();
            if(server2 != null) server2.kill();
        }

        assertFalse(server2Fails);
    }
    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Helpers
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void sendRecieveTester(UdpServer server, final String message){

        //Step 1...
        //Configure a Message Handler and Start the Server
        HandlerImpl handler = new HandlerImpl();
        server.addMessageHandler(handler);

        //Step 2....
        //Create a messageOut and have the server send it
        UdpMessageOut messageOut = new UdpMessageOut() {public String getMessageString() {return message;}};
        server.sendMessage(messageOut);

        //Step 3....
        //Give it a second and then lets check the handler
        try{Thread.sleep(200);} catch (Exception e){}

        assertEquals(message, handler.getRawMessageText());
    }
    public class HandlerImpl implements UdpMessageHandler{
        String recievedMessage;
        public void handleMessage(UdpMessageIn messageIn) {recievedMessage = messageIn.getRawMessage();}
        public String getRawMessageText(){return recievedMessage;}
    }
}
