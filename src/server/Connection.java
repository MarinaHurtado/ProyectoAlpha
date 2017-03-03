/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.Serializable;

/**
 *
 * @author M
 */
public class Connection implements Serializable{
    String broadcastAddress;
    int broadcastPort;
    String tcpAddress;
    int tcpPort;

    public Connection(String broadcastAddress, int broadcastPort, String tcpAddress, int tcpPort) {
        this.broadcastAddress = broadcastAddress;
        this.broadcastPort = broadcastPort;
        this.tcpAddress = tcpAddress;
        this.tcpPort = tcpPort;
    }
    
       
}
