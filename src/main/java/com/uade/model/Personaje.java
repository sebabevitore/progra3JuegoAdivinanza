package com.uade.model;

public class Personaje {
    private int id;
    private String nombre;
    private Color camiseta;
    private Color pelo;
    private boolean esHombre;
    private boolean usaLentes;
    private boolean tieneBarba;
    private boolean esPelado;
    private boolean tieneGorro;

    public Personaje(String nombre, boolean esHombre) {
        this.nombre = nombre;
        this.esHombre = esHombre;
    }
}
