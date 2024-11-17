package race4x100;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author eugid
 */
public class Atleta extends Thread {

    private static int turnoActual = 1; // Variable estática para controlar el turno actual
    private static final Object testigo = new Object(); // Objeto compartido como testigo
    private final int numeroAtleta;
    private final int totalAtletas; // Número total de atletas en la carrera
    private long tiempoInicio;
    private long tiempoFinal;

    public Atleta(int numero, int totalAtletas) {
        this.numeroAtleta = numero;
        this.totalAtletas = totalAtletas;
    }

    @Override
    public void run() {
        synchronized (testigo) {
            try {
                while (turnoActual != numeroAtleta) {
                    testigo.wait(); // Espera hasta que sea su turno
                }

                // Simulación del tiempo de carrera
                tiempoInicio = System.currentTimeMillis();
                int tiempoCarrera = (int) (9000 + Math.random() * 2000); // Entre 9 y 11 segundos
                System.out.println("Atleta " + numeroAtleta + " está corriendo...");
                if(numeroAtleta < 4){
                    System.out.println("Atleta " + (numeroAtleta + 1) + " está esperando testigo...");
                }
                Thread.sleep(tiempoCarrera);
                if(numeroAtleta < 4){
                    System.out.println("Atleta " + (numeroAtleta + 1) + " recibe testogo...");
                }
                tiempoFinal = System.currentTimeMillis();
                System.out.println("Atleta " + numeroAtleta + " terminó en " + (tiempoFinal - tiempoInicio) / 1000.0 + " segundos.");

                // Cambiar al siguiente turno
                turnoActual++;
                testigo.notifyAll(); // Notificar a todos los hilos

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

