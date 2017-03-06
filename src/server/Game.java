/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author M
 */
public class Game {
    
    HashMap<String,Integer> players = new HashMap(); //Key-Username, Value-Score
    HashMap<String,Boolean> connected = new HashMap(); //Key-Username, Connected
    int round=0;
    int winScore=5;
    int monster = 1;
    boolean w = false;
    String winner;
    boolean hit = false;

    public Game() {
    }

    boolean addPlayer(String username){
        if(!players.containsKey(username)){
            players.put(username, 0);
            return true;
        }
        return false;
    }
    
    void newRound(){
        round++;
        hit = false;
    }
    
    int newMonster(){
        Random rand = new Random();
        int num = rand.nextInt(9);
        monster = num;
        return num;
    }
    
    //addPoint returns true if the player wins, else returns false;
    void addPoint(String username){
        if (!hit) {
            int points = players.get(username) + 1;
            System.out.println("Usuario " +username +" tiene puntos: " +points);
            if (points==winScore && !w) {
                w = true;
                winner = username;
                return;
            }            
            players.put(username, points);
            hit = true;
        }
    }
    
    boolean reset(){
        round = 0;
        players.clear();
        w = false;
        hit = false;
        return false;
    }
    
}
