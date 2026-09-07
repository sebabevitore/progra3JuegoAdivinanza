package com.uade.controller;

import com.uade.model.Personaje;
import com.uade.model.Player;
import com.uade.strategy.CatalogoFiltros;
import com.uade.strategy.Filtro;
import com.uade.strategy.SelectorDeFiltro;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PartidaController {
    private Player humano;
    private Player maquina;
    private Scanner scanner;
    private Random random;

    public PartidaController() {
        this.scanner = new Scanner(System.in);
        this.random = new Random();
    }

    // =========================================================================
    // MODO HUMANO VS MÁQUINA
    // =========================================================================
    public void iniciarPartida() {
        System.out.println("==========================================================================");
        System.out.println("          BIENVENIDO AL JUEGO DE ADIVINANZA (MODO CONSOLA)               ");
        System.out.println("==========================================================================");

        List<Personaje> personajes = generarYOrdenarPersonajes();

        this.humano = new Player("Humano", personajes);
        this.maquina = new Player("Máquina", personajes);

        Personaje secretoMaquina = personajes.get(random.nextInt(personajes.size()));
        maquina.setPersonajeSecreto(secretoMaquina);

        seleccionarPersonajeHumano(personajes);

        boolean turnoHumano = true;
        boolean partidaTerminada = false;

        System.out.println("\n¡Todo listo! Comienza la partida.");

        while (!partidaTerminada) {
            if (turnoHumano) {
                partidaTerminada = ejecutarTurnoHumano(humano, maquina);
            } else {
                partidaTerminada = ejecutarTurnoMaquina(maquina, humano, false);
            }
            if (!partidaTerminada) {
                turnoHumano = !turnoHumano;
            }
        }

        System.out.println("\n==========================================================================");
        System.out.println("                           FIN DE LA PARTIDA                              ");
        System.out.println("==========================================================================");
        System.out.println("Personaje secreto de la Máquina: " + maquina.getPersonajeSecreto().getNombre() +
                " (ID: " + maquina.getPersonajeSecreto().getId() + ")");
        System.out.println("Tu personaje secreto era: " + humano.getPersonajeSecreto().getNombre() +
                " (ID: " + humano.getPersonajeSecreto().getId() + ")");
        System.out.println("==========================================================================");
    }

    // =========================================================================
    // MODO MÁQUINA VS MÁQUINA (proceso de decisión visible)
    // =========================================================================
    public void iniciarPartidaMaquinaVsMaquina() {
        System.out.println("==========================================================================");
        System.out.println("               MODO MÁQUINA VS MÁQUINA (proceso visible)                 ");
        System.out.println("==========================================================================");

        List<Personaje> personajes = generarYOrdenarPersonajes();

        Player maquina1 = new Player("Máquina 1", personajes);
        Player maquina2 = new Player("Máquina 2", personajes);

        maquina1.setPersonajeSecreto(personajes.get(random.nextInt(personajes.size())));
        maquina2.setPersonajeSecreto(personajes.get(random.nextInt(personajes.size())));

        System.out.println("(Secretos asignados y ocultos entre sí; se revelan al finalizar la partida)");
        System.out.println("Total de candidatos iniciales por jugador: " + personajes.size());

        boolean turnoUno = true;
        boolean partidaTerminada = false;
        int numeroTurno = 1;

        while (!partidaTerminada) {
            System.out.println("\n----- Turno " + numeroTurno + " -----");
            if (turnoUno) {
                partidaTerminada = ejecutarTurnoMaquina(maquina1, maquina2, true);
            } else {
                partidaTerminada = ejecutarTurnoMaquina(maquina2, maquina1, true);
            }
            turnoUno = !turnoUno;
            numeroTurno++;
        }

        System.out.println("\n==========================================================================");
        System.out.println("                     FIN DE LA PARTIDA (MÁQUINA VS MÁQUINA)               ");
        System.out.println("==========================================================================");
        System.out.println("Secreto de Máquina 1: " + maquina1.getPersonajeSecreto().getNombre() +
                " (ID: " + maquina1.getPersonajeSecreto().getId() + ")");
        System.out.println("Secreto de Máquina 2: " + maquina2.getPersonajeSecreto().getNombre() +
                " (ID: " + maquina2.getPersonajeSecreto().getId() + ")");
        System.out.println("Turnos jugados: " + (numeroTurno - 1));
        System.out.println("==========================================================================");
    }

    // =========================================================================
    // GENERACIÓN Y ORDEN COMPARTIDOS POR AMBOS MODOS
    // =========================================================================
    private List<Personaje> generarYOrdenarPersonajes() {
        CreadorPersonajes creador = new CreadorPersonajes();
        creador.inicializador();
        List<Personaje> personajes = creador.getPersonajes();

        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(personajes, 0, personajes.size() - 1);

        return personajes;
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
            return ejecutarTurnoHumano(humano, maquina);
        }

        if (opcion == 1) {
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
    // TURNO DE MÁQUINA — reutilizado por ambos modos
    // mostrarProceso=false en Humano vs Máquina (no revela la deliberación)
    // mostrarProceso=true en Máquina vs Máquina (requisito de la consigna)
    // =========================================================================
    public boolean ejecutarTurnoMaquina(Player maquina, Player rival, boolean mostrarProceso) {
        System.out.println("\n==================================================");
        System.out.println(">>> TURNO DE: " + maquina.getNombre().toUpperCase());
        List<Personaje> candidatos = maquina.getCandidatosRestantes();
        System.out.println("La Máquina tiene " + candidatos.size() + " sospechosos restantes.");

        if (candidatos.size() == 1) {
            return arriesgarCandidato(maquina, rival, candidatos.get(0));
        }

        Filtro filtroElegido = seleccionarFiltroMaquina(maquina, candidatos, mostrarProceso);

        if (filtroElegido == null) {
            System.out.println("La Máquina ya no tiene preguntas útiles disponibles.");
            System.out.println("Quedan " + candidatos.size() + " sospechosos empatados en características.");
            Personaje candidatoElegido = candidatos.get(random.nextInt(candidatos.size()));
            return arriesgarCandidato(maquina, rival, candidatoElegido);
        }

        System.out.println("La Máquina pregunta: \"" + filtroElegido.getDescripcion() + "\"");
        boolean respuesta = rival.responderFiltro(filtroElegido);
        System.out.println("Respuesta a la Máquina: " + (respuesta ? ">>> SÍ <<<" : ">>> NO <<<"));

        int descartados = maquina.descartarCandidatos(filtroElegido, respuesta);
        System.out.println("La Máquina descartó " + descartados + " sospechosos. Le quedan " +
                maquina.getCandidatosRestantes().size() + " personajes.");

        return false;
    }

    private boolean arriesgarCandidato(Player maquina, Player rival, Personaje candidato) {
        System.out.println("La Máquina decide arriesgar directo: ¿Tu personaje es " +
                candidato.getNombre() + " (ID: " + candidato.getId() + ")?");

        if (rival.esCorrecto(candidato)) {
            System.out.println("\n**************************************************************");
            System.out.println("¡" + maquina.getNombre() + " ha adivinado correctamente el personaje secreto!");
            System.out.println(">>> " + maquina.getNombre().toUpperCase() + " HA GANADO LA PARTIDA. <<<");
            System.out.println("**************************************************************");
            return true;
        } else {
            System.out.println(maquina.getNombre() + " falló al arriesgar.");
            maquina.getCandidatosRestantes().remove(candidato);
            return false;
        }
    }

    /**
     * Selección de filtro de la máquina, ahora consultando el historial
     * propio de CADA jugador (maquina.yaUsoFiltro / registrarFiltroUsado)
     * en vez de un Set compartido — necesario para que dos máquinas
     * jugando entre sí no compartan su historial de preguntas.
     */
    private Filtro seleccionarFiltroMaquina(Player maquina, List<Personaje> candidatos, boolean mostrarProceso) {
        List<Filtro> catalogo = CatalogoFiltros.obtenerFiltrosDisponibles();
        List<Filtro> noUsados = new ArrayList<>();

        for (Filtro f : catalogo) {
            if (!maquina.yaUsoFiltro(f.getDescripcion())) {
                noUsados.add(f);
            }
        }

        if (noUsados.isEmpty()) {
            return null;
        }

        SelectorDeFiltro selector = new SelectorDeFiltro();
        Filtro seleccionado = selector.elegirMejorFiltro(candidatos, noUsados, mostrarProceso);
        
        if (seleccionado == null) {
            // El selector determinó que ningún filtro restante aporta
            // información nueva (indistinguibilidad total detectada antes
            // de gastar un turno preguntando).
            return null;
        }
        
        maquina.registrarFiltroUsado(seleccionado.getDescripcion());
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
