package com.uade.controller;

import com.uade.model.Personaje;
import com.uade.model.Player;
import com.uade.strategy.CatalogoFiltros;
import com.uade.strategy.Filtro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class PartidaController {
    private Player humano;
    private Player maquina;
    private Scanner scanner;
    private Random random;
    private Set<String> filtrosUsadosMaquina;

    public PartidaController() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
        this.filtrosUsadosMaquina = new HashSet<>();
    }

    public void iniciarPartida() {
        System.out.println("==========================================================================");
        System.out.println("          BIENVENIDO AL JUEGO DE ADIVINANZA (MODO CONSOLA)               ");
        System.out.println("==========================================================================");

        // 1. Generación de los 23 personajes
        CreadorPersonajes creador = new CreadorPersonajes();
        creador.inicializador();
        List<Personaje> personajes = creador.getPersonajes();

        // 2. Ordenamiento por género usando MergeSort (mujeres primero)
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(personajes, 0, personajes.size() - 1);

        // 3. Instanciación de Jugadores con su copia propia de los 23 personajes
        this.humano = new Player("Humano", personajes);
        this.maquina = new Player("Máquina", personajes);

        // 4. Selección ciega de personajes secretos
        // La máquina elige aleatoriamente
        Personaje secretoMaquina = personajes.get(random.nextInt(personajes.size()));
        maquina.setPersonajeSecreto(secretoMaquina);

        // El humano elige su personaje secreto
        seleccionarPersonajeHumano(personajes);

        // 5. Flujo de turnos alternados
        boolean turnoHumano = true;
        boolean partidaTerminada = false;

        System.out.println("\n¡Todo listo! Comienza la partida.");

        while (!partidaTerminada) {
            if (turnoHumano) {
                partidaTerminada = ejecutarTurnoHumano(humano, maquina);
            } else {
                partidaTerminada = ejecutarTurnoMaquina(maquina, humano);
            }

            if (!partidaTerminada) {
                turnoHumano = !turnoHumano;
            }
        }

        // Fin de partida: revelación de personajes
        System.out.println("\n==========================================================================");
        System.out.println("                           FIN DE LA PARTIDA                              ");
        System.out.println("==========================================================================");
        System.out.println("Personaje secreto de la Máquina: " + maquina.getPersonajeSecreto().getNombre() +
                " (ID: " + maquina.getPersonajeSecreto().getId() + ")");
        System.out.println("Tu personaje secreto era: " + humano.getPersonajeSecreto().getNombre() +
                " (ID: " + humano.getPersonajeSecreto().getId() + ")");
        System.out.println("==========================================================================");
    }

    private void seleccionarPersonajeHumano(List<Personaje> personajes) {
        System.out.println("\n--- LISTA DE PERSONAJES DISPONIBLES (Ordenados: Mujeres primero) ---");
        for (Personaje p : personajes) {
            System.out.println(p.toString());
        }
        System.out.println("--------------------------------------------------------------------------");

        Personaje elegido = null;
        while (elegido == null) {
            int idIngresado = leerEntero("Ingresa el ID del personaje que deseas elegir como tu personaje secreto: ", 1,
                    100);
            for (Personaje p : personajes) {
                if (p.getId() == idIngresado) {
                    elegido = p;
                    break;
                }
            }
            if (elegido == null) {
                System.out.println("ID no encontrado en la lista. Intenta nuevamente.");
            }
        }

        humano.setPersonajeSecreto(elegido);
        System.out.println("\n>>> Has elegido a: " + elegido.getNombre() + " (ID: " + elegido.getId() + ")");
    }

    private boolean ejecutarTurnoHumano(Player humano, Player maquina) {
        System.out.println("\n==================================================");
        System.out.println(">>> TURNO DE: " + humano.getNombre().toUpperCase());
        System.out.println("Sospechosos restantes en tu tablero: " + humano.getCandidatosRestantes().size());
        System.out.println("--------------------------------------------------");
        System.out.println("1. Hacer una pregunta sobre atributos (Filtro)");
        System.out.println("2. Arriesgar personaje directo");
        System.out.println("3. Ver mis sospechosos restantes");

        int opcion = leerEntero("Selecciona una opción (1-3): ", 1, 3);

        if (opcion == 3) {
            System.out.println("\n--- Tus sospechosos restantes ---");
            for (Personaje c : humano.getCandidatosRestantes()) {
                System.out.println(c.toString());
            }
            // Vuelve a dar las opciones de turno sin perderlo
            return ejecutarTurnoHumano(humano, maquina);
        }

        if (opcion == 1) {
            // Opción 1: Preguntar con Filtro
            List<Filtro> filtros = CatalogoFiltros.obtenerFiltrosDisponibles();
            System.out.println("\n--- Catálogo de Preguntas Disponibles ---");
            for (int i = 0; i < filtros.size(); i++) {
                System.out.println((i + 1) + ". " + filtros.get(i).getDescripcion());
            }

            int numFiltro = leerEntero("Elige el número de pregunta (1-" + filtros.size() + "): ", 1, filtros.size());
            Filtro filtroSeleccionado = filtros.get(numFiltro - 1);

            System.out.println("\nLe preguntas a la Máquina: \"" + filtroSeleccionado.getDescripcion() + "\"");
            boolean respuesta = maquina.responderFiltro(filtroSeleccionado);
            System.out.println("La Máquina responde: " + (respuesta ? ">>> SÍ <<<" : ">>> NO <<<"));

            int descartados = humano.descartarCandidatos(filtroSeleccionado, respuesta);
            System.out.println("Se descartaron " + descartados + " personajes de tu tablero.");
            System.out.println("Te quedan " + humano.getCandidatosRestantes().size() + " sospechosos restantes.");

            if (humano.getCandidatosRestantes().size() == 1) {
                System.out.println(
                        "¡Atención! Solo te queda 1 sospechoso: " + humano.getCandidatosRestantes().get(0).getNombre());
            }

            return false;
        } else {
            // Opción 2: Arriesgar personaje directo
            System.out.println("\n--- Tus sospechosos actuales ---");
            for (Personaje c : humano.getCandidatosRestantes()) {
                System.out.println("  [ID: " + c.getId() + "] " + c.getNombre());
            }

            int idArriesgado = leerEntero("Ingresa el ID del personaje que crees que es el secreto de la Máquina: ", 1,
                    100);
            Personaje candidato = null;
            for (Personaje p : humano.getCandidatosRestantes()) {
                if (p.getId() == idArriesgado) {
                    candidato = p;
                    break;
                }
            }

            if (candidato == null) {
                System.out.println(
                        "El ID " + idArriesgado + " no está en tu lista de sospechosos restantes. Pierdes el turno.");
                return false;
            }

            System.out.println("\nArriesgas a: " + candidato.getNombre() + " (ID: " + candidato.getId() + ")");
            if (maquina.esCorrecto(candidato)) {
                System.out.println("\n**************************************************************");
                System.out.println("¡CORRECTO! ¡Adivinaste el personaje secreto de la Máquina!");
                System.out.println(">>> ¡FELICITACIONES, HAS GANADO LA PARTIDA! <<<");
                System.out.println("**************************************************************");
                return true;
            } else {
                System.out.println("\n¡INCORRECTO! " + candidato.getNombre() + " NO es el personaje secreto.");
                System.out.println("El personaje " + candidato.getNombre() + " ha sido eliminado de tus sospechosos.");
                humano.getCandidatosRestantes().remove(candidato);
                System.out.println("Te quedan " + humano.getCandidatosRestantes().size()
                        + " sospechosos restantes. Pierdes el turno.");
                return false;
            }
        }
    }

    // =========================================================================
    // PUNTO DE EXTENSIÓN: Heurística de la Máquina
    // Un compañero de equipo integrará aquí la heurística Greedy definitiva.
    // =========================================================================
    public boolean ejecutarTurnoMaquina(Player maquina, Player rival) {
        System.out.println("\n==================================================");
        System.out.println(">>> TURNO DE: " + maquina.getNombre().toUpperCase());
        List<Personaje> candidatos = maquina.getCandidatosRestantes();
        System.out.println("La Máquina tiene " + candidatos.size() + " sospechosos restantes.");

        // Regla 1: Si le queda 1 solo candidato, arriesga directamente ese personaje
        if (candidatos.size() == 1) {
            Personaje candidatoUnico = candidatos.get(0);
            System.out.println("La Máquina decide arriesgar directo: ¿Tu personaje es " +
                    candidatoUnico.getNombre() + " (ID: " + candidatoUnico.getId() + ")?");

            if (rival.esCorrecto(candidatoUnico)) {
                System.out.println("\n**************************************************************");
                System.out.println("¡La Máquina ha adivinado correctamente tu personaje secreto!");
                System.out.println(">>> LA MÁQUINA HA GANADO LA PARTIDA. <<<");
                System.out.println("**************************************************************");
                return true;
            } else {
                System.out.println("La Máquina falló al arriesgar.");
                candidatos.remove(candidatoUnico);
                return false;
            }
        }

        // Regla 2: Si le quedan más de 1, selecciona una pregunta disponible
        Filtro filtroElegido = seleccionarFiltroMaquina();
        System.out.println("La Máquina pregunta: \"" + filtroElegido.getDescripcion() + "\"");

        boolean respuesta = rival.responderFiltro(filtroElegido);
        System.out.println("Respuesta a la Máquina: " + (respuesta ? ">>> SÍ <<<" : ">>> NO <<<"));

        int descartados = maquina.descartarCandidatos(filtroElegido, respuesta);
        System.out.println("La Máquina descartó " + descartados + " sospechosos. Le quedan " +
                maquina.getCandidatosRestantes().size() + " personajes.");

        return false;
    }

    /**
     * Selección de filtro de la máquina.
     * (Punto de extensión para conectar con algoritmo Greedy).
     */
    private Filtro seleccionarFiltroMaquina() {
        List<Filtro> catalogo = CatalogoFiltros.obtenerFiltrosDisponibles();
        List<Filtro> noUsados = new ArrayList<>();

        for (Filtro f : catalogo) {
            if (!filtrosUsadosMaquina.contains(f.getDescripcion())) {
                noUsados.add(f);
            }
        }

        Filtro seleccionado;
        if (!noUsados.isEmpty()) {
            seleccionado = noUsados.get(random.nextInt(noUsados.size()));
        } else {
            // si ya se usaron todas las preg, se elige una random del catalogo
            seleccionado = catalogo.get(random.nextInt(catalogo.size()));
        }

        filtrosUsadosMaquina.add(seleccionado.getDescripcion());
        return seleccionado;
    }

    private int leerEntero(String mensaje, int min, int max) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(input);
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("Por favor ingresa un número entre " + min + " y " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingresa un número entero.");
            }
        }
    }
}
