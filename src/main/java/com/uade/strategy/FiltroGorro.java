package com.uade.strategy;

import com.uade.model.Personaje;

public class FiltroGorro implements Filtro {
    private final boolean tieneGorro;

    public FiltroGorro(boolean tieneGorro) {
        this.tieneGorro = tieneGorro;
    }

    @Override
    public boolean aplicaA(Personaje personaje) {
        return personaje.isTieneGorro() == tieneGorro;
    }

    @Override
    public String getDescripcion() {
        return tieneGorro ? "¿Tiene gorro?" : "¿No tiene gorro?";
    }

    public boolean isTieneGorro() {
        return tieneGorro;
    }
}
