package com.uade.model;

import com.uade.strategy.Filtro;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String nombre;
    private Personaje personajeSecreto;
    private List<Personaje> candidatosRestantes;

    public Player(String nombre, List<Personaje> personajesIniciales) {
        this.nombre = nombre;
        this.candidatosRestantes = new ArrayList<>(personajesIniciales);
    }

    public void setPersonajeSecreto(Personaje personaje) {
        this.personajeSecreto = personaje;
    }

    /**
     * NOTA: Usarlo únicamente para fines de setup/debug.
     * En la partida la máquina no debe acceder directamente al personaje secreto del rival ni viceversa.
     */
    public Personaje getPersonajeSecreto() {
        return personajeSecreto;
    }

    public boolean responderFiltro(Filtro filtro) {
        if (this.personajeSecreto == null) {
            throw new IllegalStateException("El personaje secreto no ha sido asignado.");
        }
        return filtro.aplicaA(this.personajeSecreto);
    }

    public boolean esCorrecto(Personaje candidato) {
        if (this.personajeSecreto == null || candidato == null) {
            return false;
        }
        return this.personajeSecreto.getId() == candidato.getId();
    }

    public int descartarCandidatos(Filtro filtro, boolean coincide) {
        int cantidadAntes = this.candidatosRestantes.size();
        this.candidatosRestantes.removeIf(p -> filtro.aplicaA(p) != coincide);
        return cantidadAntes - this.candidatosRestantes.size();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Personaje> getCandidatosRestantes() {
        return candidatosRestantes;
    }
}
