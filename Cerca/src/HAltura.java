public class HAltura implements IHeuristica {
    public Double heuristica(Nodo a, Nodo b) {
        double distancia = Math.abs(b.getX() - a.getX()) + Math.abs(b.getY() - a.getY());
        
        double diferenciaAltura = Math.abs(b.getAltura() - a.getAltura());
       
        return distancia + (diferenciaAltura * 0.5);

    }
}
