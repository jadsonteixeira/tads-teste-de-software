package com.testedesoftware.atividade.jacoco.junit;

public class Disciplina {

    private final String dia;
    private final double horaInicio;
    private final double horaFim;

    public Disciplina(String dia, double horaInicio, double horaFim) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public String getDia() {
        return dia;
    }

    public double getHoraInicio() {
        return horaInicio;
    }

    public double getHoraFim() {
        return horaFim;
    }
}
