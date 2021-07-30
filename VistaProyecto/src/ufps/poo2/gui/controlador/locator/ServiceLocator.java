/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.gui.controlador.locator;

import java.util.logging.Level;
import java.util.logging.Logger;
import ufps.poo2.negocio.INegocio;
import ufps.poo2.negocio.Negocio;

/**
 *
 * @author USUARIO
 */
public class ServiceLocator {
    
    public static ServiceLocator locator;
    private INegocio negocio;
    
    
    private ServiceLocator(){
        try {
            negocio=new Negocio();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static ServiceLocator getInstance(){
        if (locator==null) {
            synchronized(ServiceLocator.class){
                if (locator==null) {
                    locator=new ServiceLocator();
                }
            }
        }
        return locator;
    }
    
    public INegocio getNegocio(){
        return negocio;
    }
}
