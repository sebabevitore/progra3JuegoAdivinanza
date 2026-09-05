package com.uade;

import com.uade.controller.Play;
import com.uade.model.Personaje;

public class Main {
    public static void main(String[] args) {
        Play juego = new Play();

        // Ejecutamos la creación aleatoria de personajes
        juego.inicializador();

        // Comprobación por consola
        System.out.println("Total de personajes generados: " + juego.getPersonajes().size() + "\n");

        for (Personaje p : juego.getPersonajes()) {
            System.out.println(p.toString());
        }
    }
}