package com.PortalInmoviliaria.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "viviendas")
public class Vivienda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "precio")
    private double precio;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "ubicacion")
    private String ubicacion;


    @ManyToOne
    @JoinColumn(name = "agente")
    private Agente agente;
    @Column(name = "habitaciones")
    private int habitaciones;
    @Column(name = "servicios")
    private int servicios;
    @Column(name = "garajes")
    private int garajes;
    @Column(name = "descripcion")
    private String descripcion;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "viviendas_clientes",
    joinColumns = {@JoinColumn(name = "vivienda_id")},
    inverseJoinColumns = {@JoinColumn(name = "cliente_id")}
    )
    private List<Cliente> clientes;

    public Vivienda() {
    }

    public Vivienda(double precio, String titulo, String ubicacion, Agente agente, int habitaciones, int servicios, int garajes, String descripcion, List<Cliente> clientes) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
