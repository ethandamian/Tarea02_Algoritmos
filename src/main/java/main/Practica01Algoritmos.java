/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import grafica.Grafica;
import grafica.OperacionesGrafica;
import grafica.Vertice;
import java.util.List;

/**
 *
 * @author ethan
 */
public class Practica01Algoritmos {

    public static void main(String[] args) {
        Grafica grafica = new Grafica();
        OperacionesGrafica operacionesGrafica = new OperacionesGrafica();
        String archivoTexto = "src/main/java/grafica/archivo/archivo-input.txt";
        
        
        Grafica graficaConstruida = grafica.construirGraficaPorArchivo(archivoTexto);
        System.out.println(graficaConstruida);
        
        
        
        System.out.println("Conjunto Independiente:" + operacionesGrafica.encontrarConjuntoIndependienteMaximo(graficaConstruida));
        
      
        
       // List<Vertice> listaVertices = graficaConstruida.getVertices();
//      
        //Vertice vertice = listaVertices.get(5);
//        Vertice verticeOcho = listaVertices.get(7);
//        Vertice verticeCinco = listaVertices.get(4);
//        
//        
//        System.out.println(operacionesGrafica.sonAdyacentes(verticeCuatro, verticeOcho, graficaConstruida));
        
        //List<Vertice> vecidadVertices = graficaConstruida.vecindadVertice(vertice);
//        
        //System.out.println(vecidadVertices);
//        
       // Grafica graficaSinVecindad = operacionesGrafica.eliminarVecidadDeGrafica(vecidadVertices,graficaConstruida);
//        System.out.println(graficaSinVecindad);

        
        
        
        
        
        
        
        
        
    }
}
