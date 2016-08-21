package com.example.luis.aplicativo1;

/**
 * Created by luis on 20/08/16.
 */
public class Prato {
    String tipo;
    String nomeDoPrato;

    public Prato(String tipo, String nomeDoPrato) {
        this.tipo = tipo;
        this.nomeDoPrato = nomeDoPrato;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomeDoPrato() {
        return nomeDoPrato;
    }

    public void setNomeDoPrato(String nomeDoPrato) {
        this.nomeDoPrato = nomeDoPrato;
    }
}
