package com.uade.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CatalogoFiltros {

    private CatalogoFiltros() {
        // Clase utilitaria, evitar instanciación
    }

    public static List<Filtro> obtenerFiltrosDisponibles() {
        List<Filtro> filtros = new ArrayList<>();

        // Género
        filtros.add(new FiltroGenero(true));   // ¿Es hombre?
        filtros.add(new FiltroGenero(false));  // ¿Es mujer?

        // Calvicie
        filtros.add(new FiltroCalvicie(true));  // ¿Es pelado?
        filtros.add(new FiltroCalvicie(false)); // ¿Tiene pelo?

        // Lentes
        filtros.add(new FiltroLentes(true));   // ¿Usa lentes?
        filtros.add(new FiltroLentes(false));  // ¿No usa lentes?

        // Barba
        filtros.add(new FiltroBarba(true));    // ¿Tiene barba?
        filtros.add(new FiltroBarba(false));   // ¿No tiene barba?

        // Gorro
        filtros.add(new FiltroGorro(true));    // ¿Tiene gorro?
        filtros.add(new FiltroGorro(false));   // ¿No tiene gorro?

        // Color de pelo
        filtros.add(new FiltroColorPelo("Colorado"));
        filtros.add(new FiltroColorPelo("Negro"));
        filtros.add(new FiltroColorPelo("Amarillo"));

        return Collections.unmodifiableList(filtros);
    }
}
