/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package race;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author eugid
 */
public class Race extends Thread {

    protected static final Object startRace = new Object();
    private static int salida;
    private int athletes;

    public static int getSalida() {
        return salida;
    }

    public static void setSalida(int newSalida) {
        if (newSalida < 0) {
            throw new IllegalArgumentException("La salida no puede ser negativa.");
        }
        salida = newSalida;
    }

    public Race(int athletes) {
        this.athletes = athletes;
    }

    public void run() {
        try {
            Athlete[] listAthlete = new Athlete[athletes];

            for (int i = 0; i < athletes; i++) {
                listAthlete[i] = new Athlete(i);
                listAthlete[i].start();
            }

            iniciarCarrera();

            synchronized (startRace) {
                setSalida(1);
                startRace.notifyAll();
            }

            for (Athlete athlete : listAthlete) {
                athlete.join();
            }

        } catch (InterruptedException ex) {
            System.out.println("Se ha interrumpido el hilo " + Thread.currentThread().getName() + ". " + ex.getMessage());
        }
    }

    public void iniciarCarrera() {
        Scanner lector = new Scanner(System.in);
        while (salida != 1) {
            try {

                System.out.println("Preparados!!!!");
                Thread.sleep(1000);

                System.out.println("Listos!!!!!");
                Thread.sleep(1000);

                System.out.println("!yaaaaa");
                    synchronized (startRace) {
                    System.out.println("¡La carrera ha comenzado!");
                    setSalida(1);
                    startRace.notifyAll();
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Race.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lector.close();
    }
}
