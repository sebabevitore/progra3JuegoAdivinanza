package com.uade.controller;

import com.uade.model.Personaje;
import java.util.ArrayList;
import java.util.List;

public class Play {
    private List<Personaje> personajes;
    private MergeSort mergeSort;

    public Play() {
        this.personajes = new ArrayList<>();
        this.mergeSort = new MergeSort();
        inicializar();
    }

    public void inicializar() {
        CreadorPersonajes creador = new CreadorPersonajes();
        creador.inicializador();

        // Inicializamos una única lista de personajes para ambos jugadores
        this.personajes = new ArrayList<>(creador.getPersonajes());

        // Primer ordenamiento con MergeSort (mujeres primero)
        if (!personajes.isEmpty()) {
            mergeSort.mergeSort(personajes, 0, personajes.size() - 1);
        }
    }

    public void inicializador() {
        inicializar();
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }
}
