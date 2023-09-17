package grafica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author ethan
 */
public class Grafica {

    private List<Vertice> vertices;
    private List<Arista> aristas;

    public Grafica() {
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    // Constructor de copia
    public Grafica(Grafica otraGrafica) {
        this.vertices = new ArrayList<>(otraGrafica.getVertices().size());
        for (Vertice vertice : otraGrafica.getVertices()) {
            this.vertices.add(vertice);
        }

        this.aristas = new ArrayList<>(otraGrafica.getAristas().size());
        for (Arista arista : otraGrafica.getAristas()) {
            Vertice nodoHijo = arista.getNodoDestino();
            Vertice nodoPadre = arista.getNodoOrigen();
            this.aristas.add(new Arista(nodoPadre, nodoHijo));
        }
        
    }

    public void agregarVertice(Vertice vertice) {
        vertices.add(vertice);
    }

    public void agregarArista(Vertice origen, Vertice destino) {
        aristas.add(new Arista(origen, destino));
    }

    public Grafica construirGraficaPorArchivo(String nombreArchivo) {
        Grafica grafica = new Grafica();

        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            // Leer la primera línea para obtener los vértices
            String lineaVertices = br.readLine();
            String[] vertices = lineaVertices.split(",");
            for (String valorVertice : vertices) {
                try {
                    int valor = Integer.parseInt(valorVertice.trim());
                    grafica.agregarVertice(new Vertice(valor));
                } catch (NumberFormatException e) {
                }
            }

            // Leer las líneas restantes para obtener las aristas
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] aristaInfo = linea.split(",");
                int origenValor = 0;
                int destinoValor = 0;
                try {
                    origenValor = Integer.parseInt(aristaInfo[0].trim());
                    destinoValor = Integer.parseInt(aristaInfo[1].trim());
                } catch (NumberFormatException e) {
                }

                // Buscar los vértices correspondientes en la gráfica
                Vertice origen = null;
                Vertice destino = null;
                if (origenValor >= 0 && destinoValor >= 0) {
                    origen = grafica.buscarVerticePorValor(origenValor);
                    destino = grafica.buscarVerticePorValor(destinoValor);
                }

                // Si los vértices existen, agregar la arista
                if (origen != null && destino != null) {
                    grafica.agregarArista(origen, destino);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return grafica;
    }

    private Vertice buscarVerticePorValor(int verticeABuscar) {
        for (Vertice vertice : vertices) {
            if (vertice.getValor() == verticeABuscar) {
                return vertice;
            }
        }
        return null; // Si no se encuentra ningún vértice con el valor buscado
    }

    public List<Vertice> vecindadVertice(Vertice vertice) {
        List<Vertice> vecindadVertice = new ArrayList<>();

        if (vertice != null) {
            if (buscarVerticePorValor(vertice.getValor()) != null) {
                vecindadVertice.add(vertice);
                for (Arista arista : aristas) {
                    Vertice origen = arista.getNodoOrigen();
                    if (origen.equals(vertice)) {
                        vecindadVertice.add(arista.getNodoDestino());
                    }
                }

            }
        }

        return vecindadVertice;
    }
    
    
    

    public Grafica conjuntoIndependiente() {

        return null;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public void setAristas(List<Arista> aristas) {
        this.aristas = aristas;
    }

    @Override
    public String toString() {

        String grafica = "Numero de vertices: " + vertices.size() + "\nNumero de aristas: " + aristas.size()
                + "\nGrafica Construida:\n";

        if (aristas.isEmpty()) {
            grafica = grafica + vertices.toString();
        } else {
            grafica = grafica + aristas.toString();
        }
        return grafica;

    }

}
