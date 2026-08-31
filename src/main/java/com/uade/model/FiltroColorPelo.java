package com.uade.model;

import com.uade.interfaces.Filtro;

public class FiltroColorPelo implements Filtro{
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
		return "Tiene el pelo " + colorPelo + "?";
	}

}
