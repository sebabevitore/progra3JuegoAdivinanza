package com.uade.controller;

import com.uade.model.Personaje;

import java.util.ArrayList;
import java.util.List;

public class Play {
    List<Personaje> personajes;

    public Play() {
        personajes = new ArrayList<>();
    }

    public void inicializador (){
        List<String> nombresFemeninos = List.of(
                "Ana", "Diana", "Elena", "Gabriela", "Ines",
                "Karina", "Maria", "Olivia", "Quinta", "Sofia",
                "Ursula", "Valeria"
        );

        List<String> nombresMasculinos = List.of(
                "Bruno", "Carlos", "Fernando", "Hugo", "Javier",
                "Luis", "Nicolas", "Pablo", "Raul", "Tomas",
                "Walter"
        );

        for (String nombre : nombresFemeninos) {
            personajes.add(new Personaje(nombre, false));
        }

        for (String nombre : nombresMasculinos) {
            personajes.add(new Personaje(nombre, true));
        }






    }

}
