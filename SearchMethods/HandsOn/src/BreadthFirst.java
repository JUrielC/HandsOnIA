import java.util.ArrayList;

public class BreadthFirst extends SearchMethods {

    BreadthFirst(String origen, String destino){
        ArrayList<Nodo> listaAbierta = new ArrayList<>();
        ArrayList <Nodo> listaCerrada = new ArrayList<>();

        Nodo nodoRaiz = new Nodo(origen);
        crearArbol(nodoRaiz,destino);
        listaAbierta.add(nodoRaiz);

        breadthSearch(listaAbierta,listaCerrada,destino);

        mostrarRutasBreadth(listaCerrada);

    }

    public static void main (String [] args){
        new BreadthFirst(args[0],args[1]);
    }
}
