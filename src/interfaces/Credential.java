/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import client.*;
import java.io.Serializable;

/**
 *
 * @author sdist
 */
public class Credential implements Serializable{
    
    private String nombre = "Juan";
    private String estado = "Aguascalientes";
    private int año = 1987;
    private int cred = 123456;

    public Credential() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

    public int getAño() {
        return año;
    }

    public int getCred() {
        return cred;
    }
    
    
    
}
