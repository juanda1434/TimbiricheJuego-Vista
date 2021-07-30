/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.poo2.gui.controlador;

import java.io.File;
import java.util.ArrayList;
import ufps.poo2.gui.controlador.locator.ServiceLocator;
import ufps.poo2.gui.utilidades.Utilidades;
import ufps.poo2.negocio.INegocio;
import ufps.poo2.negocio.basedatos.derby.dto.JugadaDTO;

/**
 *
 * @author USUARIO
 */
public class Controlador {
    
    private ServiceLocator localizador;
    
    
    
    
    public boolean ingresarNick(String nick,String directorio){
        boolean exito=false;
        exito=localizador.getNegocio().actualizarNick(nick,directorio);
        return exito;
    }
    
    public Controlador(){
        localizador= ServiceLocator.getInstance();
    }
    
    public boolean crearServidor(String host,int puerto)throws Exception{
        boolean exito=false;
        
            exito = localizador.getNegocio().iniciarServidor(puerto);
       
        return exito;
    }
    
    public boolean conectarCliente(String host,int puerto)throws Exception{
        INegocio negocio=localizador.getNegocio();
        boolean exito=false; 
            if (negocio.crearCliente(host, puerto)) {
                exito=true;        
            }
       
        return exito;
    }
    
    public void enviarMensaje(String mensaje){
        INegocio negocio=localizador.getNegocio();
        try {       
            negocio.enviarMensaje(mensaje);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
     public boolean enviarLinea(int a, int b,int c,int d) throws Exception{
         INegocio negocio=localizador.getNegocio();
         JugadaDTO jugada=new JugadaDTO(a,b,c,d);
         return negocio.ingresarLinea(jugada);
     }
    
     

    public ArrayList<int []> getLineas(){
        INegocio negocio=localizador.getNegocio();
        return negocio.getLineas();
    }
	public ArrayList<int []> getCuadrado(){
            INegocio negocio=localizador.getNegocio();
        return negocio.getCuadrado();
        }
        
        public void iniciarJuego(int tam)throws Exception{
            INegocio negocio=localizador.getNegocio();
            negocio.iniciarPartido(tam);
        }
        
        
        public int empezarPartido() throws Exception{
            INegocio negocio=localizador.getNegocio();
           
            int exito=negocio.empezarPartido();
            return exito;
        }
        
    public String mensaje(){
        INegocio negocio=localizador.getNegocio();
        String men="";
        try {
            men= negocio.getChat();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return men;
    }
    
    public boolean[] esMiTurno(){
        INegocio negocio=localizador.getNegocio();
        boolean[] esMiTur=negocio.actualizarTurno();
        
        return esMiTur;
    }
    
    public String[] sacarPuntos(){
        INegocio negocio=localizador.getNegocio();
        return negocio.mostrarPuntos();
    }
    
    public String[] sacarNombres(){
        INegocio negocio=localizador.getNegocio();
        
      return negocio.sacarNombres();
    }
  
    
    public String validarGanador(){
        String exito="";
        exito=localizador.getNegocio().sacarGanador(); 
        return exito;
    }
    
    public boolean acabar(){
        INegocio negocio=localizador.getNegocio();
        return negocio.acabarConexion();
    }
    
    
 public boolean guardarChat(File chat,String s)throws Exception{
     boolean exito=false;
     INegocio negocio=localizador.getNegocio();
     exito=negocio.escribirChat(chat, s);
     return exito;
 }
 
 public String retornarChat(File file)throws Exception{
     String chat=null;
     INegocio negocio=localizador.getNegocio();
     chat=negocio.leerChat(file);
     return chat;
 }
 
 public void enviarArchivo(String ubicacion,String nombre,int port)throws Exception{     
     INegocio negocio=localizador.getNegocio();
     negocio.enviarArchivo(ubicacion,nombre, port);    
 }
}
