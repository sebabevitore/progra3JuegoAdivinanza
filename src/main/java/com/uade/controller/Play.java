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

<<<<<<< Updated upstream
        for (String nombre : nombresFemeninos) {
            personajes.add(new Personaje(nombre, false));
=======
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
>>>>>>> Stashed changes
        }

        for (String nombre : nombresMasculinos) {
            personajes.add(new Personaje(nombre, true));
        }






    }

}
