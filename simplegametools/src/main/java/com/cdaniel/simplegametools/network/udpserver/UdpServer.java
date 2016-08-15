package com.cdaniel.simplegametools.network.udpserver;

import com.cdaniel.simplegametools.network.messages.UdpMessageHandler;
import com.cdaniel.simplegametools.network.messages.UdpMessageIn;
import com.cdaniel.simplegametools.network.messages.UdpMessageOut;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by christopher.daniel on 8/14/16.
 */
public class UdpServer extends Thread {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Variables
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private String remoteHostName;
    private String remoteIp;
    private int    remotePort;
    private int    listenOnPort;

    private InetAddress remoteHostAddress;
    private UdpReciever reciever;
    private UdpSender sender;

    private boolean started = false;
    private boolean awake   = false;
    private boolean killed  = false;
    private UdpMessageHandler messageHandler;

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Constructor
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public UdpServer(String remoteHostName, String remoteIp, int remotePort, int listenOnPort) {

        if(remoteHostName == null){
            throw new RuntimeException("Must specify host name");
        }
        if(!UdpServerUtils.isPortAvailable(listenOnPort)){
            throw new RuntimeException("Listen port is in use");
        }

        this.remoteHostName = remoteHostName;
        this.remoteIp       = remoteIp;
        this.remotePort     = remotePort;

        this.listenOnPort = listenOnPort;

        establishInetAddress(this.remoteHostName);
        this.sender   = new UdpSender(remoteHostAddress, remotePort);
        this.reciever = new UdpReciever(listenOnPort, this);
    }
    public void establishInetAddress(String hostName) {

        try {
            if (hostName.toLowerCase().equals("localhost")) {
                this.remoteHostAddress = InetAddress.getLocalHost();
            }
            else {
                this.remoteHostAddress = InetAddress.getByName(hostName);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException("Cant resolve remote server");
        }
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Maintain
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void run(){

        this.started = true;
        this.awake = true;
        this.reciever.start();
        this.sender.start();

        while (!killed) {
            //run this thing forever
        }
    }
    public void pauseServer(){

        if(!started) return;
        if(!awake) return;

        this.awake = false;
        this.reciever.setAwake(false);
        this.sender.setAwake(false);
    }
    public void resumeServer(){

        if(!started) return;
        if(awake) return;

        this.awake = true;
        this.reciever.notify();
        this.reciever.setAwake(true);
        this.sender.notify();
        this.sender.setAwake(true);
    }
    public void kill(){
        if(reciever != null){
            this.reciever.setAlive(false);
            //this.reciever.interrupt();
            reciever = null;
        }
        if(sender != null){
            this.sender.setAlive(false);
            this.sender.interrupt();
            reciever = null;
        }
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Messaging
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void sendMessage(UdpMessageOut messageOut){
        if(!awake) throw new RuntimeException("Udp Server is currently Stopped");
        this.sender.sendMessage(messageOut);
    }
    public void addMessageHandler(UdpMessageHandler handler){
        this.messageHandler = handler;
    }
    public void routeMessageIn(DatagramPacket packetIn){

        String message = UdpServerUtils.stringFromPacket(packetIn);
        UdpMessageIn messageIn = new UdpMessageIn_Default(message);
        this.messageHandler.handleMessage(messageIn);
    }
}
