# 🕹️ TP Programación III: Juego de Adivinanzas (Adivina Quién)

## Integrantes
**Bevitore Sebastian Ivan** (LU:1211500)
**Lazaro** (LU:)



## 1. Divide y Conquista (Divide & Conquer)
En nuestro juego la aplicamos en:

### A. Ordenamiento Inicial: ¿Por qué MergeSort y no QuickSort o Burbujeo?
La consigna nos pide que los 23 personajes iniciales se ubiquen ordenados por su género (mujeres primero).
* **¿Cómo divide y conquista?**
  1. **Dividir:** Corta recursivamente la lista a la mitad mediante el índice medio `(ini + fin) / 2`.
  2. **Conquistar:** Llega hasta el caso trivial de listas con 1 solo personaje.
  3. **Combinar (Merge):** Fusiona las mitades comparando los géneros y ubicando primero a las mujeres en un arreglo temporal, reconstruyendo la lista completa.
* **¿Por qué descartamos Burbujeo (Bubble Sort)?**
  El ordenamiento por burbuja tiene un costo cuadrático de $O(n^2)$. Si bien con 23 personajes la computadora lo resuelve rápido, conceptualmente es pésimo si el día de mañana el juego escala a 10.000 personajes.
* **¿Por qué elegimos MergeSort sobre QuickSort?**
  * QuickSort depende críticamente de elegir un buen "pivot". Si el pivot queda desbalanceado (lo cual es muy probable en un conjunto donde solo hay dos valores posibles: hombre o mujer), QuickSort se degrada a su peor caso de $O(n^2).
  * **MergeSort es predecible y seguro:** garantiza siempre un tiempo de ejecución casi lineal de $O(n \log n)$ tanto en el mejor, promedio y peor caso.

### B. Proceso de Búsqueda y Descarte (Adivinanza en el Tablero)
Acá es donde está el corazón de Divide y Conquista dentro del juego:
* **El problema:** Encontrar al personaje secreto entre un grupo de sospechosos restantes.
* **Dividir el espacio de búsqueda:** En vez de hacer fuerza bruta preguntando de a uno, cada pregunta sobre un atributo divide el conjunto de personajes posibles en dos mitades.
* **Conquistar y Descartar:** La respuesta del rival nos dice en qué subconjunto está el personaje. Inmediatamente descartamos a todos los que no coincidan.
* **Caso trivial:** Cuando la lista de sospechosos se reduce a **1 personaje**, el problema está resuelto.

---

## 2. Algoritmo Voraz (Greedy) en el Turno de la Máquina
