package Main;

import Modelo.Contador;
import Modelo.Fantasmas;
import Modelo.Fantasmas;
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
        PacMan pacman = new PacMan(null); // Lo inicializo null para poder pasarselo al tablero primero
        Tablero tablero = new Tablero(vidasRestantes, tiempoRestante, pacman);
        pacman = new PacMan(tablero);
        
        Fantasmas fantasmas = new Fantasmas(tablero);
        
        PedirDireccion pedirDireccion = new PedirDireccion(tablero);
        Contador contador = new Contador(tablero); 
                
        //Comienzo de los hilos
        pacman.start();
        
        fantasmas.start();
        
        pedirDireccion.start();
        contador.start();
        
    }
}
