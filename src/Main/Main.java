package Main;

import Modelo.Fantasma;
import Modelo.PacMan;
import Modelo.PedirDireccion;
import Modelo.Tablero;
import java.util.Scanner;

/**
 *
 * @author Santiago Spina
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int eleccion = 0;
        int vidasRestantes = 3;
        int tiempoRestante = 600;

        System.out.println("Elija la dificultad: 1.Facil, 2.Normal, 3.Dificil\n");
        System.out.println("Facil:\n Tiempo: 5 minutos\n Vidas restantes: 5");
        System.out.println("Normal:\n Tiempo: 3 minutos\n Vidas restantes: 3");
        System.out.println("Dificil:\n Tiempo: 1 minutos\n Vidas restantes: 2\n");
        eleccion = sc.nextInt();
        
        switch (eleccion) {
            case 1:
                vidasRestantes = 5;
                tiempoRestante = 99;
                System.out.println("\nDificultad facil elegida\n");
                break;
            case 2:
                vidasRestantes = 3;
                tiempoRestante = 90;
                System.out.println("\nDificultad normal elegida\n");
                break;
            case 3:
                vidasRestantes = 2;
                tiempoRestante = 60;
                System.out.println("\nDificultad Dificil elegida\n");
                break;

        }
        System.out.println("");
        Tablero tablero = new Tablero(vidasRestantes, tiempoRestante);
        PacMan pacMan = new PacMan(tablero);
        Fantasma fantasma1 = new Fantasma(tablero, 9,6);
        Fantasma fantasma2 = new Fantasma(tablero,9,7);
        Fantasma fantasma3 = new Fantasma(tablero, 10,7);
        Fantasma fantasma4 = new Fantasma(tablero,8,7);
        PedirDireccion pedirDireccion = new PedirDireccion();
        
        pacMan.start();
        fantasma1.start();
        fantasma2.start();
        fantasma3.start();
        fantasma4.start();
        pedirDireccion.start();
        

    }

    

}
