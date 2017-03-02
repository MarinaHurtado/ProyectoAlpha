/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaces.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import interfaces.Credential;
/**
 *
 * @author sdist
 */
public class ComputeClient {
    
    public static void main(String[] args) throws NotBoundException {
        
        System.setProperty("java.security.policy","file:\\C:\\Users\\M\\Documents\\NetBeansProjects\\ProyectoAlfa\\src\\client\\client.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }

        String name = "Compute";
        Credential user = new Credential();

        Registry registry;
        try {
            registry = LocateRegistry.getRegistry("localhost"); // server's ip address
            GameMethods comp = (GameMethods) registry.lookup(name);
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(ComputeClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
