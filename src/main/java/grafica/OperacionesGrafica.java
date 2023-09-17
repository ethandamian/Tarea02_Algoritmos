/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafica;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author ethan
 */
public class OperacionesGrafica {

    public Grafica eliminarVecidadDeGrafica(List<Vertice> listaDeVertices, Grafica grafica) {

        Grafica graficaResultado = new Grafica(grafica);

        if (listaDeVertices != null) {
            Iterator<Arista> iteradorAristas = graficaResultado.getAristas().iterator();

            while (iteradorAristas.hasNext()) {
                Arista arista = iteradorAristas.next();
                Vertice origen = arista.getNodoOrigen();
                Vertice destino = arista.getNodoDestino();

                for (Vertice vertice : listaDeVertices) {
                    if (origen.equals(vertice) || destino.equals(vertice)) {
                        iteradorAristas.remove();
                        break; // Salir del bucle una vez que se elimine una arista
                    }
                }
            }

            // Eliminar vértices de la lista de vértices
            graficaResultado.getVertices().removeAll(listaDeVertices);
        }
        
        System.out.println("Grafica original: " + grafica.getAristas());
        System.out.println("Grafica sin vecindiad:" + graficaResultado.getAristas());
        System.out.println("Vertices de grafica resultado:" + graficaResultado.getVertices());
        
        return graficaResultado;
    }

    public List<Vertice> checarCasosBase(Grafica grafica) {

        List<Vertice> conjuntoIndependiente = new ArrayList<>();

        if (grafica.getAristas().isEmpty()) {
            for (Vertice vertice : grafica.getVertices()) {
                conjuntoIndependiente.add(vertice);
            }

            return conjuntoIndependiente;
        }

        return null;

    }

    public Vertice encontrarVerticeConExgradoMaximo(Grafica grafica) {
        Vertice verticeConExgradoMaximo = null;
        int exgradoMaximo = -1; // Inicializado con un valor negativo para manejar vértices sin salida

        for (Vertice vertice : grafica.getVertices()) {
            int exgrado = calcularExgrado(vertice, grafica);
            if (exgrado > exgradoMaximo) {
                exgradoMaximo = exgrado;
                verticeConExgradoMaximo = vertice;
            }
        }

        return verticeConExgradoMaximo;
    }

    public int calcularExgrado(Vertice vertice, Grafica grafica) {
        int exgrado = 0;

        for (Arista arista : grafica.getAristas()) {
            if (arista.getNodoOrigen().equals(vertice)) {
                exgrado++;
            }
        }

        return exgrado;
    }

    public boolean esConjuntoIndependiente(Grafica grafica, List<Vertice> listaDeVertices) {
        // Itera a través de cada par de vértices en la lista
        for (int i = 0; i < listaDeVertices.size(); i++) {
            Vertice vertice1 = listaDeVertices.get(i);
            for (int j = i + 1; j < listaDeVertices.size(); j++) {
                Vertice vertice2 = listaDeVertices.get(j);

                // Verifica si los vértices son adyacentes en la gráfica
                if (sonAdyacentes(vertice1, vertice2, grafica)) {
                    return false; // No es un conjunto independiente
                }
            }
        }

        return true; // Es un conjunto independiente
    }

    public boolean sonAdyacentes(Vertice vertice1, Vertice vertice2, Grafica grafica) {
        for (Arista arista : grafica.getAristas()) {
            // Comprueba si la arista conecta los vértices vertice1 y vertice2
            if ((arista.getNodoOrigen().equals(vertice1) && arista.getNodoDestino().equals(vertice2))
                    || (arista.getNodoOrigen().equals(vertice2) && arista.getNodoDestino().equals(vertice1))) {
                return true; // Los vértices son adyacentes
            }
        }
        return false; // Los vértices no son adyacentes
    }
    
    
    public Set<Vertice> encontrarConjuntoIndependienteMaximo(Grafica grafica) {
        
        
        Set<Vertice> conjuntoIndependienteMaximo = new HashSet<>();

        // Caso base: Si la gráfica está vacía, no hay más vértices que añadir al conjunto independiente
        if (grafica.getVertices().isEmpty()) {
            return conjuntoIndependienteMaximo;
        }

        // Paso recursivo: Encuentra el vértice con el exgrado más grande
        Vertice verticeMaximoExgrado = encontrarVerticeConExgradoMaximo(grafica);
        System.out.println("Vertice al azar: " + verticeMaximoExgrado);

        List<Vertice> vecindadVerticeExgradoMax = grafica.vecindadVertice(verticeMaximoExgrado);
        // Crea una gráfica inducida H sin el vértice y su vecindad
        Grafica graficaInducida = eliminarVecidadDeGrafica(vecindadVerticeExgradoMax, grafica);

        // Realiza la recursión en la gráfica inducida
        Set<Vertice> conjuntoIndependienteRecursivo = encontrarConjuntoIndependienteMaximo(graficaInducida);

        // Agrega el vértice seleccionado al conjunto independiente máximo
        conjuntoIndependienteMaximo.add(verticeMaximoExgrado);

        // Verifica si es necesario remover vértices que causen conflicto
        for (Vertice vertice : conjuntoIndependienteRecursivo) {
            if (!sonAdyacentes(verticeMaximoExgrado, vertice, grafica)) {
                conjuntoIndependienteMaximo.add(vertice);
            }else{
                conjuntoIndependienteMaximo.remove(vertice);
            }
        }

        return conjuntoIndependienteMaximo;
    }
    
    
    public Vertice obtenerVerticeAlAzar(Grafica grafica) {
    List<Vertice> vertices = grafica.getVertices();
    if (vertices.isEmpty()) {
        // Si no hay vértices en la gráfica, devuelve null o maneja el caso según tus necesidades.
        return null;
    }

    Random rand = new Random();
    int indiceAleatorio = rand.nextInt(vertices.size()); // Genera un índice aleatorio

    return vertices.get(indiceAleatorio); // Devuelve el vértice en el índice aleatorio
}


}
