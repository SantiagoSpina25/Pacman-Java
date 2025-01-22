package Modelo;

import java.util.Scanner;

/**
 *
 * @author Santaigo Spina
 */
public class PedirDireccion extends Thread{
    
    Scanner sc = new Scanner(System.in);
    private static String direccion="d";
    
    @Override
    public void run() {
        while(true){
            //Pide la direccion
            String direccion = sc.next();
            if(!isCorrecta(direccion)){
                 direccion = "d";
            }
        }
    }
    
    public boolean isCorrecta(String direccion){
        return (direccion.equalsIgnoreCase("w") || direccion.equalsIgnoreCase("a") || direccion.equalsIgnoreCase("s") || direccion.equalsIgnoreCase("d"));
    }

    public static String getDireccion() {
        return direccion;
    }
    
}
