import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Mapa mapa = new Mapa();
        mapa.carregarMapa("mapa2.txt");
        BFirst alg1 = new BFirst(0, 0, 9, 9, new HAltura(), mapa.getMapa());
       
        AStar alg2 = new AStar(0, 0, 9, 9, new HAltura(), mapa.getMapa());
       
       System.out.println("LLegenda: Color blanc verde representa el camí, gris els visitats y roig el precipicis\n");

        System.out.println("===> BEST FIRST <===");
        List<Nodo> res = alg1.cerca();   
        comprovarRes(res, mapa, alg1.getVisitats());
        System.out.println("Amb temps: " +alg1.getTemps());
        System.out.println("Estats tractats: "+alg1.getIteracions());

        System.out.println("\n===> A* <===");
        List<Nodo> res2 = alg2.cerca();
        comprovarRes(res2, mapa, alg2.getVisitats());
        System.out.println("Amb temps: " +alg2.getTemps());
        System.out.println("Estats tractats: "+alg2.getIteracions());

        System.out.println("");
    }

    public static void comprovarRes(List<Nodo> res, Mapa mapa, List<Nodo> tractats){
        if (res != null){
            System.out.println("Solució trobada:");
            for(Nodo node : res){
                System.out.print("("+ node.getX() + ", " + node.getY() + ")");
            }
            
            System.out.println("\n\nMapa amb el camí resaltat:");
            imprimirMapa(mapa, res, tractats);
        }
        else {
            System.out.println("No s'ha trobat solució");
        }  
    }

    public static void imprimirMapa(Mapa mapa, List<Nodo> res, List<Nodo> tractats) {
        int rows = 10;
        int cols = 10;
        String[][] visualMap = new String[rows][cols];

        for (Nodo nodo : mapa.getMapa()) {
            if (nodo.getAltura() == -1) {
                visualMap[nodo.getX()][nodo.getY()] = "\u001B[31m#\u001B[0m"; // Rojo para precipicio
            } else {
                visualMap[nodo.getX()][nodo.getY()] = "\u001B[37m" + nodo.getAltura() + "\u001B[0m"; // Blanco para alturas
            }
        }

        for (Nodo nodo : tractats) {
            visualMap[nodo.getX()][nodo.getY()] = "\u001B[90m" + nodo.getAltura() + "\u001B[0m"; // Negrita para visitados
        }

        for (Nodo nodo : res) {
            visualMap[nodo.getX()][nodo.getY()] = "\u001B[32m" + nodo.getAltura() + "\u001B[0m"; // Verde para el camino
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(visualMap[i][j] + " ");
            }
            System.out.println();
        }
    }
}
