/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pingPong;
/**
 *
 * @author eugid
 */
public class RunPingPong {

    public static void main(String[] args) {
        try {
            ThreadPingPong pingPongOne = new ThreadPingPong('P');
            ThreadPingPong pingPongTwo = new ThreadPingPong('p');
            pingPongOne.start();
            pingPongTwo.start();
            pingPongOne.join();
            pingPongTwo.join();
            //pingPongOne.setPriority(Thread.MAX_PRIORITY);
            //pingPongTwo.setPriority(Thread.MIN_PRIORITY);
            System.out.println();
        } catch (InterruptedException ex) {
            System.out.println("Se interrumpió en hilo " + Thread.currentThread().getName() + " ." + ex.getMessage());
        }
    }
}
