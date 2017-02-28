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
    
    private static HashMap<String,Integer> players = new HashMap(); //Key-Username, Value-Score
    private static int round=0;
    private static int winScore=3;
    private static int monster = 1;

    public static int getRound() {
        return round;
    }

    public static int getMonster() {
        return monster;
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
    }
    
    int newMonster(){
        Random rand = new Random();
        int num = rand.nextInt(9);
        monster = num;
        return num;
    }
    
    //addPoint returns true if the player wins, else returns false;
    boolean addPoint(String username){
        int points = players.get(username) + 1;
        if (points==winScore)
            return true;
        players.put(username, points+1);
        return false;
    }
    
}
