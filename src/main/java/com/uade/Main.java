package com.uade;

import com.uade.controller.Play;
import com.uade.model.Personaje;

public class Main {
    public static void main(String[] args) {
        Play juego = new Play();

        // Ejecutamos la inicialización y el ordenamiento de personajes
        juego.inicializar();

        System.out.println("=== PERSONAJES (Ordenados con MergeSort - Mujeres primero) ===");
        System.out.println("Total: " + juego.getPersonajes().size() + "\n");
        for (Personaje p : juego.getPersonajes()) {
            System.out.println(p.toString());
        }
    }
}