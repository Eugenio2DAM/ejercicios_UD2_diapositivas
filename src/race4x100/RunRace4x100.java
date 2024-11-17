/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package race4x100;

/**
 *
 * @author eugid
 */
public class RunRace4x100 {
    public static void main(String[] args){
        try {
            final int totalAtletas = 4; // Número de atletas
            
            // Crear atletas
            Atleta atleta1 = new Atleta(1, totalAtletas);
            Atleta atleta2 = new Atleta(2, totalAtletas);
            Atleta atleta3 = new Atleta(3, totalAtletas);
            Atleta atleta4 = new Atleta(4, totalAtletas);
            
            // Iniciar los hilos
            atleta1.start();
            atleta2.start();
            atleta3.start();
            atleta4.start();
            
            // Esperar a que todos los atletas terminen
            atleta1.join();
            atleta2.join();
            atleta3.join();
            atleta4.join();
            
            System.out.println("La carrera ha terminado.");
        } catch (InterruptedException ex) {
            System.out.println("Se ha interrumpido el hilo " + Thread.currentThread().getName() + " ." + ex.getMessage());
        }
    }
}

