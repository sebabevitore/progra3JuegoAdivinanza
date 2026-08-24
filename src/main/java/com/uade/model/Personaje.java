package com.uade.model;

public class Personaje {
    private int id;
    private String nombre;
    private boolean esHombre;
    private String colorPelo; // colorado, negro, amarillo
    private boolean usaLentes;
    private boolean tieneBarba;
    private boolean esPelado;
    private boolean tieneGorro;

    public Personaje(int id, String nombre, boolean esHombre, String colorPelo, boolean usaLentes, boolean tieneBarba, boolean esPelado, boolean tieneGorro) {
        this.id = id;
        this.nombre = nombre;
        this.esHombre = esHombre;
        this.colorPelo = colorPelo;
        this.usaLentes = usaLentes;
        this.tieneBarba = tieneBarba;
        this.esPelado = esPelado;
        this.tieneGorro = tieneGorro;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public boolean isEsHombre() { return esHombre; }
    public String getColorPelo() { return colorPelo; }
    public boolean isUsaLentes() { return usaLentes; }
    public boolean isTieneBarba() { return tieneBarba; }
    public boolean isEsPelado() { return esPelado; }
    public boolean isTieneGorro() { return tieneGorro; }

    @Override
    public String toString() {
        String genero = esHombre ? "Hombre" : "Mujer";
        return String.format("ID: %d | Nombre: %s (%s) | Pelo: %s | Lentes: %b | Barba: %b | Pelado: %b | Gorro: %b",
                id, nombre, genero, colorPelo, usaLentes, tieneBarba, esPelado, tieneGorro);
    }
}