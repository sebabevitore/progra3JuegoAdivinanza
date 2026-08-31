package com.uade.model;

import com.uade.interfaces.Filtro;

public class FiltroGenero implements Filtro{
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
		return "Es " + (esHombre ? "hombre" : "mujer") + "?";
	}

}
