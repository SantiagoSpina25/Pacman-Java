package Modelo;

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

    private String direccionPacman = "DERECHA";

    private final Semaphore semaforoTablero = new Semaphore(1);

    public Tablero(int vidasRestantes, int tiempoRestante) {
        this.vidasRestantes = vidasRestantes;
        this.tiempoRestante = tiempoRestante;

        rellenarTablero();
    }

    public int[] mover(String personaje, int posX, int posY) {
        int[] posiciones = {posY, posX};
        try {
            semaforoTablero.acquire();
            if (personaje.equals("F")) {
                int numRandom = (int) (Math.random() * 4);

                switch (numRandom) {//Compruebo si se puede mover
                    case 0://Arriba
                        if (tablero[posY - 1][posX].equals(".")) {
                            tablero[posY - 1][posX] = personaje;
                            tablero[posY][posX] = ".";
                            posY--;
                        }
                        break;
                    case 1://Derecha
                        if (tablero[posY][posX + 1].equals(".")) {
                            tablero[posY][posX + 1] = personaje;
                            tablero[posY][posX] = ".";
                            posX++;
                        }
                        break;
                    case 2://Abajo
                        if (tablero[posY + 1][posX].equals(".")) {
                            tablero[posY + 1][posX] = personaje;
                            tablero[posY][posX] = ".";
                            posY++;
                        }
                        break;
                    case 3://Izquierda
                        if (tablero[posY][posX - 1].equals(".")) {
                            tablero[posY][posX - 1] = personaje;
                            tablero[posY][posX] = ".";
                            posX--;
                        }
                        break;

                }
            } else {// Si es PACMAN
                switch (direccionPacman) {//Compruebo si se puede mover
                    case "ARRIBA"://Arriba
                        if (tablero[posY - 1][posX].equals(".")) {
                            tablero[posY - 1][posX] = personaje;
                            tablero[posY][posX] = ".";
                            posY--;
                        }
                        break;
                    case "DERECHA"://Derecha
                        if (tablero[posY][posX + 1].equals(".")) {
                            tablero[posY][posX + 1] = personaje;
                            tablero[posY][posX] = ".";
                            posX++;
                        }
                        break;
                    case "ABAJO"://Abajo
                        if (tablero[posY + 1][posX].equals(".")) {
                            tablero[posY + 1][posX] = personaje;
                            tablero[posY][posX] = ".";
                            posY++;
                        }
                        break;
                    case "IZQUIERDA"://Izquierda
                        if (tablero[posY][posX - 1].equals(".")) {
                            tablero[posY][posX - 1] = personaje;
                            tablero[posY][posX] = ".";
                            posX--;
                        }
                        break;
                }
            }
            //Guarda las posiciones cambiadas en un array
            posiciones[0] = posY;
            posiciones[1] = posX;
            
            //Actualizo el tiempo en el tablero
            tablero[4][9] = String.valueOf(tiempoRestante);
            
            //Muestra el tablero actualizado y libera el tablero
            mostrarTablero();
            semaforoTablero.release();
        } catch (Exception e) {
            System.out.println(e);
        }
        return posiciones;
    }
    
    public void disminuirTiempo(){
        if(tiempoRestante > 0){
            tiempoRestante--;
        }
    }

    public String getDireccionPacman() {
        return direccionPacman;
    }

    public void setDireccionPacman(String direccionPacman) {
        this.direccionPacman = direccionPacman;
    }

    public void mostrarTablero() {
        System.out.println("");
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

        //Muestro el tiempo restante
        tablero[4][9] = String.valueOf(tiempoRestante);

        //Muestro las vidas restantes
        tablero[10][9] = String.valueOf(vidasRestantes);

    }

}
