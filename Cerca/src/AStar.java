import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class AStar extends Algoritme {

    public AStar(int xIni, int yIni, int xFin, int yFin, IHeuristica heurTipus, List<Nodo> mapa){
        // A* -> f(n) = g(n) + h(n)
        pends = new PriorityQueue<>(Comparator.comparingDouble(n -> n.getDist() + n.getHeur()));
        tractats = new ArrayList<>();

        this.heurTipus = heurTipus;
        Nodo inicial = null, fi = null;
        // Cercar si nocd inicial i final al mapa
        for(Nodo node : mapa){
            if (node.getX() == xIni && node.getY() == yIni) inicial = node;
            if (node.getX() == xFin && node.getY() == yFin) fi = node;
        }
        this.fin=fi;
        this.ini=inicial;

        if(inicial == null || fi == null) System.out.println("El node inicial o final no es troben al mapa");; 

        ini.setDist(0.0); // Cost inicial a 0 -> g(n) = 0
        ini.setHeur(heurTipus.heuristica(ini, fi));
        pends.add(ini);
        this.mapa=mapa;
        this.trobat=false;
    }


    public List<Nodo> cerca(){
        while (!trobat && !(pends.isEmpty())){
            Nodo actual = pends.poll();     // S'extreu el primer el primer node de l'estructura de pendents (amb menor f(n) )
            
            if (actual.esIgual(fin)) {
                trobat = true;
                if (getResult().size() == 1) {
                    return null;            // Només hauria de passar quan l'estat inicial sigui igual al final
                } else return getResult();

            } else {
                for (Nodo n : actual.getAdj()) {
                    double novaDist = actual.getDist() + calcularNovaDist(actual, n);   // Calcular g(n)
                    if (!(tractats.contains(n)) && !(pends.contains(n))  && n.getAltura() != -1) {              // Si el vei encara no ha sigut tractat
                        n.setHeur(heurTipus.heuristica(n, fin));
                        
                        n.setDist(novaDist);
                        n.setPred(actual);  // Només es faria si el cost es menor
                        pends.add(n);    
                    }
                    else if (novaDist < n.getDist() && n.getAltura() != -1) {  // Si trobem un cami mes curt
                        n.setDist(novaDist);
                        if (!(n.equals(ini))) n.setPred(actual);
                        pends.remove(n);
                        pends.add(n);
                    }
                }
                tractats.add(actual);
            }
        }
        if (trobat) return getResult();
        else return null;
    }

    public double calcularNovaDist(Nodo act, Nodo vei){
        if((vei).getAltura() == -1) return 9999;
        
        double costMoviment;
        int diffAltura = vei.getAltura() - act.getAltura();
        if (diffAltura >= 0) costMoviment = 1+diffAltura; // Si la altura es la misma o subida
        else costMoviment = 0.5; // Si es baixada sempre 1/2 unitat
        
        return costMoviment;  

    }


}
