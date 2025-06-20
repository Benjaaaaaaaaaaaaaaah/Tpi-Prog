
package com.mycompany.tpiprogramacion2.controlador;

import java.util.*;
import com.mycompany.tpiprogramacion2.modelo.Equipo;
import com.mycompany.tpiprogramacion2.modelo.Liga;
import com.mycompany.tpiprogramacion2.modelo.Partido;
import com.mycompany.tpiprogramacion2.visual.Visual;
import java.io.*;


public class Controlador {
    private List<Liga> ligas = new ArrayList<>();
    private Visual v = new Visual();
    private Partido p = new Partido();
    private List<List<Equipo>> emparejamientoLiga1 = new ArrayList<>();
    private List<List<Equipo>> emparejamientoLiga2 = new ArrayList<>();
    private boolean jornada = true;
    
    public void cargarPrueba(){
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\nicoo\\Documents\\NetBeansProjects\\TPIProgramacion2\\src\\main\\java\\com\\mycompany\\tpiprogramacion2\\archivo\\Ligas.txt"))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] partes = linea.split(",");
                ligas.add(new Liga(partes[0]));
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\nicoo\\Documents\\NetBeansProjects\\TPIProgramacion2\\src\\main\\java\\com\\mycompany\\tpiprogramacion2\\archivo\\Equipos.txt"))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] partes = linea.split(",");
                Equipo equipo = new Equipo(partes[0]);
                for(Liga l : ligas){
                    if(l.getNombre().equalsIgnoreCase(partes[1])){
                        l.setEquipos(equipo);
                    }
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Se cargaron con exito los datos de Prueba");
            
    }
    
    public void guardar(Equipo equipo, Liga liga){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\nicoo\\Documents\\NetBeansProjects\\TPIProgramacion2\\src\\main\\java\\com\\mycompany\\tpiprogramacion2\\archivo\\Guardado.txt",true))){
            bw.write(equipo.getNombre()+","+liga.getNombre());
            bw.newLine();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    public void cargarGuardado(){
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\nicoo\\Documents\\NetBeansProjects\\TPIProgramacion2\\src\\main\\java\\com\\mycompany\\tpiprogramacion2\\archivo\\Guardado.txt"))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] partes = linea.split(",");
                Equipo equipo = new Equipo(partes[0]);
                for(Liga l : ligas){
                    
                    if(!l.getNombre().equalsIgnoreCase(partes[1])){
                        ligas.add(new Liga(partes[1]));
                    }
                    if(l.getNombre().equalsIgnoreCase(partes[1])){
                        l.setEquipos(equipo);
                    }
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    
    
    public void agregarLiga(){
        if(ligas.size() == 2){
            v.imprimir("LLego al numero limite de ligas.");
            return;
        }
        Liga liga = new Liga(v.agregarLiga());
        ligas.add(liga);
    }
    
    public Liga recorrerLiga(){
        int opcion = 0;
        for(int i = 0; i<ligas.size();i++){
            Liga l = ligas.get(i);
            v.imprimir((i+1)+"Liga: "+l.getNombre());
        }
        opcion = Integer.parseInt(v.pedir());
        Liga liga = ligas.get(opcion-1);
        return liga;
    }
    
    public void agregarEquipo(){
        if(ligas.size() == 0){
            v.imprimir("No hay ligas a las cual ingresar a este Equipo.");
            return;
        }
        Liga liga = recorrerLiga();
        Equipo equipo = new Equipo(v.agregarEquipo());
        liga.setEquipos(equipo);
        
        guardar(equipo, liga);
    }
    
    public void equipoPorLiga(){
        Liga liga = recorrerLiga();
        liga.listaEquipos();
    }
    
    public void tabla(){
        v.imprimir("De que liga quiere la tabla?");
        Liga liga = recorrerLiga();
        liga.imprimirTabla();
    }
    
    public void verJornada(){
        Liga liga1 = ligas.get(0);
        Liga liga2 = ligas.get(1);
        this.emparejamientoLiga1 = p.emparejamiento(liga1);
        this.emparejamientoLiga2 = p.emparejamiento(liga2);
        
        
        int i = 0;
        v.imprimir("\n------------------"+liga1.getNombre()+"----------------");
        for(List<Equipo> l : emparejamientoLiga1){
            i++;
            v.imprimir("Jornada "+i+"- "+l.get(0).getNombre()+" - "+l.get(1).getNombre());
        }
        v.imprimir("----------------------------------------------------------");
        v.imprimir("\n------------------"+liga2.getNombre()+"----------------");
        i = 0;
        for(List<Equipo> l : emparejamientoLiga2){
            
            i++;
            v.imprimir("Jornada "+i+"- "+l.get(0).getNombre()+" - "+l.get(1).getNombre());
        }
        v.imprimir("-----------------------------------------------------------");
    }
    
    public void jugarJornada(){
        if(jornada){
        v.imprimir(ligas.get(0).getNombre()+": ");
            p.jugarPartidos(emparejamientoLiga1);
        v.imprimir(ligas.get(1).getNombre()+": ");
            p.jugarPartidos(emparejamientoLiga2);
        }
        this.jornada = false;
    }
    
    public void jugarCruces(){
        if(jornada){
            v.imprimir("Deben jugar las jornadas antes.");
            return;
        }
        ligas.get(0).emparejamientoCruce(ligas.get(1));
    }
    
    public void ejecutar(){
        int opcion;
        do{
            opcion = v.menu();
            switch (opcion) {
                case 1 -> agregarLiga();
                case 2 -> agregarEquipo();
                case 3 -> equipoPorLiga();
                case 4 -> tabla();
                case 5 -> verJornada();
                case 6 -> jugarJornada();
                case 7 -> jugarCruces();
                case 8 -> cargarGuardado();
                case 777 -> cargarPrueba();
            }
        }while(opcion !=0);
    }
    
}
