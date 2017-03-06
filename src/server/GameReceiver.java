/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author M
 */
public class GameReceiver extends Thread {
    
    Game game;
    int tcpPort;

    public GameReceiver(Game game, int tcpPort) {
        this.game = game;
        this.tcpPort = tcpPort;
    }   
    
    @Override
    public void run() {
	try{
            ServerSocket listenSocket = new ServerSocket(tcpPort);
            while(true) {
                Socket clientSocket = listenSocket.accept();  // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
                Connect c = new Connect(clientSocket, game);
                c.start();
            }
	} catch(IOException e) {System.out.println("Listen :"+ e.getMessage());}
    }
   
}

class Connect extends Thread {
        Game game;        
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;
	public Connect (Socket aClientSocket, Game game) {
	    try {
		clientSocket = aClientSocket;
                
                this.game = game;
		
		out =new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
	     } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
	}
        
        @Override
	public void run(){
	    try {
                // an echo server
                TCP message = (TCP) in.readObject();
                String username = message.username;
                System.out.println("Message received from: " + username +" " +message.monster);
                if(message.round == game.round  && message.monster == game.monster) {
                    game.addPoint(username);
                }
                
                out.writeObject(new TCP(-1,-1,""));
	    } 
            catch(EOFException e) {
                System.out.println("EOF:"+e.getMessage());
	    } 
            catch(IOException e) {
                System.out.println("IO:"+e.getMessage());
	    } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
            }finally {
                try {
                    clientSocket.close();
                } catch (IOException e){
                    System.out.println(e);
                }
                }
            }
}
