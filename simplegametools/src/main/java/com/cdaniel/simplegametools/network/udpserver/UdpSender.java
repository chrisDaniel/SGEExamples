package com.cdaniel.simplegametools.network.udpserver;

import com.cdaniel.simplegametools.network.messages.UdpMessageOut;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Stack;

/**
 * Created by christopher.daniel on 8/14/16.
 */
public class UdpSender extends Thread {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Variables
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private DatagramSocket socket;
    private InetAddress remoteHostAddress;
    private int remoteHostPort;

    private boolean alive = true;
    private boolean awake = true;

    private volatile Stack<UdpMessageOut> messageStack = new Stack<>();

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * The Constructor
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public UdpSender(InetAddress remoteHostAddress, int remoteHostPort) {
        try {
            this.remoteHostAddress = remoteHostAddress;
            this.remoteHostPort    = remoteHostPort;
            this.socket            = new DatagramSocket();
        }
        catch (IOException ex) {
            throw new RuntimeException("Creating UPD Server Sender failed", ex);
        }
    }

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Requesting a Message to be sent
    * Stack is first in first out with push/pop
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void sendMessage(UdpMessageOut message){
        messageStack.push(message);
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Manage Life
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public void setAwake(boolean awake){
        this.awake = awake;
    }
    public void setAlive(boolean alive){
        this.alive = alive;
    }


    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * The Sender
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void run() {

        try {
            while(alive)
            {
                try {
                    if (!awake) {
                        wait();
                    }
                    if (messageStack.size() > 0) {
                        UdpMessageOut messageToSend = this.messageStack.pop();
                        run_send(messageToSend);
                    }
                }
                catch (InterruptedException e) {}
            }
        }
        finally {
            if(socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    private void run_send(UdpMessageOut message){

        try {
            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, remoteHostAddress, remoteHostPort);

            UdpServerUtils.stringToPacket(message.getMessageString(), packet);

            socket.send(packet);
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
        }
    }
}

