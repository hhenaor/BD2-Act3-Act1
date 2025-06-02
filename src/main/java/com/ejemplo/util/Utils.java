package com.ejemplo.util;

import java.util.Scanner;

/**
 * para operaciones comunes
 */
public class Utils {
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * leer string
     */
    public static String leerString(String mensaje) {
        System.out.print(mensaje + ": ");
        return scanner.nextLine();
    }
    
    /**
     * leer texto
     */
    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
    
    /**
     * leer un int
     */
    public static int leerInt(String mensaje) {
        System.out.print(mensaje + ": ");
        int valor = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de linea
        return valor;
    }
    
    /**
     * pause hasta que el usuario presione Enter
     */
    public static void pausar() {
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }
    
    /**
     * un mensaje con formato
     */
    public static void mostrarMensaje(String mensaje) {
        System.out.println(">>> " + mensaje);
    }
}