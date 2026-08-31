package com.uade.model;

import com.uade.interfaces.Filtro;

public class FiltroLentes implements Filtro{
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
		return "¿" + (usaLentes ? "Usa" : "No usa") + " lentes?";
	}

}
