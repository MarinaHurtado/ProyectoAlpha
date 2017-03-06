/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaces.GameMethods;
import java.awt.Image;
import java.io.IOException;
import java.net.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;

/**
 *
 * @author M
 */
public class MoleReceiver extends Thread{
    
    Connection con;
    JButton btns[];
    static Integer round;
    Mole groseria;

    public MoleReceiver(Connection con, JButton[] btns, Mole g) {
        this.con = con;
        this.btns = btns;
        groseria = g;
    }
    
    @Override
    public void run() {
        MulticastSocket socket = null;
        try {
            InetAddress group = InetAddress.getByName(con.broadcastAddress);
            socket = new MulticastSocket(con.broadcastPort);
            socket.joinGroup(group);
            while(true){
                byte[] buffer = new byte[100];
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                socket.receive(messageIn);
                String roundInfo[] = new String(messageIn.getData()).trim().split(" ");
                //checar si se escucha varias veces lo mismo
                //Button images
                if (roundInfo[0].equals("round")){ // 1-ronda 2-monstruo
                    round = Integer.parseInt(roundInfo[1]);
                    int monster = Integer.parseInt(roundInfo[2]);
                    System.out.println(btns[monster].toString() + "MoleR");
                    ImageIcon imgHole = new ImageIcon(getClass().getResource("/client/hole.png"));
                    ImageIcon imgMole = new ImageIcon(getClass().getResource("/client/mole.png"));
                    ImageIcon img = new ImageIcon(imgMole.getImage().getScaledInstance(imgHole.getIconHeight(), imgHole.getIconWidth(), 0));
                    btns[monster].setIcon(img);
                    Thread.sleep(2000);
                    btns[monster].setIcon(imgHole);
                }
                
                if (roundInfo[0].equals("winner")) {
                    System.out.println(roundInfo[1]);
                    if (roundInfo[1].equals(groseria.username)) {
                        JOptionPane.showMessageDialog(groseria, "Felicidades!", "Eres el ganador ".concat(groseria.username), 1);
                    } else {
                        JOptionPane.showMessageDialog(groseria, "Perdiste!", "Lo sentimos ".concat(groseria.username), 1);
                    }
                    
                    
                    System.setProperty("java.security.policy","file:\\C:\\Users\\M\\Documents\\NetBeansProjects\\ProyectoAlfa\\src\\client\\client.policy");

                    if (System.getSecurityManager() == null) {
                        System.setSecurityManager(new SecurityManager());
                    }

                    String name = "GameServer";


                    Registry registry;
                    try {
                        registry = LocateRegistry.getRegistry("localhost"); // server's ip address
                        GameMethods gameManager = (GameMethods) registry.lookup(name);
                        gameManager.Exit(groseria.username);
                        groseria.dispose();
                        Login ventana = new Login();
                        ventana.setVisible(true);
                        this.stop();
                    } catch (RemoteException | NotBoundException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }    }
                    
                    
                }              
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(MoleReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MoleReceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MoleReceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
