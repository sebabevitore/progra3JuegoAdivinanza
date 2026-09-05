package com.uade.controller;

public class Play {
    private final PartidaController partidaController;

    public Play() {
        this.partidaController = new PartidaController();
    }

    public void iniciarPartida() {
        partidaController.iniciarPartida();
    }
}
