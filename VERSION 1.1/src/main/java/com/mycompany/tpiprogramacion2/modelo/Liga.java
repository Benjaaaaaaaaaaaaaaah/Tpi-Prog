package com.mycompany.tpiprogramacion2.modelo;
import java.util.*;
import java.util.stream.Collectors;

public class Liga {
    private String nombre;
    private List<Equipo> equipos = new ArrayList<>();
    private String jornada;

    public Liga(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Equipo> getEquipos() {
        return equipos;
    }
    
    public void listaEquipos(){
        for(Equipo e : equipos){
            System.out.println(e.getNombre());
        }
    }

    public String getJornada() {
        return jornada;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEquipos(Equipo equipo) {
        equipos.add(equipo);
    }

    public void setJornada(String jornada) {
        this.jornada = jornada;
    }
    
    public List<Equipo> hacerRanking(){
        List<Equipo> ranking = equipos.stream()
                .sorted(Comparator.comparing(Equipo::getPuntos)
                        .thenComparing(Equipo::getGolesDiferencia)
                .reversed())
                .collect(Collectors.toList());
        
        return ranking;
    }
    
    public void imprimirTabla(){
        List<Equipo> ranking = hacerRanking();
        System.out.printf("%-3s %-15s %-7s %-7s %-7s %-7s %-7s%n", "#", "Equipo", "Partidos", "GF", "GC", "DG","Puntos");

    for (int i = 0; i < ranking.size(); i++) {
        Equipo e = ranking.get(i);
        System.out.printf("%-3d %-15s %-7d %-7d %-7d %-7d %-7d%n",
            i + 1, e.getNombre(), e.getPartidosJugados(), e.getGolesFavor(),
            e.getGolesContra(), e.getGolesDiferencia(), e.getPuntos());
    }
    }
    
    public void emparejamientoCruce(Liga l){
        List<Equipo> ranking = hacerRanking();
        List<Equipo> ranking2 = l.hacerRanking();
        
        Equipo e1r1 = ranking.get(0);
        Equipo e2r1 = ranking.get(1);
        Equipo e3r1 = ranking.get(2);
        Equipo e4r1 = ranking.get(3);
        Equipo e1r2 = ranking2.get(0);
        Equipo e2r2 = ranking2.get(1);
        Equipo e3r2 = ranking2.get(2);
        Equipo e4r2 = ranking2.get(3);
        
        System.out.println("\n-----------Cuartos de Final-------------");
        Equipo cuartos1 = jugar(e1r1,e4r2);
        System.out.println("Ganador "+cuartos1.getNombre());
        Equipo cuartos2 = jugar(e2r1,e3r2);
        System.out.println("Ganador "+cuartos2.getNombre());
        Equipo cuartos3 = jugar(e3r1,e2r2);
        System.out.println("Ganador "+cuartos3.getNombre());
        Equipo cuartos4 = jugar(e4r1,e1r2);
        System.out.println("Ganador "+cuartos4.getNombre());
        System.out.println("----------------------------------------");
        System.out.println("\n--------------Semis Finales--------------------");
        Equipo semi1 = jugar(cuartos1,cuartos2);
        System.out.println("Ganador "+semi1.getNombre());
        Equipo semi2 = jugar(cuartos3,cuartos4);
        System.out.println("Ganador "+semi2.getNombre());
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------Final-------------------");
        Equipo ganador = jugar(semi1,semi2);
        System.out.println("El ganador de los cruces es: "+ganador.getNombre());
        System.out.println("--------------------------------------------");
    }
    
    public Equipo jugar(Equipo e1,Equipo e2){
        Random r = new Random();
        int golese1 = r.nextInt(6);
        int golese2 = r.nextInt(6);
        System.out.println(e1.getNombre() + " "+golese1 + " - " +golese2+" "+e2.getNombre());
        if(golese1 > golese2){
            return e1;
        }else if(golese1 < golese2){
            return e2;
        }else{
            Equipo ganador = r.nextBoolean() ? e1:e2;
            System.out.println("Ganador de penales " + ganador.getNombre());
            return ganador;
        }
    }
}
