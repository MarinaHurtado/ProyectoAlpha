/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.Serializable;

/**
 *
 * @author M
 */
public class Connection implements Serializable{
    String dirMulticast;
    int multicastSock;
    int tcpServer;
    String ipServ;

    public Connection(String dirMulticast, int multicastSock, int tcpServer, String ipServ) {
        this.dirMulticast = dirMulticast;
        this.multicastSock = multicastSock;
        this.tcpServer = tcpServer;
        this.ipServ = ipServ;
    }
    
    
}
