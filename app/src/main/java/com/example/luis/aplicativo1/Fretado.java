package com.example.luis.aplicativo1;

/**
 * Created by luis on 10/07/16.
 */
public class Fretado {
    String linha;
    String horarioPartida;
    String localPartida;
    String horarioChegada;
    String localChegada;

    public Fretado(String linha, String horarioPartida, String localPartida, String horarioChegada, String localChegada){
        this.linha = linha;
        this.horarioPartida = horarioPartida;
        this.localPartida = localPartida;
        this.horarioChegada = horarioChegada;
        this.localChegada = localChegada;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getHorarioPartida() {
        return horarioPartida;
    }

    public void setHorarioPartida(String horarioPartida) {
        this.horarioPartida = horarioPartida;
    }

    public String getLocalPartida() {
        return localPartida;
    }

    public void setLocalPartida(String localPartida) {
        this.localPartida = localPartida;
    }

    public String getHorarioChegada() {
        return horarioChegada;
    }

    public void setHorarioChegada(String horarioChegada) {
        this.horarioChegada = horarioChegada;
    }

    public String getLocalChegada() {
        return localChegada;
    }

    public void setLocalChegada(String localChegada) {
        this.localChegada = localChegada;
    }
}
