package com.uade.strategy;

import com.uade.model.Personaje;

import java.util.List;

public class SelectorDeFiltro {

    public Filtro elegirMejorFiltro(List<Personaje> candidatos, List<Filtro> filtrosDisponibles) {
        Filtro mejorFiltro = null;
        int menorPeorCaso = Integer.MAX_VALUE;

        for (Filtro filtro : filtrosDisponibles) {
            int cumplen = 0;
            int noCumplen = 0;

            for (Personaje candidato : candidatos) {
                if (filtro.aplicaA(candidato)) {
                    cumplen++;
                } else {
                    noCumplen++;
                }
            }

            int peorCaso = Math.max(cumplen, noCumplen);

            if (peorCaso < menorPeorCaso) {
                menorPeorCaso = peorCaso;
                mejorFiltro = filtro;
            }
        }

        return mejorFiltro;
    }
}