package com.PortalInmoviliaria.dto;

import com.PortalInmoviliaria.entities.Agente;
import com.PortalInmoviliaria.entities.Cliente;

import java.util.List;

public class ViviendaDto {

    private double precio;
    private String titulo;
    private String ubicacion;

    private Agente agente;
    private int habitaciones;
    private int servicios;
    private int garajes;
    private String descripcion;
    private List<Cliente> clientes;

    public ViviendaDto() {
    }

    public ViviendaDto(double precio, String titulo, String ubicacion, String propietario, Agente agente, int habitaciones, int servicios, int garajes, String descripcion, List<Cliente> clientes) {
        this.precio = precio;
        this.titulo = titulo;
        this.ubicacion = ubicacion;

        this.agente = agente;
        this.habitaciones = habitaciones;
        this.servicios = servicios;
        this.garajes = garajes;
        this.descripcion = descripcion;
        this.clientes = clientes;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }



    public Agente getAgente() {
        return agente;
    }

    public void setAgente(Agente agente) {
        this.agente = agente;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(int habitaciones) {
        this.habitaciones = habitaciones;
    }

    public int getServicios() {
        return servicios;
    }

    public void setServicios(int servicios) {
        this.servicios = servicios;
    }

    public int getGarajes() {
        return garajes;
    }

    public void setGarajes(int garajes) {
        this.garajes = garajes;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
