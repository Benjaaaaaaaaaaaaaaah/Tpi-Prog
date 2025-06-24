package com.mycompany.tpiprogramacion2.controlador;

import java.util.*;
import com.mycompany.tpiprogramacion2.modelo.Equipo;
import com.mycompany.tpiprogramacion2.modelo.Liga;
import com.mycompany.tpiprogramacion2.modelo.Partido;
import com.mycompany.tpiprogramacion2.visual.Visual;
import java.io.*;

public class Controlador {

    private List<Liga> listaLigas = new ArrayList<>();
    private Visual visual = new Visual();
    private Partido partido = new Partido();
    private List<List<Equipo>> emparejamientoLiga1 = new ArrayList<>();
    private List<List<Equipo>> emparejamientoLiga2 = new ArrayList<>();
    private boolean estadoJornada = true;

    public void cargarPrueba() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\nicoo\\Documents\\NetBeansProjects\\TPIProgramacion2\\src\\main\\java\\com\\mycompany\\tpiprogramacion2\\archivo\\Ligas.txt"))) {
            String linea;
            // saca la informacion del archivo y crea una liga
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                listaLigas.add(new Liga(partes[0]));
            }

        } catch (IOException e) {
            visual.imprimir(e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\nicoo\\Documents\\NetBeansProjects\\TPIProgramacion2\\src\\main\\java\\com\\mycompany\\tpiprogramacion2\\archivo\\Equipos.txt"))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                // saca la informacion del archivo y crea el equipo
                String[] partes = linea.split(",");
                Equipo equipo = new Equipo(partes[0]);
                // añade a una liga el equipo
                for (Liga l : listaLigas) {
                    if (l.getNombre().equalsIgnoreCase(partes[1])) {
                        l.setEquipos(equipo);
                    }

                }
            }
        } catch (IOException e) {
            visual.imprimir(e.getMessage());
        }
        visual.imprimir("Se cargaron con exito los datos de Prueba");

    }

    public void guardar(Equipo equipo, Liga liga) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\nicoo\\Documents\\NetBeansProjects\\TPIProgramacion2\\src\\main\\java\\com\\mycompany\\tpiprogramacion2\\archivo\\Guardado.txt", true))) {
            // escribe los datos ingresados en un archivo guardado
            bw.write(equipo.getNombre() + "," + liga.getNombre());
            bw.newLine();

        } catch (IOException e) {
            visual.imprimir(e.getMessage());
        }
    }

    public void cargarGuardado() {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\nicoo\\Documents\\NetBeansProjects\\TPIProgramacion2\\src\\main\\java\\com\\mycompany\\tpiprogramacion2\\archivo\\Guardado.txt"))) {

            String linea;
            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split(",");
                Equipo equipo = new Equipo(partes[0]);

                Liga ligaEncontrada = null;

                //recorre las lista y si encuentra una la pone en una variable
                for (Liga l : listaLigas) {
                    if (l.getNombre().equalsIgnoreCase(partes[1])) {
                        ligaEncontrada = l;
                        break;
                    }
                }

                //si no encuentra ligas crea una nueva liga
                if (ligaEncontrada == null && listaLigas.size() != 2) {
                    ligaEncontrada = new Liga(partes[1]);
                    listaLigas.add(ligaEncontrada);
                } else if (listaLigas.size() == 2) {
                    visual.imprimir("La lista llego a su limite y no agrego una liga");
                }
                //ingresa el equipo dentro de la liga creada o encontrada
                ligaEncontrada.setEquipos(equipo);
            }
            visual.imprimir("Datos anteriores cargados");
        } catch (IOException e) {
            visual.imprimir(e.getMessage());
        }
    }

    public void agregarLiga() {
        // pone limite de ligas que se puedan crear
        if (listaLigas.size() == 2) {
            visual.imprimir("LLego al numero limite de ligas.");
            return;
        }
        // crea una liga con informacion ingresada y la añade a una lista
        Liga liga = new Liga(visual.agregarLiga());
        listaLigas.add(liga);
    }

    public Liga recorrerLiga() {

        int opcion = 0;
        // recorre la lista de ligas para sacar una liga de ahi
        for (int i = 0; i < listaLigas.size(); i++) {
            Liga l = listaLigas.get(i);
            visual.imprimir((i + 1) + "Liga: " + l.getNombre());
        }

        opcion = Integer.parseInt(visual.pedir());
        Liga liga = listaLigas.get(opcion - 1);

        return liga;
    }

    public void agregarEquipo() {
        // cuando no hay liga no te permite agregar equipo
        if (listaLigas.size() == 0) {
            visual.imprimir("No hay ligas a las cual ingresar a este Equipo.");
            return;
        }
        // pide una liga y un nombre para crear el equipo
        Liga liga = recorrerLiga();
        Equipo equipo = new Equipo(visual.agregarEquipo());

        liga.setEquipos(equipo);

        guardar(equipo, liga);
    }

    public void equipoPorLiga() {
        // te pide una liga para mostrarte todos los equipos que hay en esta
        Liga liga = recorrerLiga();
        liga.listaEquipos();
    }

    public void tabla() {
        // te hace una tabla con todos los datos de los equipos por liga
        visual.imprimir("De que liga quiere la tabla?");
        Liga liga = recorrerLiga();

        liga.imprimirTabla();
    }

    public void verJornada() {
        // toma las ligas y crea los emparejamientos
        Liga liga1 = listaLigas.get(0);
        Liga liga2 = listaLigas.get(1);
        this.emparejamientoLiga1 = partido.emparejamiento(liga1);
        this.emparejamientoLiga2 = partido.emparejamiento(liga2);

        // muestra los emparejamientos
        int i = 0;
        visual.imprimir("\n------------------" + liga1.getNombre() + "----------------");
        for (List<Equipo> l : emparejamientoLiga1) {
            i++;
            visual.imprimir("Partido " + i + "- " + l.get(0).getNombre() + " - " + l.get(1).getNombre());
        }
        visual.imprimir("----------------------------------------------------------");

        visual.imprimir("\n------------------" + liga2.getNombre() + "----------------");
        i = 0;
        for (List<Equipo> l : emparejamientoLiga2) {

            i++;
            visual.imprimir("Partido " + i + "- " + l.get(0).getNombre() + " - " + l.get(1).getNombre());
        }
        visual.imprimir("-----------------------------------------------------------");
    }

    public void jugarJornada() {
        // juega si antes no se jugo
        if (estadoJornada) {
            visual.imprimir(listaLigas.get(0).getNombre() + ": ");
            partido.jugarPartidos(emparejamientoLiga1);

            visual.imprimir(listaLigas.get(1).getNombre() + ": ");
            partido.jugarPartidos(emparejamientoLiga2);
        }
        // una vez se ejecuta cambia el estado para no volver a ejecutar
        this.estadoJornada = false;
    }

    public void jugarCruces() {
        // se ejecuta si se ejecuto la jornada
        if (estadoJornada) {
            visual.imprimir("Deben jugar las jornadas antes.");
            return;
        }
        // empareja y juega los cruces
        listaLigas.get(0).emparejamientoCruce(listaLigas.get(1));
    }

    public void ejecutar() {
        int opcion;
        do {
            opcion = visual.menu();
            switch (opcion) {
                case 1 ->
                    agregarLiga();
                case 2 ->
                    agregarEquipo();
                case 3 ->
                    equipoPorLiga();
                case 4 ->
                    tabla();
                case 5 ->
                    verJornada();
                case 6 ->
                    jugarJornada();
                case 7 ->
                    jugarCruces();
                case 8 ->
                    cargarGuardado();
                case 777 ->
                    cargarPrueba();
            }
        } while (opcion != 0);
    }

}
