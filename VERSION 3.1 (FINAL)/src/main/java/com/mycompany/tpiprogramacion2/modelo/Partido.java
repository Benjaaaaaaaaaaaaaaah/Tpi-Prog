package com.mycompany.tpiprogramacion2.modelo;

import java.util.*;

public class Partido {

    private String resultado;
    private List<Equipo> equipos = new ArrayList<>();
    private String fecha;
    private String jornada;

    public Partido() {
    }

    public String getResultado() {
        return resultado;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }

    public String getFecha() {
        return fecha;
    }

    public String getJornada() {
        return jornada;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void setEquipos(List<Equipo> equipos) {
        this.equipos = equipos;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }

    public List<List<Equipo>> emparejamiento(Liga l) {
        List<List<Equipo>> emparejamientos = new ArrayList<>();
        List<Equipo> equipos = l.getEquipos();

        for (int i = 0; i < equipos.size(); i++) {

            for (int j = i + 1; j < equipos.size(); j++) {
                Equipo e1 = equipos.get(i);
                Equipo e2 = equipos.get(j);

                List<Equipo> partidoIda = new ArrayList<>();
                partidoIda.add(e1);
                partidoIda.add(e2);
                emparejamientos.add(partidoIda);

                List<Equipo> partidoVuelta = new ArrayList<>();
                partidoVuelta.add(e2);
                partidoVuelta.add(e1);
                emparejamientos.add(partidoVuelta);
            }
        }

        return emparejamientos;
    }

    public void asignarPuntos(Equipo e, int puntos) {
        e.sumarPuntos(puntos);
    }

    public void jugarPartidos(List<List<Equipo>> emparejamientos) {
        Random r = new Random();
        List<Equipo> emparejamiento = new ArrayList<>();

        for (int i = 0; i < emparejamientos.size(); i++) {
            emparejamiento = emparejamientos.get(i);

            if (emparejamiento.size() == 2) {
                List<Equipo> lista = new ArrayList<>(emparejamiento);
                Equipo e1 = lista.get(0);
                Equipo e2 = lista.get(1);
                int golesE1 = r.nextInt(6);
                int golesE2 = r.nextInt(6);

                System.out.println("Partido " + (i + 1) + ". " + e1.getNombre() + " " + golesE1 + " - " + golesE2 + " " + e2.getNombre());

                e1.sumarPartidos();
                e1.sumarGolesFavor(golesE1);
                e1.sumarGolesContra(golesE2);

                e2.sumarPartidos();
                e2.sumarGolesFavor(golesE2);
                e2.sumarGolesContra(golesE1);

                if (golesE1 > golesE2) {
                    System.out.println("Ganador " + e1.getNombre());
                    asignarPuntos(e1, 3);
                } else if (golesE1 < golesE2) {
                    System.out.println("Ganador " + e2.getNombre());
                    asignarPuntos(e2, 3);
                } else {
                    System.out.println("Empate");
                    asignarPuntos(e2, 1);
                    asignarPuntos(e1, 1);
                }
                System.out.println("-------------------------------------------------------------------------");
            }
        }
    }

}
