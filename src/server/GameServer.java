/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.Connection;
import interfaces.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author sdist
 */
public class GameServer implements GameMethods{
    
    static Game game = new Game();
    static String broadcastAddress = "225.4.6.7";
    static int broadcastPort = 4234;
    static String tcpAddress;
    static int tcpPort = 5234;
    
    public static void getIP() throws UnknownHostException{
        tcpAddress = Inet4Address.getLocalHost().getHostAddress();
    }
    
    public static void main(String[] args) throws UnknownHostException, IOException {
        
        System.setProperty("java.security.policy","file:\\C:\\Users\\M\\Documents\\NetBeansProjects\\ProyectoAlfa\\src\\server\\server.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
        String name = "GameServer";        
        
        try {            
            LocateRegistry.createRegistry(1099);
            GameServer engine = new GameServer();
            GameMethods stub = (GameMethods) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
                       
        } catch (RemoteException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getIP();
      
                   
        MulticastSocket moleShoutOut = null;
        Integer monster;
        try {

            InetAddress gameRoom = InetAddress.getByName(broadcastAddress); // destination multicast group 
            moleShoutOut = new MulticastSocket(broadcastPort);
            moleShoutOut.joinGroup(gameRoom); 
            System.out.println("Messages' TTL (Time-To-Live): "+ moleShoutOut.getTimeToLive());
            GameReceiver gR = new GameReceiver(game, tcpPort);
            gR.start();
            String toSend;
            while (true) {
                
                if (!game.w) {
                    game.newRound();
                    monster = game.newMonster();
                    toSend = "round ".concat(((Integer)(game.round)).toString()).concat(" ").concat(monster.toString());
                                      
                } else {
                    toSend = "winner ".concat(game.winner);                    
                    game.reset();
                }
                
                byte [] m = toSend.getBytes();
                DatagramPacket messageOut = new DatagramPacket(m, m.length, gameRoom, broadcastPort);
                moleShoutOut.send(messageOut);
                Thread.sleep(3000);
                
            }          

        }
        catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }
        catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            if(moleShoutOut != null) moleShoutOut.close();
        } 
    }

    
    public GameServer() throws RemoteException{
        super();
    }

    @Override
    public boolean logIn(String username) throws RemoteException {        
        if (game.players.get(username) == null)
            game.addPlayer(username);
        if (game.connected.get(username) == null || !game.connected.get(username)){
            //Connect
            game.connected.put(username, true);
            return true;
        }        
        return false;        
    }    

    @Override
    public void Exit(String username) throws RemoteException {
        game.connected.put(username, false);
    }

    @Override
    public Connection getSettings() throws RemoteException {
        Connection con = new Connection(broadcastAddress, broadcastPort, tcpAddress, tcpPort);
        return con;
    }
}
