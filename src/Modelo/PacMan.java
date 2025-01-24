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

    public PacMan(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public void run() {
        while (!tablero.juegoTerminado()) {
            tablero.mover(false);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        if (tablero.getObjetivosRecogidos() == 4) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\nJUEGO TERMINADO, HAS RECOGIDO LOS 4 OBJETIVOS. GANASTE\n\n\n\n\n\n\n\n\n\n");
        } else {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\nJUEGO TERMINADO, PERDISTE\n\n\n\n\n\n\n\n\n\n");
        }
        System.exit(0);

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
