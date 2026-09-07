package com.uade.controller;

import java.util.Scanner;

public class Play {
    private final PartidaController partidaController;

    public Play() {
        this.partidaController = new PartidaController();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Elegí un modo de juego:");
        System.out.println("1. Humano vs Máquina");
        System.out.println("2. Máquina vs Máquina (proceso de decisión visible)");
        System.out.print("Opción: ");
        String opcion = scanner.nextLine().trim();

        if (opcion.equals("2")) {
            partidaController.iniciarPartidaMaquinaVsMaquina();
        } else {
            partidaController.iniciarPartida();
        }
        
        scanner.close();
    }

    public void iniciarPartida() {
        partidaController.iniciarPartida();
    }
}