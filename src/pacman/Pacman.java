package pacman;

/**
 *
 * @author Santiago Spina
 */
public class Pacman {

    public static void main(String[] args) {

        //Tablero de 15 de alto y 19 de ancho
        String tablero[][] = new String[15][19];

        rellenarTablero(tablero);
        mostrarTablero(tablero);

    
    
    
    }

    
    
    
    
    
    private static void mostrarTablero(String[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void rellenarTablero(String[][] tablero) {

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
            if ((i > 0 && i < 4) || (i > 6 && i < 9) || (i > 9 && i < 12)  || (i > 14 && i < 18)) {
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
            if(i>7 && i<11)
            tablero[7][i] = "F";
        }
            
    }
}
