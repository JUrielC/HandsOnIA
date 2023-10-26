import java.util.ArrayList;

public class Nodo {

    int distanciaActual;
    String nombre;
    Nodo padre;
    ArrayList<Nodo> hijos_Vecinos = new ArrayList<>();
    Nodo(String nom){
        this.nombre=nom;
    }

    public int getDistanciaActual() {
        return distanciaActual;
    }

    public void setDistanciaActual(int distanciaActual) {
        this.distanciaActual = distanciaActual;
    }

        public String getNombre() {
        return nombre;
    }

    public Nodo getPadre() {
        return padre;
    }

    public ArrayList<Nodo> getHijos_Vecinos() {
        return hijos_Vecinos;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public void addHijo(Nodo hijo) {
        hijos_Vecinos.add(hijo);
        hijo.setPadre(this);
    }

    public void addVecino(Nodo vecino){
        hijos_Vecinos.add(vecino);
    }
}
