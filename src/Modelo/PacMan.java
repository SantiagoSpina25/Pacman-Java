package Modelo;

/**
 *
 * @author Santiago Spina
 */
public class PacMan extends Thread {

    private Tablero tablero;
    private int posX = 9;
    private int posY = 9;
    private String personaje = "P";
    
    public PacMan(Tablero tablero){
        this.tablero= tablero;
    }
    
    @Override
    public void run() {
        while(true){
            //Al mover en el tablero, recibe la nueva posicion y lo actualiza
            int [] posiciones = tablero.mover(getPersonaje(), getPosX(), getPosY());
            setPosY(posiciones[0]);
            setPosX(posiciones[1]);
            
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
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

    public String getPersonaje() {
        return personaje;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }
    
    
}
