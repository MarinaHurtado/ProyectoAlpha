/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import interfaces.GameMethods;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.Credential;
/**
 *
 * @author sdist
 */
public class ComputeServer implements GameMethods {
    
    Game game = new Game();
    
    public static void main(String[] args) {
        
        System.setProperty("java.security.policy","file:\\C:\\Users\\M\\Documents\\NetBeansProjects\\ProyectoAlfa\\src\\server\\server.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        
        String name = "GameServer";
        
        try {
            LocateRegistry.createRegistry(1099);

            ComputeServer engine = new ComputeServer();
            GameMethods stub = (GameMethods) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            
        } catch (RemoteException ex) {
            Logger.getLogger(ComputeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    public ComputeServer() throws RemoteException{
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
}
