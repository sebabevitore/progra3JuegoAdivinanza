package com.uade.controller;

import com.uade.model.Personaje;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    // 1. Método principal que divide recursivamente
    public void mergeSort(List<Personaje> u, int ini, int fin) {
        if (ini < fin) {
            int mid = (ini + fin) / 2;
            mergeSort(u, ini, mid);       // Ordena mitad izquierda
            mergeSort(u, mid + 1, fin);   // Ordena mitad derecha
            merge(u, ini, fin);           // Mezcla ambas mitades
        }
    }

    // 2. Método que combina y ordena a las mujeres primero
    private void merge(List<Personaje> u, int ini, int fin) {
        int mid = (ini + fin) / 2;
        int i = ini;       // Puntero para la mitad izquierda
        int j = mid + 1;   // Puntero para la mitad derecha

        // Array temporal para guardar la mezcla
        List<Personaje> w = new ArrayList<>();

        for (int k = 0; k <= (fin - ini); k++) {
            boolean ganaIzquierda = false;

            // Si todavía quedan elementos en la izquierda
            if (i < mid + 1) {
                // Si la derecha se vació (j > fin), o si comparamos y gana la izquierda
                if (j > fin) {
                    ganaIzquierda = true;
                } else {
                    boolean izqEsMujer = !u.get(i).isEsHombre();
                    boolean derEsMujer = !u.get(j).isEsHombre();

                    // La izquierda tiene prioridad si es Mujer, o si ambos son Hombres
                    if (izqEsMujer || (!izqEsMujer && !derEsMujer)) {
                        ganaIzquierda = true;
                    }
                }
            }

            // Guardamos al ganador en la lista temporal 'w'
            if (ganaIzquierda) {
                w.add(u.get(i));
                i++;
            } else {
                w.add(u.get(j));
                j++;
            }
        }

        // Finalmente, copiamos los elementos ordenados de 'w' a la lista original 'u'
        for (int k = 0; k <= (fin - ini); k++) {
            u.set(ini + k, w.get(k));
        }
    }
}
