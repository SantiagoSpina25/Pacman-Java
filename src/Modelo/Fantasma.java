package Modelo;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santiago Spina
 */
public class Fantasma extends Thread {

    private Tablero tablero;
    private String personaje = "F";
    private int posX = 9;
    private int posY = 6;
    
    public Fantasma(Tablero tablero, int posX, int posY){
        this.tablero= tablero;
        this.posX = posX;
        this.posY = posY;
    }
    
    @Override
    public void run() {
        while(true){
            int [] posiciones = tablero.mover(getPersonaje(), getPosX(), getPosY());
            setPosX(posiciones[1]);
            setPosY(posiciones[0]);
            
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Fantasma.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posicionx) {
        this.posX = posicionx;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posiciony) {
        this.posY = posiciony;
    }

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }
    
    
    
}
