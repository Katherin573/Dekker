/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class Main {
    public static void main(String[] args) {
             Cuenta cuenta = new Cuenta(2000, 10000);
             Vector<Persona> gente = new Vector<Persona>();
             gente.addElement(new Persona("Inazio", 0, cuenta));
             gente.addElement(new Persona("Claver", 1, cuenta));
             for (int i = 0; i < gente.size(); i++){
                    gente.elementAt(i).start();
             }
       }
}
