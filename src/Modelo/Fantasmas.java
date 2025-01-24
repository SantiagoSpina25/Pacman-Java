package Modelo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santiago Spina
 */
public class Fantasmas extends Thread {

    private Tablero tablero;

    public Fantasmas (Tablero tablero){
        this.tablero= tablero;
    }
    
    @Override
    public void run() {
        while(!tablero.juegoTerminado()){
            //Al mover en el tablero, recibe la nueva posicion y lo actualiza
            tablero.mover(true);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Fantasmas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
