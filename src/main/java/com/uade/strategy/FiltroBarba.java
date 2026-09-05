package com.uade.strategy;

import com.uade.model.Personaje;

public class FiltroBarba implements Filtro {
    private final boolean tieneBarba;

    public FiltroBarba(boolean tieneBarba) {
        this.tieneBarba = tieneBarba;
    }

    @Override
    public boolean aplicaA(Personaje personaje) {
        return personaje.isTieneBarba() == tieneBarba;
    }

    @Override
    public String getDescripcion() {
        return tieneBarba ? "¿Tiene barba?" : "¿No tiene barba?";
    }

    public boolean isTieneBarba() {
        return tieneBarba;
    }
}
