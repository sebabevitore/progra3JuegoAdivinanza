package com.uade.strategy;

import com.uade.model.Personaje;

import java.util.List;

public class SelectorDeFiltro {
	public Filtro elegirMejorFiltro(List<Personaje> candidatos, List<Filtro> filtrosDisponibles, boolean mostrarProceso) {
	    Filtro mejorFiltro = null;
	    int menorPeorCaso = Integer.MAX_VALUE;

	    if (mostrarProceso) {
	        System.out.println("    [Evaluación de filtros sobre " + candidatos.size() + " candidatos]");
	    }

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

	        if (mostrarProceso) {
	            System.out.printf("      - %-30s cumplen=%-3d no_cumplen=%-3d peor_caso=%d%n",
	                    filtro.getDescripcion(), cumplen, noCumplen, peorCaso);
	        }

	        if (peorCaso < menorPeorCaso) {
	            menorPeorCaso = peorCaso;
	            mejorFiltro = filtro;
	        }
	    }
	    // Si incluso el mejor filtro disponible no logra separar a ningún
	    // candidato del resto (peor_caso == cantidad total de candidatos),
	    // significa que TODOS los filtros restantes son igual de inútiles:
	    // los candidatos actuales son indistinguibles con las preguntas
	    // que quedan. No tiene sentido gastar un turno preguntando.
	    if (menorPeorCaso == candidatos.size()) {
	        if (mostrarProceso) {
	            System.out.println("    [Ningún filtro disponible aporta información nueva sobre estos candidatos]");
	        }
	        return null;
	    }
	    
	    if (mostrarProceso) {
	        System.out.println("    [Elegido: " + mejorFiltro.getDescripcion() + " (peor caso = " + menorPeorCaso + ")]");
	    }

	    return mejorFiltro;
	}
}