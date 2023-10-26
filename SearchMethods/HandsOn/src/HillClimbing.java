import java.util.ArrayList;

public class HillClimbing extends SearchMethods{

    HillClimbing(String raiz){

        ArrayList<Nodo> listaAbierta = new ArrayList<>();
        ArrayList <Nodo> listaCerrada = new ArrayList<>();
        Nodo origen = new Nodo(raiz);
        crearGrafo(origen);
        listaAbierta.add(origen);
        HillClimbingSearch(listaAbierta,listaCerrada,"");
    }

    public static void main (String [] args){

        new HillClimbing(args[0]);
    }

}
