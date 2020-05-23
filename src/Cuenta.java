/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class Cuenta {
    // Propiedades
       private boolean[] bandera = new boolean[2];
       private int turno = 0;
       private int saldoActual;
       private int saldoMaximo;
      
       // Constructor
       public Cuenta(int saldoActual, int saldoMaximo){
             inicializarArray(bandera);
             this.saldoActual = saldoActual;
             this.saldoMaximo = saldoMaximo;
       }
      
       // Metodos
       public synchronized void ingresar(int cantidad, int turno, Persona ultimoCliente){
             bandera[turno] = true;
            
             if (turno == 0)
                    this.turno = 1;
             else
                    this.turno = 0;
            
             if (turno == 0){
                    while(bandera[1] && turno == 1){
                           try{
                                  Thread.sleep(300);
                           }
                           catch(InterruptedException e){}
                    } // Fin While
                   
                    // SECCION CRITICA
                    seccionCriticaIngreso(cantidad, ultimoCliente);
             } // Fin if Thread 0
            
             if (turno == 1){
                    while(bandera[0] && turno == 0){
                           try{
                                  Thread.sleep(300);
                           }
                           catch(InterruptedException e){}
                    } // Fin While
                   
                    // SECCION CRITICA
                    seccionCriticaIngreso(cantidad, ultimoCliente);
             } // Fin if Thread 1
             bandera[turno] = false;
             notifyAll();
       } // Fin ingresar
      
       public synchronized void retirar(int cantidad, int turno, Persona ultimoCliente){
             bandera[turno] = true;
            
             if (turno == 0)
                    this.turno = 1;
             else
                    this.turno = 0;
            
             if (turno == 0){
                    while(bandera[1] && turno == 1){
                           try{
                                  Thread.sleep(300);
                           }
                           catch(InterruptedException e){}
                    } // Fin While
                   
                    // SECCION CRITICA
                    seccionCriticaRetirada(cantidad, ultimoCliente);
             } // Fin if Thread 0
            
             if (turno == 1){
                    while(bandera[0] && turno == 0){
                           try{
                                  Thread.sleep(300);
                           }
                           catch(InterruptedException e){}
                    } // Fin While
                   
                    // SECCION CRITICA
                    seccionCriticaRetirada(cantidad, ultimoCliente);
             } // Fin if Thread 1
             bandera[turno] = false;
             notifyAll();
       } // Fin retirar
      
       private void inicializarArray(boolean[] array){
             array[0] = false;
             array[1] = false;
       }
      
       private void seccionCriticaIngreso(int cantidad, Persona ultimoCliente){
             if (saldoActual + cantidad <= saldoMaximo){
                    saldoActual = saldoActual + cantidad;
                    System.out.println(ultimoCliente.getNombre() + " ha ingresado " + cantidad + " euros");
             }
             else{
                    System.out.println(ultimoCliente.getNombre() + " ha superado el máximo permitido");
                    ultimoCliente.stop();
             }// Fin if zona critica
       }
      
       private void seccionCriticaRetirada(int cantidad, Persona ultimoCliente){
             if (saldoActual - cantidad >= 0){
                    saldoActual = saldoActual + cantidad;
                    System.out.println(ultimoCliente.getNombre() + " ha retirado " + cantidad + " euros");
             }
             else{
                    System.out.println(ultimoCliente.getNombre() + " ha superado el máximo permitido");
                    ultimoCliente.stop();
             } // Fin if zona critica
       }
      
}

