public class HManhattan implements IHeuristica {
    public Double heuristica(Nodo a, Nodo b) {
        int diffAltura = b.getAltura() - a.getAltura();
        Double temps = 0.0;
        if (diffAltura >= 0) temps = 1.0 + diffAltura; // Si la altura es la misma o subida
        else temps = 0.5;                              // Si es baixada sempre 1/2 unitat
        return temps + Double.valueOf(Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()));   // Es té en compte la distància Manhattan amb el temps
    }
}