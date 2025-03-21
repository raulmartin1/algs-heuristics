import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mapa {
    List<Nodo> nodos;
     
    public void carregarMapa(String fitxer) {
        List<int[]> mapa = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fitxer))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                linia = linia.replaceAll("[\\[\\]]", ""); // Elimina els [] si estan presents
                String[] parts = linia.trim().split(",\\s*"); // Separa per comes

                int[] fila = Arrays.stream(parts)
                                   .mapToInt(Integer::parseInt)
                                   .toArray();
                mapa.add(fila);
            }
        } catch (IOException e) {
            System.err.println("Error llegint el fitxer: " + e.getMessage());
        }

        // Passar el mapa a una llista de nodes
        nodos = new ArrayList<>();
        for (int i = 0; i < mapa.size(); i++) {
            for (int j = 0; j < mapa.get(i).length; j++) {
                nodos.add(new Nodo(i, j, 9999.9, mapa.get(i)[j], null));
            }
        }

        // S'estableixen els nodes son adjacents
        for (int i = 0; i < mapa.size(); i++) {
            for (int j = 0; j < mapa.get(i).length; j++) {
                Nodo nodoActual = nodos.get(i * mapa.get(i).length + j);
                if (i + 1 < mapa.size()) {
                    Nodo nodoAbaix = nodos.get((i + 1) * mapa.get(i).length + j);
                    nodoActual.addAdj(nodoAbaix);
                }
                if (j + 1 < mapa.get(i).length) {
                    Nodo nodoDreta = nodos.get(i * mapa.get(i).length + j + 1);
                    nodoActual.addAdj(nodoDreta);
                }
                if (i - 1 >= 0) {
                    Nodo nodoAdalt = nodos.get((i - 1) * mapa.get(i).length + j);
                    nodoActual.addAdj(nodoAdalt);
                }
                if (j - 1 >= 0) {
                    Nodo nodoEsquerra = nodos.get(i * mapa.get(i).length + j - 1);
                    nodoActual.addAdj(nodoEsquerra);
                }
            }
        }
    }

    public List<Nodo> getMapa() {
        return nodos;
    }
}
