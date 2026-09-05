package com.uade.controller;

import com.uade.model.Personaje;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreadorPersonajes {
    private List<Personaje> personajes;

    public CreadorPersonajes() {
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

        String[] coloresDisponibles = {"Colorado", "Negro", "Amarillo"};
        Random random = new Random();

        // Creamos exactamente 23 personajes alternando géneros
        for (int i = 0; i < 23; i++) {
            boolean esHombre = (i % 2 != 0); // Si el índice es impar, es hombre. Si es par, mujer.

            // Toma el nombre de la lista correspondiente según el índice
            String nombre = esHombre ? nombresMasculinos.get(i) : nombresFemeninos.get(i);

            // Generación aleatoria de atributos
            String colorPelo = coloresDisponibles[random.nextInt(coloresDisponibles.length)];
            boolean usaLentes = random.nextBoolean();
            boolean esPelado = random.nextBoolean();
            boolean tieneGorro = random.nextBoolean();

            // Lógica simple: si es mujer o si es pelado, no tiene barba
            boolean tieneBarba = esHombre && random.nextBoolean();

            // Instanciamos pasándole los datos ya calculados (Cumple con Principio de Responsabilidad Única)
            Personaje p = new Personaje(
                    i + 1,
                    nombre,
                    esHombre,
                    colorPelo,
                    usaLentes,
                    tieneBarba,
                    esPelado,
                    tieneGorro
            );

            personajes.add(p);
        }
    }

    public List<Personaje> getPersonajes() {
        return personajes;
    }
}