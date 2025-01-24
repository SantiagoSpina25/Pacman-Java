package Modelo;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Santiago Spina
 */
public class Tablero {

    //Tablero de 15 de alto y 19 de ancho
    private String tablero[][] = new String[15][19];
    private int vidasRestantes;
    private int tiempoRestante;
    private int objetivosRecogidos = 0;
    private PacMan pacman;

    private String direccionPacman = "DERECHA";

    private final Semaphore semaforoTablero = new Semaphore(1);

    public Tablero(int vidasRestantes, int tiempoRestante, PacMan pacman) {
        this.vidasRestantes = vidasRestantes;
        this.tiempoRestante = tiempoRestante;
        this.pacman = pacman;

        rellenarTablero();
    }

    //Este metodo mueve tanto a los fantasmas como al pacman y recibe por parametros un booleano (true si lo llama un fantasma y false si es el pacman)
    public void mover(boolean esFantasma) {
        try {
            semaforoTablero.acquire();

            if (esFantasma) {
                // Si lo llama un fantasma mueve a todos los otros fantasmas a la vez buscandolos en la matriz
                for (int i = 0; i < tablero.length; i++) {
                    for (int j = 0; j < tablero[i].length; j++) {
                        if (tablero[i][j].equals("F")) {
                            moverFantasma(i, j);
                        }
                    }
                }
            } else {
                //Mueve al pacman
                moverPacman();
            }

            //Actualizo el tiempo en el tablero
            tablero[4][9] = String.valueOf(tiempoRestante);

            //Actualiza la pantalla
            mostrarTablero();

            semaforoTablero.release();

        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    private void moverPacman() {
        //Obtengo la posicion del pacman y creo una nueva variable para la nueva posicion
        int posX = pacman.getPosX();
        int posY = pacman.getPosY();
        int nuevaPosX = posX;
        int nuevaPosY = posY;

        switch (direccionPacman) {
            case "ARRIBA":
                nuevaPosY--;
                break;
            case "DERECHA":
                nuevaPosX++;
                break;
            case "ABAJO":
                nuevaPosY++;
                break;
            case "IZQUIERDA":
                nuevaPosX--;
                break;
        }
        System.out.println(nuevaPosX);
        //Compruebo si el nuevo movimiento es valido
        if (esMovimientoValido(nuevaPosY, nuevaPosX, "pacman")) {
            //Compruebo si recoge un objetivo (si es asi suma un objetivo a la variable)
            if (tablero[nuevaPosY][nuevaPosX].equals("O")) {
                objetivosRecogidos++;
            }
            //Limpio la casilla anterior y mueve al pacman
            tablero[posY][posX] = " ";
            tablero[nuevaPosY][nuevaPosX] = "P";
            pacman.setPosX(nuevaPosX);
            pacman.setPosY(nuevaPosY);
        } else if (nuevaPosX > 18) {//En el caso que pase por el camino "ocuto" de la derecha
            System.out.println("entro");
            nuevaPosY = 7;
            nuevaPosX = 0;
            tablero[posY][posX] = " ";
            tablero[nuevaPosY][nuevaPosX] = "P";
            pacman.setPosX(nuevaPosX);
            pacman.setPosY(nuevaPosY);
        } else if (nuevaPosX < 0) {//En el caso que pase por el camino "ocuto" de la izquierda
            nuevaPosY = 7;
            nuevaPosX = 18;
            tablero[posY][posX] = " ";
            tablero[nuevaPosY][nuevaPosX] = "P";
            pacman.setPosX(nuevaPosX);
            pacman.setPosY(nuevaPosY);
        }
    }

    private void moverFantasma(int posY, int posX) {
        //Guardo los movimientos posibles en un arraylist y las direcciones en una matriz provisional
        ArrayList<int[]> movimientosPosibles = new ArrayList<>();
        int[][] direcciones = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

        //Guarda todos los movimientos
        for (int[] dir : direcciones) {
            int nuevaX = posX + dir[0];
            int nuevaY = posY + dir[1];

            // Verifica todas las posiciones y guarda las que son posibles
            if (esMovimientoValido(nuevaY, nuevaX, "fantasma")) {
                movimientosPosibles.add(new int[]{nuevaX, nuevaY});
            }
        }

        //Si se puede realizar al menos un movimiento, se elije uno random del array
        if (!movimientosPosibles.isEmpty()) {
            int[] nuevaPos = movimientosPosibles.get((int) (Math.random() * movimientosPosibles.size()));

            if (!tablero[nuevaPos[1]][nuevaPos[0]].equals("O")) {
                //No moverse si hay un objetivo
                // Actualiza el tablero: mueve el fantasma y deja el espacio como estaba
                tablero[posY][posX] = ".";
                tablero[nuevaPos[1]][nuevaPos[0]] = "F";
            }
        }
    }

    private boolean esMovimientoValido(int y, int x, String personaje) {
        boolean esValido = false;
        if ((x >= 0) && (x < tablero[0].length) && (y >= 0) && (x < tablero[0].length) && (y < tablero.length)) {
            //Si el personaje es un fantasma y toca al pacman o viceversa, activa el metodo pacmanAtrapado
            if ((personaje.equals("pacman") && tablero[y][x].equals("F"))) {
                pacmanAtrapado();
            } //Devuelve si el movimiento es valido si:
            //Si la casilla a la que se mueve es un . o un "" o un O
            else if (((tablero[y][x].equals(".") || tablero[y][x].equals(" ") || tablero[y][x].equals("O")))) {
                esValido = true;
            }
        }
        return esValido;

    }

    public void disminuirTiempo() {
        if (tiempoRestante > 0) {
            tiempoRestante--;
        }
    }

    public String getDireccionPacman() {
        return direccionPacman;
    }

    public void setDireccionPacman(String direccionPacman) {
        this.direccionPacman = direccionPacman;
    }

    public int getObjetivosRecogidos() {
        return objetivosRecogidos;
    }

    public void setObjetivosRecogidos(int objetivosRecogidos) {
        this.objetivosRecogidos = objetivosRecogidos;
    }

    public void mostrarTablero() {
        System.out.println("\n\n\n\n\n\n\n\n Objetivos restantes:  " + (4 - objetivosRecogidos) + "\n");
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void rellenarTablero() {

        // Inicializa el tablero vacío con espacios
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = ".";
            }
        }

        // Muros externos
        // Verticales
        for (int i = 0; i < tablero.length; i++) {
            tablero[i][0] = "|";
            tablero[i][18] = "|";
        }

        // Horizontales
        for (int i = 0; i < tablero[0].length; i++) {
            tablero[0][i] = "-";
            tablero[14][i] = "-";
        }

        // Muros en medio del mapa
        // Horizontales
        for (int i = 0; i < tablero[0].length; i++) {
            if ((i > 1 && i < 4) || (i > 4 && i < 8) || (i > 10 && i < 14) || (i > 14 && i < 17)) {
                tablero[2][i] = "-";
            }
        }

        for (int i = 0; i < tablero[0].length; i++) {
            if ((i > 1 && i < 4) || (i > 6 && i < 12) || (i > 14 && i < 17)) {
                tablero[4][i] = "-";
            }
        }

        for (int i = 0; i < tablero[0].length; i++) {
            if ((i > 0 && i < 4) || (i > 6 && i < 9) || (i > 9 && i < 12) || (i > 14 && i < 18)) {
                tablero[6][i] = "-";
            }
        }

        for (int i = 0; i < tablero[0].length; i++) {
            if ((i > 0 && i < 4) || (i > 6 && i < 12) || (i > 14 && i < 18)) {
                tablero[8][i] = "-";
            }
        }

        for (int i = 0; i < tablero[0].length; i++) {
            if ((i > 1 && i < 4) || (i > 6 && i < 12) || (i > 14 && i < 17)) {
                tablero[10][i] = "-";
            }
        }

        for (int i = 0; i < tablero[0].length; i++) {
            if ((i > 1 && i < 5) || (i > 5 && i < 8) || (i > 10 && i < 13) || (i > 13 && i < 17)) {
                tablero[12][i] = "-";
            }
        }

        // Verticales
        for (int i = 0; i < tablero.length; i++) {
            if ((i > 2 && i < 6) || (i > 6 && i < 11)) {
                tablero[i][5] = "|";
                tablero[i][13] = "|";
            }
        }

        for (int i = 0; i < tablero.length; i++) {
            if ((i > 0 && i < 3) || (i > 12 && i < 15)) {
                tablero[i][9] = "|";
            }
        }

        for (int i = 0; i < tablero.length; i++) {
            if ((i > 6 && i < 8)) {
                tablero[i][7] = "|";
                tablero[i][11] = "|";
            }
        }

        //Especificos
        //Pacman
        tablero[9][9] = "P";

        //Objetivos
        tablero[2][1] = "O";
        tablero[2][17] = "O";
        tablero[12][1] = "O";
        tablero[12][17] = "O";

        //Fantasmas
        tablero[6][9] = "F";
        for (int i = 0; i < tablero.length; i++) {
            if (i > 7 && i < 11) {
                tablero[7][i] = "F";
            }
        }

        //Huecos
        tablero[7][0] = ".";
        tablero[7][18] = ".";

        //Muestro el tiempo restante
        tablero[4][9] = String.valueOf(tiempoRestante);

        //Muestro las vidas restantes
        tablero[10][9] = String.valueOf(vidasRestantes);

    }

    public boolean juegoTerminado() {
        return ((tiempoRestante == 0) || (objetivosRecogidos == 4) || (vidasRestantes == 0));
    }

    private void pacmanAtrapado() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\nPACMAN FUE ATRAPADO. RESETEANDO LAS POSICIONES...\n\n\n\n\n\n\n\n\n\n");

        //Pacman
        tablero[pacman.getPosY()][pacman.getPosX()] = " ";
        tablero[9][9] = "P";
        pacman.setPosX(9);
        pacman.setPosY(9);
        //Reseto la direccion inicial a "derecha"
        direccionPacman = "DERECHA";

        //Fantasmas
        //Busca los fantasmas y resetea su posicion
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j].equals("F")) {
                    tablero[i][j] = ".";
                }
            }
        }

        //Vuelvo a poner a los fantsmas en su posicion inicial
        tablero[6][9] = "F";
        for (int i = 0; i < tablero.length; i++) {
            if (i > 7 && i < 11) {
                tablero[7][i] = "F";
            }
        }

        //Actualizo las vidas restantes
        vidasRestantes--;
        tablero[10][9] = String.valueOf(vidasRestantes);

        //Si no perdio todas las vidas, vuelve a empezar
        if (vidasRestantes > 0) {
            try {
                Thread.sleep(2000);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n 3 \n\n\n\n\n\n\n\n\n\n");
                Thread.sleep(1000);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n 2 \n\n\n\n\n\n\n\n\n\n");
                Thread.sleep(1000);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n 1 \n\n\n\n\n\n\n\n\n\n");
                Thread.sleep(1000);
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n START \n\n\n\n\n\n\n\n\n\n");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
