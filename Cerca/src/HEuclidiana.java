public class HEuclidiana implements IHeuristica {
    @Override
    public Double heuristica(Nodo a, Nodo b) {
        int xResult = b.getX() - a.getX();
        int yResult = b.getY() - a.getY();
        return Math.sqrt((xResult*xResult+yResult*yResult)) + a.getDist()*0.5;
    
    }
}
