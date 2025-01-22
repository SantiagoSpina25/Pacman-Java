package Modelo;

/**
 *
 * @author Santiago Spina
 */
public class Contador extends Thread{
    private Tablero tablero;

    public Contador(Tablero tablero) {
        this.tablero = tablero;
    }

    @Override
    public void run() {
        while (true) {
            tablero.disminuirTiempo();
            try {
                // Espera 1 segundo antes de restar tiempo nuevamente
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //System.out.println("El juego ha terminado.");
    }
}
