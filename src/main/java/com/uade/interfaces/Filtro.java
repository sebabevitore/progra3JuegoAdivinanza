package com.uade.interfaces;

import com.uade.model.Personaje;

public interface Filtro {
	boolean aplicaA(Personaje personaje);
	
	String getDescripcion();
}
