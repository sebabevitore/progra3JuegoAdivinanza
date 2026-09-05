package com.uade.strategy;

import com.uade.model.Personaje;

public class FiltroColorPelo implements Filtro {
    private final String colorPelo;

    public FiltroColorPelo(String colorPelo) {
        this.colorPelo = colorPelo;
    }

    @Override
    public boolean aplicaA(Personaje personaje) {
        return personaje.getColorPelo().equalsIgnoreCase(colorPelo);
    }

    @Override
    public String getDescripcion() {
        return "¿Tiene el pelo " + colorPelo + "?";
    }

    public String getColorPelo() {
        return colorPelo;
    }
}
