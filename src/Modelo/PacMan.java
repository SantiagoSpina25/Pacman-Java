package Modelo;

/**
 *
 * @author Santiago Spina
 */
public class PacMan extends Thread {

    private Tablero tablero;
    private int posX = 9;
    private int posY = 9;
    
    public PacMan(Tablero tablero){
        this.tablero= tablero;
    }
    
    @Override
    public void run() {
        while(true){
            tablero.mover("P", posX,posY);
        }
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    
}
