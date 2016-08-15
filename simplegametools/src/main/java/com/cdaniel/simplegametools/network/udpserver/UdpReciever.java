package com.cdaniel.simplegametools.network.udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/**
 * Created by christopher.daniel on 8/14/16.
 */
public class UdpReciever extends Thread {

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * Variables
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    private DatagramSocket socket;
    private UdpServer server;
    private int listenOnPort;

    private boolean alive = true;
    private boolean awake = true;

    /* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    * The Constructor
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public UdpReciever(int listenOnPort, UdpServer server) {
        try {
            this.listenOnPort = listenOnPort;
            this.server       = server;
            this.socket       = new DatagramSocket(listenOnPort);
        }
        catch (IOException ex) {
            throw new RuntimeException("Creating UPD Server Listener failed", ex);
        }
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
    * The Listener
    *
    * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    @Override
    public void run() {

        try {
            while (alive) {

                try {
                    if (!awake) {
                        wait();
                    }
                    run_waitForMessage();
                }
                catch (InterruptedException e) {}
            }
        }
        finally {
            if(socket != null) {
                socket.close();
            }
        }
    }
    private void run_waitForMessage(){

        try {
            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            packet.setLength(buffer.length);

            socket.setSoTimeout(100);
            socket.receive(packet);

            this.server.routeMessageIn(packet);
        }
        catch (SocketTimeoutException e) {
            //got nothing in time ... just reround
        }
        catch (java.io.IOException ex) {
            //todo - something bad happened on the io
        }
    }
}
