package com.empresa.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private int id;
    private int idEmpleado;
    private int idSala;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public Reserva(int id, int idEmpleado, int idSala, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        this.id = id;
        this.idEmpleado = idEmpleado;
        this.idSala = idSala;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }
}
