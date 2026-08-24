package com.uade.controller;

import com.uade.model.Personaje;

import java.util.ArrayList;
import java.util.List;

public class Play {
    List<Personaje> personajes;

    public Play() {
        personajes = new ArrayList<>();
    }

    public void inicializador() {
        List<String> nombresFemeninos = List.of(
                "Ana", "Beatriz", "Clara", "Diana", "Elena", "Florencia", "Gabriela",
                "Hilda", "Inés", "Julia", "Karina", "Laura", "María", "Natalia",
                "Olivia", "Paula", "Quintina", "Rosa", "Sofía", "Teresa",
                "Úrsula", "Valeria", "Ximena"
        );

        List<String> nombresMasculinos = List.of(
                "Andrés", "Bruno", "Carlos", "Diego", "Esteban", "Federico", "Gabriel",
                "Hugo", "Ignacio", "Javier", "Kevin", "Lucas", "Martín", "Nicolás",
                "Omar", "Pablo", "Quique", "Ramiro", "Santiago", "Tomás",
                "Ulises", "Valentín", "Walter"
        );

        for (int i = 0; i < 11; i++) {
            personajes.add(new Personaje(nombresFemeninos.get(i), false));
            personajes.add(new Personaje(nombresMasculinos.get(i), true));
        }
        personajes.add(new Personaje(nombresFemeninos.get(20), false));

    }




    public List<Personaje> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(List<Personaje> personajes) {
        this.personajes = personajes;
    }
}
