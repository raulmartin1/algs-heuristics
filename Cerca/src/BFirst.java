import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class BFirst extends Algoritme {

    public BFirst(int xIni, int yIni, int xFin, int yFin, IHeuristica heurTipus, List<Nodo> mapa) {
        
        pends = new PriorityQueue<>(Comparator.comparingDouble(Nodo::getHeur));
        tractats = new LinkedList<>();
        this.heurTipus = heurTipus;
        Nodo inicial = null;
        Nodo fi = null;
        for (Nodo node : mapa) {
        if (node.getX() == xIni && node.getY() == yIni) inicial = node;
        if (node.getX() == xFin && node.getY() == yFin) fi = node; 
        }
        this.fin=fi;
        this.ini = inicial;
        
        if(ini == null || fi == null) System.out.println("El node inicial o final no es troben al mapa");
        ini.setHeur(heurTipus.heuristica(ini, fin));
        pends.add(ini);
        this.mapa = mapa;
        trobat = false;

    }

    public List<Nodo> cerca(){
        while (!trobat && !(pends.isEmpty())){
            Nodo actual = pends.poll();     // S'extreu el primer el primer node de l'estructura de pendents
            if (actual.esIgual(fin)) {
                trobat = true;
                if (getResult().size() == 1) {
                    return null;            // Només hauria de passar quan l'estat inicial sigui igual al final
                } else return getResult();
            } else {
                for (Nodo n:actual.getAdj()) {
                    if (!(tractats.contains(n)) && !(pends.contains(n)) && n.getAltura() != -1) { // Si el vei encara no ha sigut tractat
                        if (!(n.equals(ini))) n.setPred(actual);  
                        n.setHeur(heurTipus.heuristica(n, fin)); 
                        pends.add(n);
                    }
                }
                tractats.add(actual);
            }
        }
        if (trobat) return getResult();
        else return null;
    }

}