/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class Persona extends Thread{

       // Propiedades
       private String nombre;
       private int numCliente;
       private Cuenta cuenta;
      
       // Constructor
       public Persona(String nombre, int numCliente, Cuenta cuenta){
             this.nombre = nombre;
             this.numCliente = numCliente;
             this.cuenta = cuenta;
       }
      
       // Métodos
       public void run(){
             while(true){
                    cuenta.ingresar(generarCifra(), numCliente, this);
                    try{
                           sleep(300);
                    }
                    catch(InterruptedException e){}
                   
                    cuenta.retirar(generarCifra(), numCliente, this);
                    try{
                           sleep(300);
                    }
                    catch(InterruptedException e){}
             } // Fin while
       } // Fin run
      
       public int generarCifra(){
             return (int) (Math.random()*500+1);
       }
      
       public String getNombre(){
             return nombre;
       }
}