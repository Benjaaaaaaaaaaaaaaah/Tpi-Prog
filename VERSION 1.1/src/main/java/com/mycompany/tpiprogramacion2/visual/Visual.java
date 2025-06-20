
package com.mycompany.tpiprogramacion2.visual;
import java.util.*;

public class Visual {
    private Scanner sc = new Scanner(System.in);
    
    public int menu(){
        int opcion;
        System.out.println("\n------------------Liga----------------------");
        System.out.println("1. Agregar Liga.");
        System.out.println("2. Agregar Equipo.");
        System.out.println("3. Lista equipos por Liga.");
        System.out.println("4. Tabla.");
        System.out.println("5. Ver jornada.");
        System.out.println("6. Jugar jornadas.");
        System.out.println("7. Jugar Cruce.");
        System.out.println("8. Cargar lo Guardado.");
        System.out.println("0. Salir.");
        System.out.println("------------------------------------------------");
        opcion = Integer.parseInt(sc.nextLine());
        return opcion;
    }
    
    public String agregarEquipo(){
        System.out.println("Nombre del Equipo.");
        return sc.nextLine();
    }
    
    public String agregarLiga(){
        System.out.println("Nombre de la Liga(Max de Ligas es 2).");
        return sc.nextLine();
    }
    
    public void imprimir(String m){
        System.out.println(m);
    }
    public String pedir(){
        return sc.nextLine();
    }
}
