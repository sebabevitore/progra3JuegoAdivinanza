package com.uade.strategy;

import com.uade.model.Personaje;

public class FiltroGenero implements Filtro {
    private final boolean esHombre;

    public FiltroGenero(boolean esHombre) {
        this.esHombre = esHombre;
    }

    @Override
    public boolean aplicaA(Personaje personaje) {
        return personaje.isEsHombre() == esHombre;
    }

    @Override
    public String getDescripcion() {
        return "¿Es " + (esHombre ? "hombre" : "mujer") + "?";
    }
    
    public boolean isEsHombre() {
        return esHombre;
    }
    
}
