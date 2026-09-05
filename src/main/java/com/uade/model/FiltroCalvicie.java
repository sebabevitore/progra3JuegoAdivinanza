package com.uade.model;

import com.uade.interfaces.Filtro;

public class FiltroCalvicie implements Filtro{
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
		return "¿Es " + (esPelado ? "pelado" : "no pelado") + "?";
	}

}
