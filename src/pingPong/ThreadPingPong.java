/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pingPong;

/**
 *
 * @author eugid
 */
public class ThreadPingPong extends Thread {
    
    private static final Object lock = new Object();
    private Character letter;
    private static boolean isPPrinted = false; // Controla el orden de impresión

    public ThreadPingPong(Character letter) {
        if (!Character.isLetter(letter)) {
            throw new IllegalArgumentException("El caracter debe de ser una letra. ");
        }
        this.letter = letter;
    }

    public Character getLetter() {
        return letter;
    }

    public void run() {
        for (int i = 0; i < 3000; i++) {
            synchronized (lock) {
                try {
                    // Si `word` es "P", espera a que `p` haya impreso
                    if (letter.equals('P')) {
                        while (!isPPrinted) {
                            lock.wait();
                        }
                    } else {
                        // Si `word` es "p", espera a que `P` haya impreso
                        while (isPPrinted) {
                            lock.wait();
                        }
                    }

                    // Imprime el carácter y cambia el turno
                    System.out.print(letter);
                    System.out.flush();

                    // Cambia el estado para alternar entre "P" y "p"
                    isPPrinted = !isPPrinted;
                    lock.notifyAll(); // Despierta al otro hilo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
