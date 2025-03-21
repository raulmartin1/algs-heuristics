import java.util.ArrayList;
import java.util.List;

public class Nodo {
    private int x, y, altura;
    private Double h, dist;
    private Nodo pred;
    private List<Nodo> adj;
    
    public Nodo (int x, int y, Double h, int altura, Nodo pred){
        this.x = x;
        this.y = y;
        dist = 0.0;
        this.h = h;
        this.altura = altura;
        this.pred = pred;
        this.adj = new ArrayList<>();
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAltura() {
        return altura;
    }

    public Double getDist() {
        return dist;
    }

    public void setDist(Double dist) {
        this.dist = dist;
    }

    public void setPred(Nodo pred) {
        this.pred = pred;
    }

    public Double getH() {
        return h;
    }

    public Nodo getPred() {
        return pred;
    }

    public Double getHeur() {
        return h;
    }

    public void setHeur(Double heur) {
        h = heur;
    }

    public void addAdj(Nodo n) {
        if (!(adj.contains(n))) adj.add(n);
    }

    public List<Nodo> getAdj() {
        return adj;
    }

    public boolean esIgual(Nodo nodo) {
        return (x==nodo.x) && (y==nodo.y);
    }
}
