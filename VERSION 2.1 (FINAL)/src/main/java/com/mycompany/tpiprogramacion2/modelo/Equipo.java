package com.mycompany.tpiprogramacion2.modelo;

public class Equipo {

    private String nombre;
    private int puntos;
    private int golesFavor;
    private int golesContra;
    private int partidosJugados;
    private int posicionEnTabla;
    private int golesDiferencia;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.puntos = 0;
        this.golesContra = 0;
        this.golesFavor = 0;
        this.partidosJugados = 0;
    }

    public int getGolesDiferencia() {
        return golesDiferencia;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntos() {
        return puntos;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public int getPartidosJugados() {
        return partidosJugados;
    }

    public int getPosicionEnTabla() {
        return posicionEnTabla;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPosicionEnTabla(int posicionEnTabla) {
        this.posicionEnTabla = posicionEnTabla;
    }

    public int golesDiferencia() {
        int g = golesFavor - golesContra;
        return g;
    }

    public void sumarGolesFavor(int goles) {
        this.golesFavor = golesFavor + goles;
    }

    public void sumarGolesContra(int goles) {
        this.golesContra = golesContra + goles;
    }

    public void sumarPuntos(int punto) {
        this.puntos = puntos + punto;
    }

    public void sumarPartidos() {
        this.partidosJugados++;
    }

}
