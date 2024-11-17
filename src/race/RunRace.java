/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package race;

/**
 *
 * @author eugid
 */
public class RunRace {

    public static void main(String[] args) {
         try {
                Race newRace = new Race(8);
                newRace.start();
                newRace.join();
            } catch (InterruptedException ex) {
                System.out.println("Se ha interrumpido el hilo " + Thread.currentThread().getName() + " ." + ex.getMessage());
            }
            System.out.println("La carrera ha terminado");
    }
}
