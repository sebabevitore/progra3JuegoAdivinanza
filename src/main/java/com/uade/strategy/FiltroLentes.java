package com.uade.strategy;

import com.uade.model.Personaje;

public class FiltroLentes implements Filtro {
    private final boolean usaLentes;

    public FiltroLentes(boolean usaLentes) {
        this.usaLentes = usaLentes;
    }

    @Override
    public boolean aplicaA(Personaje personaje) {
        return personaje.isUsaLentes() == usaLentes;
    }

    @Override
    public String getDescripcion() {
        return usaLentes ? "¿Usa lentes?" : "¿No usa lentes?";
    }

    public boolean isUsaLentes() {
        return usaLentes;
    }
}
