/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;

/**
 *
 * @author M
 */
public class TCP implements Serializable{
    
    Integer round;
    int monster;
    String username;

    public TCP(int round, int monster, String username) {
        this.round = round;
        this.monster = monster;
        this.username = username;
    }   
    
}
