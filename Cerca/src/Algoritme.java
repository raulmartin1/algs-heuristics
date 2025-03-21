import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public abstract class Algoritme {
    protected Nodo ini, fin;
    protected boolean trobat;
    protected IHeuristica heurTipus;
    protected List<Nodo> mapa;
    protected List<Nodo> solucio;
    private Double temps;
    protected PriorityQueue<Nodo> pends;
    protected List<Nodo> tractats;

    public abstract List<Nodo> cerca();
    
    public Algoritme(){
        trobat = false;
        temps = 0.0;
    }

    public List<Nodo> getResult(){
        if (trobat && solucio == null) {
            List<Nodo> result = new LinkedList<>();
            result.add(fin);
            Nodo nodo = fin;
            while (nodo.getPred() != null) {
                temps += sumaTemps(nodo.getPred(), nodo);
                result.add(nodo);
                nodo = nodo.getPred();
            }
            result.add(nodo);
            Collections.reverse(result);
            solucio = result;
        }
        return solucio;
    }

    public Double getTemps(){
        return temps;
    }

    public Double sumaTemps(Nodo n1, Nodo n2){
        Double diffAltura = Double.valueOf(n2.getAltura() - n1.getAltura());
        if (diffAltura >= 0) return (1 + diffAltura);   // Si la altura es la misma o subida
        else return 0.5;                                // Si es baixada sempre 1/2 unitat
        
    }

    public List<Nodo> getVisitats() {
        return tractats;
    }

    public int getIteracions(){
        return tractats.size();
    }
}
