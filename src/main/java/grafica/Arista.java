/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grafica;

/**
 *
 * @author ethan
 */

public class Arista {
    private Vertice nodoOrigen;
    private Vertice nodoDestino;

    public Arista(Vertice nodoHijo, Vertice nodoPadre) {
        this.nodoOrigen = nodoHijo;
        this.nodoDestino = nodoPadre;
    }

    public Vertice getNodoOrigen() {
        return nodoOrigen;
    }

    public Vertice getNodoDestino() {
        return nodoDestino;
    }

    @Override
    public String toString() {
        return "(" + nodoOrigen + ", " + nodoDestino + ")";
    }

}
