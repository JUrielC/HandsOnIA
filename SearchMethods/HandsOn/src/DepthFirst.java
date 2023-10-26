import java.util.ArrayList;

public class DepthFirst extends SearchMethods {

    DepthFirst (String origen, String destino){

        ArrayList <Nodo> listaAbierta = new ArrayList<>();
        ArrayList <Nodo> listaCerrada = new ArrayList<>();

        Nodo nodoRaiz = new Nodo(origen);
        crearArbol(nodoRaiz,destino);
        listaAbierta.add(nodoRaiz);

        DepthSearch(destino, listaAbierta,listaCerrada);

    }

    public  static  void main (String [] args){
        new DepthFirst(args[0], args[1]);
    }

}

