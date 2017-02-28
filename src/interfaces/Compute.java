/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author sdist
 */
public interface Compute extends Remote{
    
    // Calculate the square of a number
        public double square ( int number ) throws RemoteException;

    // Calculate the power of a number
        public double power ( int num1, int num2, Credential cred) throws RemoteException;
    
}
