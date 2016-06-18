package com.example.luis.aplicativo1;

/**
 * Created by luis on 16/06/16.
 */
public class Aula {
    String horas;
    String minutos;
    String disciplina;
    String professor;
    String sala;
    String dia;

    public Aula(String horas, String minutos, String disciplina, String professor, String sala, String dia){
        this.horas = horas;
        this.minutos = minutos;
        this.disciplina = disciplina;
        this.professor = professor;
        this.sala = sala;
        this.dia = dia;

    }

    public String getHoras() {
        return horas;
    }

    public String getMinutos() {
        return minutos;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public String getProfessor() {
        return professor;
    }

    public String getSala() {
        return sala;
    }

    public String getDia() {
        return dia;
    }
}
