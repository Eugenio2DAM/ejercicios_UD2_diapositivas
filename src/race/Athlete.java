/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package race;

/**
 *
 * @author eugid
 */
public class Athlete extends Thread {

    private int dorsal;
    private long tiempoInicio;
    private long tiempoFinal;

    public Athlete(int dorsal) {
        if (dorsal < 0) {
            throw new IllegalArgumentException("Un dorsal no puede ser menor que cero.");
        }
        this.dorsal = dorsal;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void run() {
    try {
        // Esperar el pistoletazo de salida
        synchronized (Race.startRace) {
            while (Race.getSalida() != 1) {
                System.out.println("Corredor " + dorsal + " esperando pistoletazo.");
                Race.startRace.wait(); // Espera hasta ser notificado
            }
        }

        // Una vez notificado, cada hilo ejecuta su carrera de forma independiente
        tiempoInicio = System.currentTimeMillis();
        int tiempoCarrera = (int) (9000 + Math.random() * 2000);
        System.out.println("Corredor " + this.getDorsal() + " empieza a correr.");
        Thread.sleep(tiempoCarrera);
        tiempoFinal = System.currentTimeMillis();

        System.out.println("Corredor " + this.getDorsal() + " tarda "
                + (tiempoFinal - tiempoInicio) + " milisegundos");
    } catch (InterruptedException ex) {
        System.out.println("Se ha interrumpido el hilo " + Thread.currentThread().getName() + ". " + ex.getMessage());
    }
}

}

