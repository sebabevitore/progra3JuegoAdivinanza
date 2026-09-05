package com.uade.strategy;

import com.uade.model.Personaje;

public class FiltroCalvicie implements Filtro {
    private final boolean esPelado;

    public FiltroCalvicie(boolean esPelado) {
        this.esPelado = esPelado;
    }

    @Override
    public boolean aplicaA(Personaje personaje) {
        return personaje.isEsPelado() == esPelado;
    }

    @Override
    public String getDescripcion() {
        return esPelado ? "¿Es pelado?" : "¿Tiene pelo?";
    }

    public boolean isEsPelado() {
        return esPelado;
    }
}
