package com.uade.model;

import com.uade.interfaces.Filtro;

public class Player {
    private String nombre;
    private boolean turn;
    private Personaje personajeSelect;

    public Player(String nombre, boolean turn, Personaje personajeSelect) {
    	this.nombre = nombre;
        this.turn = turn;
        this.personajeSelect = personajeSelect;
    }

    public boolean responderFiltro(Filtro filtro) {
    	return filtro.aplicaA(this.personajeSelect);
    }
    
    public boolean esCorrecto(Personaje candidato) {
    	return this.personajeSelect.getId() == candidato.getId();
    }
    
    public boolean isTurn() {
        return turn;
    }

    public void activarTurno() {
        this.turn = true;
    }

    public void finalizarTurno() {
        this.turn = false;
    }
    
    
	public String getNombre() {
		return nombre;
	}
    
    
    
}
